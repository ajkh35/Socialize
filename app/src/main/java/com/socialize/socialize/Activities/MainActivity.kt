package com.socialize.socialize.Activities

import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.content.ContextCompat
import android.support.v4.view.ViewPager
import android.support.v7.widget.Toolbar
import android.util.Log
import com.google.firebase.database.*
import com.socialize.socialize.R
import com.socialize.socialize.Adapters.PagerAdapter

class MainActivity : AppCompatActivity() {

    private lateinit var mPager : ViewPager
    private lateinit var mTabs : TabLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.statusBarColor = ContextCompat.getColor(this, R.color.colorAccent)
        }
        val toolbar : Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        mPager = findViewById(R.id.pager)
        mTabs = findViewById(R.id.tabs)

        val adapter = PagerAdapter(supportFragmentManager)
        mPager.adapter = adapter
        mTabs.setupWithViewPager(mPager)

        mTabs.getTabAt(0)!!.setIcon(R.drawable.ic_web_black_36dp)
        mTabs.getTabAt(1)!!.setIcon(R.drawable.ic_account_box_black_36dp)
        mTabs.getTabAt(2)!!.setIcon(R.drawable.ic_settings_black_36dp)
    }

    override fun onStart() {
        super.onStart()
        overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_fade_out)
    }
}
