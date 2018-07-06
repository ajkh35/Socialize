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

        // Wait for 1.5 seconds
        Waiter(this).execute()
    }

    override fun finish() {
        overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_fade_out)
        super.finish()
    }

    class Waiter(context: Context) : AsyncTask<String,String,String>() {

        @SuppressLint("StaticFieldLeak")
        private val mContext = context

        override fun doInBackground(vararg p0: String?): String {
            Thread.sleep(2000)
            return ""
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)

            // Go to the intended activity
            val intent : Intent =

                    if(mContext.getSharedPreferences(Constants.MY_PREFS, 0).getBoolean(Constants.LOGGED_IN,false)){
                        Intent(mContext, MainActivity::class.java)
                    }else{
                        Intent(mContext, LoginActivity::class.java)
                    }

            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            mContext.startActivity(intent)
            (mContext as SplashActivity).finish()
        }
    }
}
