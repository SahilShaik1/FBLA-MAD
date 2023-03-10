package com.example.fbla_project_s.ui.messages

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.fbla_project_s.databinding.FragmentMessagesBinding
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MessagesFragment : Fragment() {

    private var _binding: FragmentMessagesBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val messagesViewModel =
            ViewModelProvider(this).get(MessagesViewModel::class.java)
        _binding = FragmentMessagesBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.titleMessages
        messagesViewModel.text.observe(viewLifecycleOwner) {
            textView.text = "Who do you want to message?"
        }


        _binding!!.SendEmail.setOnClickListener {
            val db = Firebase.firestore
            val recipientUserName = binding.Recipient.text.toString()
            db.collection("Users").document(recipientUserName).get().addOnSuccessListener { values ->
                if(values != null){
                    val email = values.data!!.getValue("Email").toString()
                    val subject = binding.MessageTitle.text.toString()
                    val message = binding.MessageDescription.text.toString()

                    // Defines intent of email and get info to send to
                    val intent = Intent(Intent.ACTION_SEND)
                    //add three fields to intent using putExtra function
                    intent.putExtra(Intent.EXTRA_EMAIL, arrayOf(email))
                    intent.putExtra(Intent.EXTRA_SUBJECT, subject)
                    intent.putExtra(Intent.EXTRA_TEXT, message)
                    // set type of intent
                    intent.type = "message/rfc822"
                    //sends the email!!
                    startActivity(Intent.createChooser(intent, "Choose an Email client :"))
                }
                else{
                    Toast.makeText(requireActivity(), "User not Found", Toast.LENGTH_SHORT).show();
                }
            }.addOnFailureListener {
                Toast.makeText(requireActivity(), "User not Found", Toast.LENGTH_SHORT).show();
            }



        }
        return root
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}