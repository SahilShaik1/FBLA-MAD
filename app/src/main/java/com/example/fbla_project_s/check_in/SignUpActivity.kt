package com.example.fbla_project_s.check_in

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Button
import android.widget.Toast
import com.example.fbla_project_s.GlobalVars
import com.example.fbla_project_s.ui.home.HomeActivity2
import com.example.fbla_project_s.R
import com.example.fbla_project_s.databinding.ActivitySignUpBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding;


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        // initialize firebase
        val auth : FirebaseAuth = FirebaseAuth.getInstance()
        // to login page
        val loginButton: Button = findViewById(R.id.toLoginBtn);
        val db = Firebase.firestore
        loginButton.setOnClickListener {
            //To get to login screen
            val intent = Intent(this@SignUpActivity, LoginCode::class.java)
            startActivity(intent)
        }
        // creates user
        binding.confirmBtn.setOnClickListener {
            //get all info
            val userName = binding.usernameInput.text;
            val email = binding.emailInput.text;
            val password = binding.passwordInput.text;
            val confirmPassword = binding.confirmPasswordInput.text;
            GlobalVars.Companion.email = email.toString()
            GlobalVars.Companion.password = password.toString()
            GlobalVars.Companion.username = userName.toString()
            if (TextUtils.isEmpty(email)||TextUtils.isEmpty(password)||TextUtils.isEmpty(confirmPassword)) {
                Toast.makeText(this@SignUpActivity, "Enter all fields.", Toast.LENGTH_SHORT).show();
            }
            else if(password.toString() != confirmPassword.toString()){
                Toast.makeText(this@SignUpActivity, "Password and confirm password don't match.", Toast.LENGTH_SHORT).show();
            }
            else {
                //save to database
                saveFirestore(username = userName.toString(), email = email.toString(), password = password.toString())
                val intent1 = Intent(this@SignUpActivity, HomeActivity2::class.java);
                startActivity(intent1)

            }

        }
    }
    private fun saveFirestore(username: String, email: String, password :String){
        //easy way to save data
        val db = Firebase.firestore
        val userInfo = hashMapOf(
            "Email" to email,
            "Username" to username,
            "Password" to password
        )
        db.collection("Users").document(username).set(userInfo)
    }

}