package net.example.test_rss_reader.view.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import net.example.test_rss_reader.model.PageType
import net.example.test_rss_reader.view.FeedsFragment

class FeedsPagerAdapter(fragmentManager: FragmentManager) : FragmentPagerAdapter(
    fragmentManager,
    BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
) {
    override fun getItem(position: Int): Fragment = when (position) {
        PageType.SOCIAL.position -> FeedsFragment.newInstance(
            PageType.SOCIAL
        )
        PageType.ECONOMICS.position -> FeedsFragment.newInstance(
            PageType.ECONOMICS
        )
        PageType.LIFE.position -> FeedsFragment.newInstance(
            PageType.LIFE
        )
        else -> FeedsFragment.newInstance(
            PageType.TOP_PAGE
        )
    }

    override fun getCount(): Int = PageType.values().size
}
