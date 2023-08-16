package com.trev.hillsideschoolsdemo1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_HillsideSchoolsDemo1_SplashScreen) //set splash screen b4 main activity
        setContentView(R.layout.activity_main)
    }

    fun accountExists(view: View) {
        val intent = Intent(this, LoginActivity::class.java)
//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        startActivity(intent)
        finish()
    }


    fun facebookSign(view: View) {
        //code for connecting user using facebook
        Toast.makeText(this, "This button is disabled", Toast.LENGTH_SHORT).show()
    }
}