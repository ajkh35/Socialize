package com.socialize.socialize.Adapters

import android.support.v4.app.FragmentManager
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentPagerAdapter
import android.widget.TextView
import com.socialize.socialize.Fragments.LoginFragment
import com.socialize.socialize.Fragments.PlaceholderFragment
import com.socialize.socialize.Fragments.SignupFragment
import com.socialize.socialize.R
import com.socialize.socialize.Utilities.Constants

class LoginPagerAdapter(fm: FragmentManager?): FragmentPagerAdapter(fm) {

    private val TAB_COUNT = 2

    override fun getItem(position: Int): Fragment {
        return when(position){
            0 -> LoginFragment.newInstance(position,Constants.LOGIN)
            1 -> SignupFragment.newInstance(position,Constants.SIGNUP)
            else -> PlaceholderFragment.newInstance("","")
        }
    }

    override fun getCount(): Int {
        return TAB_COUNT
    }
}
