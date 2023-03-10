package com.example.fbla_project_s.ui.settings

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.fbla_project_s.GlobalVars
import com.example.fbla_project_s.check_in.LoginCode
import com.example.fbla_project_s.databinding.FragmentSettingsBinding
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

/*Users will access settings*/

class SettingsFragment : Fragment() {

    private var _binding: FragmentSettingsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val settingsViewModel =
            ViewModelProvider(this).get(SettingsViewModel::class.java)
        val db = Firebase.firestore
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        val root: View = binding.root
        //Sends info t gmail
        binding.confirmBtn.setOnClickListener{
            val email = "schoolsunited479@gmail.com"
            val subject = "New Bug"
            val message = binding.bugInput.text.toString()
            sendEmail(email, subject, message)
        }
        binding.signOutButton.setOnClickListener {
            //Making an intent to go to the login screen and going there
            val intent1= Intent(requireActivity(), LoginCode::class.java);
            startActivity(intent1);
        }
        binding.deleteAccountButton.setOnClickListener {
            //Getting username from global variable file
            val username = GlobalVars.Companion.username
            //Deleting the account found under the username in the database
            db.collection("Users").document(username).delete()
            //Making an intent to go to the login screen and going there
            val intent = Intent(requireActivity(), LoginCode::class.java)
            startActivity(intent)
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    private fun sendEmail(recipient: String, subject: String, message: String) {
        /*ACTION_SEND action to launch an email client installed on your Android device.*/
        val mIntent = Intent(Intent.ACTION_SEND)
        /*To send an email you need to specify mailto: as URI using setData() method
        and data type will be to text/plain using setType() method*/
        mIntent.data = Uri.parse("mailto:")
        mIntent.type = "text/plain"
        // put recipient email in intent
        /* recipient is put as array because you may wanna send email to multiple emails
           so enter comma(,) separated emails, it will be stored in array*/
        mIntent.putExtra(Intent.EXTRA_EMAIL, arrayOf(recipient))
        //put the Subject in the intent
        mIntent.putExtra(Intent.EXTRA_SUBJECT, subject)
        //put the message in the intent
        mIntent.putExtra(Intent.EXTRA_TEXT, message)


        try {
            //start email intent
            startActivity(Intent.createChooser(mIntent, "Choose Email Client..."))
        }
        catch (e: Exception) {
            //if any thing goes wrong for example no email client application or any exception
            //get and show exception message
            Toast.makeText(requireActivity(), e.message,Toast.LENGTH_SHORT).show()
        }
    }
}