package com.example.fbla_project_s.check_in

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.example.fbla_project_s.GlobalVars.Companion.password

import com.example.fbla_project_s.R
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.util.Properties
import javax.mail.Message
import javax.mail.Session
import javax.mail.Transport
import javax.mail.internet.InternetAddress
import javax.mail.internet.MimeMessage
import java.util.*
import javax.mail.*

import javax.mail.internet.*

class ForgotPasswordActivity : AppCompatActivity() {
    private fun putIfMissing(props: Properties, key: String, value: String) {
        if (!props.containsKey(key)) {
            props[key] = value
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)
        val backbutton: Button = findViewById(R.id.backBtn)
        val emailIn: EditText = findViewById(R.id.emailInput)
        val db = Firebase.firestore
        val confirmBtn: Button = findViewById(R.id.confirmBtn)
        backbutton.setOnClickListener {
            val intent = Intent(this@ForgotPasswordActivity, LoginCode::class.java)
            startActivity(intent)
        }
    }
}