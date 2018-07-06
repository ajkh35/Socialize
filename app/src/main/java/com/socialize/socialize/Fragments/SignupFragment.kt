package com.socialize.socialize.Fragments

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.FirebaseDatabase
import com.socialize.socialize.Activities.MainActivity
import com.socialize.socialize.R
import com.socialize.socialize.Socialize
import com.socialize.socialize.Utilities.Constants
import com.socialize.socialize.models.Person
import java.util.regex.Pattern

private const val POSITION = "position"
private const val TITLE = "title"

class SignupFragment : Fragment() {

    private var mPosition: Int? = null
    private var mTitle: String? = null
    private lateinit var mSignup: Button
    private lateinit var mEmail: EditText
    private lateinit var mPassword: EditText
    private lateinit var mUserName: EditText
    private lateinit var mFirstName: EditText
    private lateinit var mLastName: EditText
    private lateinit var mFirebaseDatabase: FirebaseDatabase


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            mPosition = it.getInt(POSITION)
            mTitle = it.getString(TITLE)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.activity_signup,container,false)

        // Inject views
        mFirstName = view.findViewById(R.id.edit_first_name)
        mLastName = view.findViewById(R.id.edit_last_name)
        mUserName = view.findViewById(R.id.edit_user_name)
        mPassword = view.findViewById(R.id.edit_password)
        mEmail = view.findViewById(R.id.edit_email)
        mSignup = view.findViewById(R.id.signup)

        // Add listeners
        mFirstName.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                if(mFirstName.text.isNullOrBlank()){
                    mFirstName.error = "Invalid entry"
                }
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

        })
        mLastName.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                if(mLastName.text.isNullOrBlank()){
                    mLastName.error = "Invalid entry"
                }
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

        })
        mUserName.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                if(mUserName.text.isNullOrBlank()){
                    mUserName.error = "Invalid entry"
                }
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if(mUserName.text.isNullOrBlank()){
                    mUserName.error = "Required Field"
                }
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

        })
        mEmail.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                if(mEmail.text.isNullOrBlank() || !Pattern.compile("^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]|[\\w-]{2,}))@"
                                + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                                + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9]))|"
                                + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$").matcher(mEmail.text.toString()).matches()){
                    mEmail.error = "Invalid entry"
                }
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if(mEmail.text.isNullOrBlank()){
                    mEmail.error = "Required field"
                }
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

        })
        mPassword.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                if(mPassword.text.isNullOrBlank() || mPassword.text.length < 6){
                    mPassword.error = "Invalid entry"
                }
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if(mPassword.text.isNullOrBlank()){
                    mPassword.error = "Required field"
                }
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

        })
        mSignup.setOnClickListener {
            if(validateFields())  {
                // Create person map
                val person = Person(
                        mFirstName.text.toString(),
                        mLastName.text.toString(),
                        "some",
                        mUserName.text.toString(),
                        mEmail.text.toString(),
                        mPassword.text.toString())
                val user = HashMap<String, Person>()
                user.put(person.userName, person)

                // Add the person map to database
                mFirebaseDatabase = (activity!!.application as Socialize).getDBInstance()
                val dbRef = mFirebaseDatabase.getReference("superuser")
                dbRef.child("users").child(person.userName).setValue(person)

                Constants.log_info(Constants.APP_TAG, "Signed up")

                // set user as logged in
                val editor = activity!!.getSharedPreferences(Constants.MY_PREFS,0).edit()
                editor.putBoolean(Constants.LOGGED_IN,true)
                editor.apply()

                // Go to Main Screen
                val intent = Intent(activity, MainActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                startActivity(intent)
                activity!!.finish()
            } else{
                Toast.makeText(activity,getString(R.string.validate_fields), Toast.LENGTH_SHORT).show()
            }
        }

        return view
    }

    /**
     * Validate user inputs
     */
    private fun validateFields(): Boolean {
        return mFirstName.error == null ||
                mLastName.error == null ||
                mUserName.error == null ||
                mEmail.error == null ||
                mPassword.error == null
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: Int, param2: String) =
                SignupFragment().apply {
                    arguments = Bundle().apply {
                        putInt(POSITION, param1)
                        putString(TITLE, param2)
                    }
                }
    }
}
