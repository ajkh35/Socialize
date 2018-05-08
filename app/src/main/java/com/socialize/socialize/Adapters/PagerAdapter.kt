package com.socialize.socialize.Adapters

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.socialize.socialize.Fragments.HomeFeed
import com.socialize.socialize.Fragments.PeopleFragment
import com.socialize.socialize.Fragments.PlaceholderFragment
import com.socialize.socialize.Fragments.SettingsFragment
import com.socialize.socialize.Utilities.Constants

class PagerAdapter(fm: FragmentManager?) : FragmentPagerAdapter(fm) {

    private val TAB_COUNT = 3

    override fun getCount(): Int {
        return TAB_COUNT
    }

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> HomeFeed.newInstance(position,Constants.HOME_FEED)
            1 -> PeopleFragment.newInstance("","")
            2 -> SettingsFragment.newInstance("","")
            else -> PlaceholderFragment.newInstance("","")
        }
    }
}
