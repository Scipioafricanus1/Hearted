package com.example.android.hearted

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*


class Login : AppCompatActivity() {
    private val TAG = "MainActivity"
    lateinit var mAuth:FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        mAuth = FirebaseAuth.getInstance()

        tvEmail.setOnEditorActionListener() { v, actionId, event ->
            if(actionId == EditorInfo.IME_ACTION_DONE){
                bLogin.performClick()
                true
            } else {
                false
            }
        }

        tvPassword.setOnEditorActionListener() { v, actionId, event ->
            if(actionId == EditorInfo.IME_ACTION_DONE){
                bLogin.performClick()
                true
            } else {
                false
            }
        }

        tvForgotPassword.setOnClickListener() { //used for later activity.
            val intent = Intent(this, ForgotPasswordActivity::class.java)
            startActivity(intent)
        }
        bSignUp.setOnClickListener {view ->
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        bLogin.setOnClickListener { view ->

            //if you don't have text and password filled in.
            if (tvEmail.text.toString().trim() == "" || tvPassword.text.toString() == "") {
                Toast.makeText(this, "No email or password entered", Toast.LENGTH_LONG).show()
            }
            else {
                confirmEmail(tvEmail.text.toString(), tvPassword.text.toString())
            }
        }
    }

    private fun confirmEmail(email: String, password: String) {
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
            if(it.isSuccessful){
                Log.d(TAG, "logInUserWithEmail:success")
                jumpToHeartViews()
            } else {
                Log.w(TAG, "logInUserWithEmail:failure")
                Toast.makeText(this, "Log In failed", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun jumpToHeartViews() {
        val intent = Intent(this, HeartViews::class.java)
        startActivity(intent)
    }
}
