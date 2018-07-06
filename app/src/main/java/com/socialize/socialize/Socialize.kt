package com.socialize.socialize

import android.app.Application
import com.google.firebase.FirebaseApp
import com.google.firebase.database.FirebaseDatabase

class Socialize : Application() {

    private lateinit var mFirebaseDatabase: FirebaseDatabase

    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this)
        mFirebaseDatabase = FirebaseDatabase.getInstance()
    }

    fun getDBInstance(): FirebaseDatabase {
        return mFirebaseDatabase
    }
}
