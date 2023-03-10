package com.example.fbla_project_s.ui.settings

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.fbla_project_s.databinding.ActivitySettingsBinding
import com.example.fbla_project_s.databinding.ActivitySignUpBinding

class SettingsActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySettingsBinding;
    override fun onCreate(savedInstanceState: Bundle?) {
        // starts the activity
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.confirmBtn.setOnClickListener{
            // variables needed for email
            val email = "schoolsunited479@gmail.com"
            val subject = "Bug Found"
            val message = binding.bugInput.text.toString()
            // Defines intent of email and get info to send to
            val intent = Intent(Intent.ACTION_SEND)
            //add three fields to intent using putExtra function
            intent.putExtra(Intent.EXTRA_EMAIL, email)
            intent.putExtra(Intent.EXTRA_SUBJECT,subject)
            intent.putExtra(Intent.EXTRA_TEXT,message)
            // set type of intent
            intent.type = "message/rfc822"
            //sends the email!!
            startActivity(Intent.createChooser(intent, "Choose an Email client :"))
        }
    }
}