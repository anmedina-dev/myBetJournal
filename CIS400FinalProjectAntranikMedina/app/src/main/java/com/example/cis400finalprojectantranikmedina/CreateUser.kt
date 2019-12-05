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
import kotlinx.android.synthetic.main.activity_create_user.*
import kotlinx.android.synthetic.main.activity_login.*

class CreateUser : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    lateinit var email: String
    lateinit var password: String
    lateinit var username: String
    lateinit var birthdate: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_user)

        auth = FirebaseAuth.getInstance()


        CreateBtn.setOnClickListener{
            email = emailCreate.text.toString()
            password = passwordCreate.text.toString()

            if(TextUtils.isEmpty(email)||TextUtils.isEmpty(password)||!email.contains("@")){
                Toast.makeText(baseContext, "Create failed: The username or password is invalid",Toast.LENGTH_SHORT).show()
            }else{
                createUser()
            }
        }
    }

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

    fun startActivity(){
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}
