package com.socialize.socialize.Activities

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.socialize.socialize.R
import com.socialize.socialize.Utilities.Constants

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        Launch().execute(this)
    }

    class Launch : AsyncTask<Context, String, String>() {

        @SuppressLint("StaticFieldLeak")
        private lateinit var mContext : Context

        override fun doInBackground(vararg p0: Context): String {
            mContext = p0[0]
            Thread.sleep(1500)
            return ""
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            val intent : Intent = if(mContext.getSharedPreferences(Constants.MY_PREFS, 0).getBoolean(Constants.LOGGED_IN,false)){
                Intent(mContext, MainActivity::class.java)
            }else{
                Intent(mContext, LoginActivity::class.java)
            }
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            mContext.startActivity(intent)
        }
    }

    override fun finish() {
        overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_fade_out)
        super.finish()
    }
}
