package com.socialize.socialize.Fragments

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckedTextView
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.*
import com.socialize.socialize.Activities.MainActivity
import com.socialize.socialize.R
import com.socialize.socialize.Socialize
import com.socialize.socialize.Utilities.Constants
import org.json.JSONObject

private const val POSITION = "position"
private const val TITLE = "title"

class LoginFragment : Fragment() {

    private var mPosition: Int? = null
    private var mTitle: String? = null
    private lateinit var mLogin : Button
    private lateinit var mUserName: EditText
    private lateinit var mPassword: EditText
    private lateinit var mFirebaseDatabase: FirebaseDatabase
    private lateinit var mUsers: JSONObject
    private lateinit var mAdminChecked: CheckedTextView
    private var mAdmin: JSONObject? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            mPosition = it.getInt(POSITION)
            mTitle = it.getString(TITLE)
        }
        mUsers = JSONObject()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_login,container,false)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            activity!!.window.statusBarColor = ContextCompat.getColor(context!!, R.color.colorAccent)
        }

        // Get users from firebase database
        mFirebaseDatabase = (activity!!.application as Socialize).getDBInstance()
        val ref = mFirebaseDatabase.getReference("superuser")
        val userRef = ref.child("users")
        userRef.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError?) {
                Constants.log_info(Constants.APP_TAG, p0.toString())
            }

            override fun onDataChange(p0: DataSnapshot?) {
                Constants.log_info(Constants.APP_TAG, "Got user data")
                val value = p0?.value.toString()
                mUsers = JSONObject(value)
            }
        })

        // Injecting views
        mLogin = view.findViewById(R.id.login)
        mUserName = view.findViewById(R.id.edit_user_name)
        mPassword = view.findViewById(R.id.edit_password)
        mAdminChecked = view.findViewById(R.id.admin)

        // Adding listeners
        mUserName.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(p0: Editable?) {
                if(mUserName.text.isNullOrBlank()){
                    Constants.log_info(Constants.APP_TAG, "Invalid entry")
                    mUserName.error = "Invalid entry"
                }
            }

        })
        mPassword.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                if(mPassword.text.isNullOrBlank() || mPassword.text.length < 6){
                    Constants.log_info(Constants.APP_TAG, "Invalid entry")
                    mPassword.error = "Invalid entry"
                }
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

        })
        mAdminChecked.setOnClickListener {
            if(mAdminChecked.isChecked){
                mAdminChecked.isChecked = false
            }else{
                mAdminChecked.isChecked = true
                if(mAdmin == null){
                    val adminRef = ref.child("admin")
                    adminRef.addValueEventListener(object : ValueEventListener{
                        override fun onCancelled(p0: DatabaseError?) {
                            Constants.log_info(Constants.APP_TAG, p0.toString())
                        }

                        override fun onDataChange(p0: DataSnapshot?) {
                            Constants.log_info(Constants.APP_TAG, "Got Admin data")
                            mAdmin = JSONObject(p0?.value.toString())
                        }

                    })
                }
            }
        }
        mLogin.setOnClickListener {
            if(isUserValid(mUserName.text.toString(),mPassword.text.toString())){

                Constants.log_info(Constants.APP_TAG, "User Logged in")

                // set user as logged in
                val editor = activity!!.getSharedPreferences(Constants.MY_PREFS,0).edit()
                editor.putBoolean(Constants.LOGGED_IN,true)
                editor.apply()

                // Go to Main Screen
                val intent = Intent(activity, MainActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                startActivity(intent)
                activity!!.finish()
            }else{
                Toast.makeText(activity,getString(R.string.invalid_user), Toast.LENGTH_SHORT).show()
            }
        }

        return view
    }

    /**
     * Check to see if the user's details are valid
     */
    fun isUserValid(userName: String?,password: String?): Boolean {
        var isValid = false
        if(mAdminChecked.isChecked){ // Check for admin
            if(mAdmin!!.has(userName)){
                val adminDetails = mAdmin!!.get(userName) as JSONObject
                if(adminDetails.get("password").equals(password)){
                    isValid = true
                }
            }
        }else{ // otherwise check for a regular user
            if(mUsers.has(userName)){
                val userDetails = mUsers.get(userName) as JSONObject
                if(userDetails.get("password").equals(password)){
                    isValid = true
                }
            }
        }
        return isValid
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: Int, param2: String) =
                LoginFragment().apply {
                    arguments = Bundle().apply {
                        putInt(POSITION, param1)
                        putString(TITLE, param2)
                    }
                }
    }
}
