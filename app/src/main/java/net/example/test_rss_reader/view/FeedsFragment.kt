package net.example.test_rss_reader.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.fragment_feeds.*
import net.example.test_rss_reader.R
import net.example.test_rss_reader.model.Feed
import net.example.test_rss_reader.model.PageType
import net.example.test_rss_reader.view.adapter.FeedRecyclerAdapter
import net.example.test_rss_reader.vm.FeedsViewModel
import net.example.test_rss_reader.vm.SharedProgressViewModel
import net.newstyleservice.common_ktx.extension.gone
import net.newstyleservice.common_ktx.extension.visible

class FeedsFragment : Fragment(), FeedRecyclerAdapter.ItemClickListener {

    private val viewModel: FeedsViewModel by viewModels()

    private val progressViewModel: SharedProgressViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_feeds, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        feedRecycler.adapter = FeedRecyclerAdapter(this)
        feedRecycler.setHasFixedSize(true)
        swipeRefresh.setOnRefreshListener {
            requestFeed()
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.getFeedList().observe(viewLifecycleOwner, Observer {
            if (!isResumed) return@Observer
            feedRecycler.visible()
            swipeRefresh.isRefreshing = false
            it?.let {
                val adapter: FeedRecyclerAdapter = feedRecycler.adapter as FeedRecyclerAdapter
                adapter.updateList(it)
            } ?: showNoItem()
            progressViewModel.updateProgressState(SharedProgressViewModel.State.HIDE)
        })
    }

    override fun onResume() {
        super.onResume()
        requestFeed()
    }

    private fun requestFeed() {
        textNoItem.gone()
        val type: PageType? = arguments?.getSerializable(ARGS_TYPE) as? PageType
        type?.let {
            progressViewModel.updateProgressState(SharedProgressViewModel.State.SHOW)
            viewModel.requestFeed(it)
        } ?: showNoItem()
    }

    override fun onItemClick(feed: Feed) {
        feed.link?.let {
            WebViewDialogFragment.newInstance(it).show(childFragmentManager, it)
        }
    }

    private fun showNoItem() {
        textNoItem.visible()
        feedRecycler.gone()
    }

    companion object {
        fun newInstance(urlConst: PageType) = FeedsFragment()
            .apply {
                arguments = bundleOf(
                    ARGS_TYPE to urlConst
                )
            }

        private const val ARGS_TYPE = "args_type"
    }
}
