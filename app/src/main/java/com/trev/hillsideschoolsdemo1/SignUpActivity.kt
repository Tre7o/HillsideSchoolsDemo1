package com.trev.hillsideschoolsdemo1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class SignUpActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var signUpButton: Button
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        auth = Firebase.auth

    }

    fun signUpUser(view: View){
        signUpButton = findViewById(R.id.btnSignup)
        signUpButton.visibility = View.INVISIBLE //make sign in button invisible

        progressBar = findViewById(R.id.progressBar2)
        progressBar.visibility = View.VISIBLE //make the progess bar visible

        val email = findViewById<EditText>(R.id.editEmail)
        val userEmail = email.text.toString()

        val password = findViewById<EditText>(R.id.editPassword)
        val userPassword = password.text.toString()

        val confirmPass = findViewById<EditText>(R.id.confirmPassword)
        val passwordConf = confirmPass.text.toString()

        val confirmLayout = findViewById<TextInputLayout>(R.id.confirmInputLayout)
        val passLayout = findViewById<TextInputLayout>(R.id.passwordInputLayout)
        val emailLayout = findViewById<TextInputLayout>(R.id.emailInputLayout)

        if(userEmail.isNotEmpty() && userPassword.isNotEmpty() && passwordConf.isNotEmpty()){
            if(userPassword == passwordConf){
                auth.createUserWithEmailAndPassword(userEmail, userPassword).addOnCompleteListener{task ->
                    if(task.isSuccessful){
                        val intent = Intent(this, LoginActivity::class.java)

                        startActivity(intent)
                        finish()

                        Toast.makeText(this, "Signed up Successfully", Toast.LENGTH_SHORT).show()
                        signUpButton.visibility = View.VISIBLE
                        progressBar.visibility = View.INVISIBLE
                    }
                }.addOnFailureListener { exception ->
                    Toast.makeText(
                        this,
                        exception.localizedMessage,
                        Toast.LENGTH_SHORT
                    ).show()
                    signUpButton.visibility = View.VISIBLE
                    progressBar.visibility = View.INVISIBLE
                }
            }else{
                confirmLayout.error = "Passwords do not match"
                Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show()
                signUpButton.visibility = View.VISIBLE
                progressBar.visibility = View.INVISIBLE
            }
        }else if(userEmail.isEmpty()){
            emailLayout.error = "Email address field should not be empty"
            signUpButton.visibility = View.VISIBLE
            progressBar.visibility = View.INVISIBLE
        }else if(userPassword.isEmpty()){
            passLayout.error = "Password field should not be empty"
            signUpButton.visibility = View.VISIBLE
            progressBar.visibility = View.INVISIBLE
        }else if(passwordConf.isEmpty()){
            confirmLayout.error = "Password field should not be empty"
            signUpButton.visibility = View.VISIBLE
            progressBar.visibility = View.INVISIBLE
        }else{
            emailLayout.error = "Please fill in all the fields"
            confirmLayout.error = "Please fill in all the fields"
            passLayout.error = "Please fill in all the fields"
            signUpButton.visibility = View.VISIBLE
            progressBar.visibility = View.INVISIBLE
        }
    }

    fun haveAccount(view: View) {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }
}