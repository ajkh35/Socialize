package com.socialize.socialize.Activities

import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.content.ContextCompat
import android.support.v4.view.ViewPager
import android.support.v7.widget.Toolbar
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.socialize.socialize.R
import com.socialize.socialize.Adapters.PagerAdapter
import com.socialize.socialize.Socialize
import com.socialize.socialize.Utilities.Constants
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    private lateinit var mPager : ViewPager
    private lateinit var mTabs : TabLayout
    private lateinit var mFirebaseDatabase: FirebaseDatabase
    private var mUsersList: JSONObject? = null

    companion object {
        var mUserName: String = ""
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.statusBarColor = ContextCompat.getColor(this, R.color.colorAccent)
        }
        val toolbar : Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        mUserName = getSharedPreferences(Constants.MY_PREFS, 0).getString(Constants.USERNAME, "")

        mPager = findViewById(R.id.pager)
        mTabs = findViewById(R.id.tabs)

        // Get firebase database
        mFirebaseDatabase = (application as Socialize).getDBInstance()
        val ref = mFirebaseDatabase.getReference("superuser")
        val userRef = ref.child("users")
        userRef.addValueEventListener(object : ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {
                Constants.log_info(Constants.APP_TAG, p0.toString())
            }

            override fun onDataChange(p0: DataSnapshot) {
                val value = p0!!.value.toString()
                mUsersList = JSONObject(value)

                val adapter = PagerAdapter(supportFragmentManager,mUsersList)
                mPager.adapter = adapter
                mTabs.setupWithViewPager(mPager)

                mTabs.getTabAt(0)!!.setIcon(R.drawable.ic_web_black_36dp)
                mTabs.getTabAt(1)!!.setIcon(R.drawable.ic_account_box_black_36dp)
                mTabs.getTabAt(2)!!.setIcon(R.drawable.ic_settings_black_36dp)
            }

        })
    }

    override fun onStart() {
        super.onStart()
        overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_fade_out)
    }
}
