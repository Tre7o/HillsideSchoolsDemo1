package com.trev.hillsideschoolsdemo1

import android.app.Application
import android.content.Intent
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class Session: Application() {
    override fun onCreate() {
        super.onCreate()

        val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
        val firebaseUser: FirebaseUser? = firebaseAuth.currentUser

//        val user = Firebase.auth.currentUser

        if(firebaseUser != null){
            val intent = Intent(this, DrawerBaseActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        }
    }
}