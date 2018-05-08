package com.socialize.socialize.Activities

import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.widget.Button
import com.socialize.socialize.R
import com.socialize.socialize.Utilities.Constants

class LoginActivity : AppCompatActivity() {

    private lateinit var mLogin : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.statusBarColor = ContextCompat.getColor(this, R.color.colorAccent)
        }

        mLogin = findViewById(R.id.login)
        mLogin.setOnClickListener {
            val editor = getSharedPreferences(Constants.MY_PREFS,0).edit()
            editor.putBoolean(Constants.LOGGED_IN,true)
            editor.apply()
        }
    }

    override fun onStart() {
        overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_fade_out)
        super.onStart()
    }
}
