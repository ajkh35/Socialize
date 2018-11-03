package com.socialize.socialize

import android.app.Application
import android.content.Context
import android.os.AsyncTask
import com.google.firebase.FirebaseApp
import com.google.firebase.database.FirebaseDatabase

class Socialize : Application() {

    private lateinit var mFirebaseDatabase: FirebaseDatabase

    override fun onCreate() {
        super.onCreate()
        initFirebase(this)
    }

    fun getDBInstance(): FirebaseDatabase {
        return mFirebaseDatabase
    }

    fun initFirebase(context: Context?) {
        FirebaseApp.initializeApp(this)
        mFirebaseDatabase = FirebaseDatabase.getInstance()
    }
}
