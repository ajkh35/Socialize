package com.socialize.socialize.Fragments

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.socialize.socialize.Activities.AboutActivity
import com.socialize.socialize.Activities.LoginActivity

import com.socialize.socialize.R
import com.socialize.socialize.Utilities.Constants

private const val POSITION = "position"
private const val TITLE = "title"

class SettingsFragment : Fragment() {
    private var mPosition: Int? = null
    private var mTitle: String? = null
    private lateinit var mLogOut: TextView
    private lateinit var mNotifications: TextView
    private lateinit var mPreferences: TextView
    private lateinit var mAbout: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            mPosition = it.getInt(POSITION)
            mTitle = it.getString(TITLE)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val view: View? = inflater.inflate(R.layout.fragment_settings, container, false)

        mLogOut = view!!.findViewById(R.id.log_out)
        mPreferences = view.findViewById(R.id.preferences)
        mNotifications = view.findViewById(R.id.notifications)
        mAbout = view.findViewById(R.id.about)

        mLogOut.setOnClickListener{
            // set user as logged out
            val editor = activity!!.getSharedPreferences(Constants.MY_PREFS,0).edit()
            editor.putBoolean(Constants.LOGGED_IN,false)
            editor.remove(Constants.USERNAME)
            editor.apply()

            Constants.log_info(Constants.APP_TAG, "User Logged out")

            // Go to Login Screen
            val intent = Intent(activity, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
            activity!!.finish()
        }
        mNotifications.setOnClickListener {
            Toast.makeText(context,getString(R.string.notifications),Toast.LENGTH_SHORT).show()
        }
        mPreferences.setOnClickListener {
            Toast.makeText(context,getString(R.string.preferences),Toast.LENGTH_SHORT).show()
        }
        mAbout.setOnClickListener{
            val intent = Intent(context, AboutActivity::class.java)
            startActivity(intent)
        }

        return view
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: Int, param2: String) =
                SettingsFragment().apply {
                    arguments = Bundle().apply {
                        putInt(POSITION, param1)
                        putString(TITLE, param2)
                    }
                }
    }
}
