package com.example.cis400finalprojectantranikmedina

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    //private var mAuthTask: UserLoginTask? = null
    private lateinit var auth: FirebaseAuth

    lateinit var email: String
    lateinit var password: String

    override
    fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        auth = FirebaseAuth.getInstance()

        login.setOnClickListener {
            email=edit_text_email.text.toString()
            password=edit_text_password.text.toString()

            if(TextUtils.isEmpty(email)&&TextUtils.isEmpty(password)){
                Toast.makeText(baseContext, "Login failed: The username or password is empty",Toast.LENGTH_SHORT).show()
            }else{
                login()
            }
        }

        signUp.setOnClickListener {
            val intent = Intent(this, CreateUser::class.java)
            startActivity(intent)
    }
    }

    // create a new user in Firebase
    fun createUser() {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    //val user = auth.currentUser
                    startActivity()
                } else {
                    Log.w("aaa", "createUserWithEmail:failure", task.exception)
                    // If sign in fails, display a message to the user.
                    Toast.makeText(baseContext, "Authentication failed: "+task.exception,
                        Toast.LENGTH_SHORT).show()
                }
                // ...
            }
    }

    //login
    fun login() {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    //val user = auth.currentUser
                    startActivity()
                } else {
                    Log.w("aaa", "signInWithEmail:failure", task.exception)
                    // If sign in fails, display a message to the user.
                    Toast.makeText(baseContext, "Authentication failed: "+task.exception,
                        Toast.LENGTH_SHORT).show()
                }
                // ...
            }
    }

    fun startActivity(){
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

}
