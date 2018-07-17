package com.example.android.hearted

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

import kotlinx.android.synthetic.main.activity_main.*
class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"
    lateinit var mAuth: FirebaseAuth

    override fun onStart() {
        super.onStart()
        //val currentUser = mAuth.currentUser

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mAuth = FirebaseAuth.getInstance()

        email.setOnEditorActionListener() { v, actionId, event ->
            if(actionId == EditorInfo.IME_ACTION_DONE){
                signUp.performClick()
                true
            } else {
                false
            }
        }

        password.setOnEditorActionListener() { v, actionId, event ->
            if(actionId == EditorInfo.IME_ACTION_DONE){
                signUp.performClick()
                true
            } else {
                false
            }
        }
        confirmPassword.setOnEditorActionListener() { v, actionId, event ->
            if(actionId == EditorInfo.IME_ACTION_DONE){
                signUp.performClick()
                true
            } else {
                false
            }
        }

        loginActivityJump.setOnClickListener() {
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
        }

        signUp.setOnClickListener { view ->
            //if all fields have text and passwords are equal.
            if(email.text.toString().trim() == "" || password.text.toString() == "" || confirmPassword.text.toString() == "") {
                Toast.makeText(this, "No email or password entered", Toast.LENGTH_LONG).show()
            } else if ( password.text.toString() != confirmPassword.text.toString()) {
                Toast.makeText(this, "Passwords are not the same", Toast.LENGTH_LONG).show()
            } else {
                createAccount( email.text.toString(), password.text.toString())
            }

        }

    }

    private fun createAccount(email: String, password: String) {
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
            if(it.isSuccessful) {
                Log.d(TAG, "createUserWithEmail:success")
                //val user = mAuth.currentUser
                jumpToHeartViews()
            } else {
                Log.w(TAG, "createUserWithEmail:failure", it.exception)
                Toast.makeText(this, "Failed: 6-digit minimum password", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun jumpToHeartViews() {
        val intent = Intent(this, HeartViews::class.java)
        startActivity(intent)
    }
}
