package com.socialize.socialize.Activities

import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v4.view.ViewPager
import com.socialize.socialize.Adapters.LoginPagerAdapter
import com.socialize.socialize.R
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.statusBarColor = ContextCompat.getColor(this, R.color.colorAccent)
        }

        val pager = login_pager
        val swipeText = dynamic_swipe
        pager.adapter = LoginPagerAdapter(supportFragmentManager)
        pager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener{
            override fun onPageScrollStateChanged(state: Int) {

            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                if(position == 0){
                    swipeText.setText(R.string.swipe_to_signup)
                }else if(position == 1){
                    swipeText.setText(R.string.swipe_login)
                }else{
                    swipeText.setText(R.string.something_went_wrong)
                }
            }

            override fun onPageSelected(position: Int) {
            }

        })
    }

    override fun onStart() {
        overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_fade_out)
        super.onStart()
    }

    override fun finish() {
        overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_fade_out)
        super.finish()
    }
}
