package com.example.fbla_project_s.check_in

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.fbla_project_s.GlobalVars
import com.example.fbla_project_s.ui.home.HomeActivity2
import com.example.fbla_project_s.R
import com.example.fbla_project_s.ui.calendar.CalendarFragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase



class LoginCode : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_screen)
        //initializes the text input, so we can retrieve data
        val emailIn: EditText = findViewById(R.id.emailInput);
        val passwordIn: EditText = findViewById(R.id.passwordInput);
        val usernameIn: EditText = findViewById(R.id.usernameInputLogin)
        var test = CalendarFragment()

        val db = Firebase.firestore

        // to sign up page
        val confirmButton: Button = findViewById(R.id.confirmBtn);
        confirmButton.setOnClickListener {
            // gets input
            val email = emailIn.text.toString()
            val password = passwordIn.text.toString()
            val username = usernameIn.text.toString()
            //signs up
            try {
                db.collection("Users").document(username)
                    .get()
                    .addOnSuccessListener { values ->
                        if (values.data != null) {
                            val passwordFound = values.data!!.getValue("Password").toString()
                            val emailFound = values.data!!.getValue("Email").toString()
                            if (password == passwordFound && email == emailFound) {
                                //Successful Login
                                GlobalVars.Companion.email = email
                                GlobalVars.Companion.password = password
                                GlobalVars.Companion.username = username
                                Toast.makeText(this@LoginCode, "User Found", Toast.LENGTH_SHORT)
                                    .show();
                                val intent2 = Intent(this@LoginCode, HomeActivity2::class.java)
                                startActivity(intent2)
                            }
                        } else {
                            Toast.makeText(this@LoginCode, "User Not Found", Toast.LENGTH_SHORT)
                                .show()
                        }
                    }
            } catch (e: java.lang.Exception){
                Toast.makeText(this@LoginCode, "User Not Found", Toast.LENGTH_SHORT)
                    .show();
            }
        }
        // to sign up page
        val signupbutton: Button = findViewById(R.id.toSignUpBtn);
        signupbutton.setOnClickListener {
            val intent = Intent(this@LoginCode, SignUpActivity::class.java)
            startActivity(intent)
        }
        // to forgot password page
        val fpbutton : Button = findViewById(R.id.forgotPasswordBtn);
        fpbutton.setOnClickListener {
            val intent1 = Intent(this@LoginCode, ForgotPasswordActivity::class.java)
            startActivity(intent1)
        }
    }

}

