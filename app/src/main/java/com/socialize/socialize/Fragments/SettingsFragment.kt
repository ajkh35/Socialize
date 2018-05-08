package com.socialize.socialize.Fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.socialize.socialize.R

private const val POSITION = "position"
private const val TITLE = "title"

class SettingsFragment : Fragment() {
    private var mPosition: Int? = null
    private var mTitle: String? = null

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
        return inflater.inflate(R.layout.fragment_settings, container, false)
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment SettingsFragment.
         */
        // TODO: Rename and change types and number of parameters
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
