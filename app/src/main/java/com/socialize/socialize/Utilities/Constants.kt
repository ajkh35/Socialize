package com.socialize.socialize.Utilities

import android.util.Log

class Constants {
    companion object {
        const val MY_PREFS = "my_prefs"
        const val LOGGED_IN = "logged_in"
        const val HOME_FEED = "home_feed"
        const val PROFILE = "profile"
        const val SETTINGS = "settings"
        const val LOGIN = "login"
        const val SIGNUP = "signup"
        const val APP_TAG = "socialize"
        const val FRIENDS = "friends"
        const val USERNAME = "username"
        const val DEBUG = true
        const val ISADMIN = "isadmin"

        fun log_info(tag: String,message: String) {
            if(DEBUG){
                Log.i(tag,message)
            }
        }
    }
}
