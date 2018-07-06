package com.socialize.socialize.Fragments

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.socialize.socialize.Activities.LoginActivity

import com.socialize.socialize.R
import com.socialize.socialize.Utilities.Constants

private const val POSITION = "position"
private const val TITLE = "title"

class SettingsFragment : Fragment() {
    private var mPosition: Int? = null
    private var mTitle: String? = null
    private lateinit var mLogOut: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            mPosition = it.getInt(POSITION)
            mTitle = it.getString(TITLE)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view: View? = inflater.inflate(R.layout.fragment_settings, container, false)

        mLogOut = view!!.findViewById(R.id.log_out)
        mLogOut.setOnClickListener{
            // set user as logged out
            val editor = activity!!.getSharedPreferences(Constants.MY_PREFS,0).edit()
            editor.putBoolean(Constants.LOGGED_IN,false)
            editor.apply()

            Constants.log_info(Constants.APP_TAG, "User Logged out")

            // Go to Login Screen
            val intent = Intent(activity, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
            activity!!.finish()
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
