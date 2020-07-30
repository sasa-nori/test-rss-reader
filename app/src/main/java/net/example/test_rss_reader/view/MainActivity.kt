package net.example.test_rss_reader.view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.activity_main.*
import net.example.test_rss_reader.R
import net.example.test_rss_reader.model.PageType
import net.example.test_rss_reader.view.adapter.FeedsPagerAdapter
import net.example.test_rss_reader.vm.SharedProgressViewModel

class MainActivity : AppCompatActivity() {

    private val progressViewModel: SharedProgressViewModel by viewModels()

    private var progressDialog: ProgressDialogFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewPager.adapter =
            FeedsPagerAdapter(
                supportFragmentManager
            )
        bottomNavigation.setOnNavigationItemSelectedListener { menu ->
            val position = when (menu.itemId) {
                R.id.page_top -> PageType.TOP_PAGE.position
                R.id.page_economics -> PageType.ECONOMICS.position
                R.id.page_social -> PageType.SOCIAL.position
                R.id.page_life -> PageType.LIFE.position
                else -> null
            }
            return@setOnNavigationItemSelectedListener position?.let {
                viewPager.currentItem = it
                true
            } ?: false
        }
        progressViewModel.getProgressState().observe(this, Observer {
            it?.let {
                when (it) {
                    SharedProgressViewModel.State.SHOW ->
                        progressDialog = ProgressDialogFragment.newInstance().apply {
                            show(
                                supportFragmentManager,
                                ProgressDialogFragment::class.java.canonicalName
                            )
                        }
                    else -> progressDialog?.dismiss()
                }
            }
        })
    }
}
