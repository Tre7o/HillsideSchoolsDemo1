package com.trev.hillsideschoolsdemo1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.core.view.isVisible
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var progressBar: ProgressBar
    private lateinit var loginButton: Button
    private lateinit var firebaseUser: FirebaseUser

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        auth = Firebase.auth

    }

    fun signInUser(view: View) {
        loginButton = findViewById(R.id.login)
        loginButton.visibility = View.INVISIBLE //make sign in button invisible

        progressBar = findViewById(R.id.progressBar)
        progressBar.visibility = View.VISIBLE //make the progess bar visible
        val email = findViewById<EditText>(R.id.editEmail)
        val userEmail = email.text.toString()

        val password = findViewById<EditText>(R.id.editPassword)
        val userPassword = password.text.toString()

        val passLayout = findViewById<TextInputLayout>(R.id.passwordInputLayout)
        val emailLayout = findViewById<TextInputLayout>(R.id.emailInputLayout)

        if(userEmail.isNotEmpty() && userPassword.isNotEmpty()) {
            auth.signInWithEmailAndPassword(userEmail, userPassword)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val intent = Intent(this, DrawerBaseActivity::class.java)
                        startActivity(intent)
                        finish()

                        firebaseUser = auth.currentUser!!

                        Toast.makeText(this, "Logged in Successfully", Toast.LENGTH_SHORT).show()
                        loginButton.visibility = View.VISIBLE
                        progressBar.visibility = View.INVISIBLE
                    }
                }.addOnFailureListener { exception ->
                Toast.makeText(
                    this,
                    exception.localizedMessage,
                    Toast.LENGTH_SHORT
                ).show()
                loginButton.visibility = View.VISIBLE
                progressBar.visibility = View.INVISIBLE
            }
        }else if(userEmail.isEmpty()){
            emailLayout.error = "Email address field should not be empty"
            loginButton.visibility = View.VISIBLE
            progressBar.visibility = View.INVISIBLE
        }else if(userPassword.isEmpty()){
            passLayout.error = "Password field should not be empty"
            loginButton.visibility = View.VISIBLE
            progressBar.visibility = View.INVISIBLE
        }else{
            emailLayout.error = "Please fill in all the fields"
            passLayout.error = "Please fill in all the fields"
            loginButton.visibility = View.VISIBLE
            progressBar.visibility = View.INVISIBLE
        }
    }

    fun noAccount(view: View) {
        val intent = Intent(this, SignUpActivity::class.java)
        startActivity(intent)
    }

//    @Deprecated("Deprecated in Java")
//    override fun onBackPressed() {
//        if(firebaseUser == null) super.onBackPressed()
//    }

}