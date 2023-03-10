package com.example.fbla_project_s.ui.add_event

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CalendarView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.fbla_project_s.GlobalVars
import com.example.fbla_project_s.databinding.FragmentAddEventBinding
import com.google.firebase.Timestamp
import com.google.firebase.firestore.FirebaseFirestore
import com.google.type.Date
import org.checkerframework.checker.units.qual.mm
import java.text.SimpleDateFormat


class add_eventFragment : Fragment() {

    private var _binding: FragmentAddEventBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var calendarView1: CalendarView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val addEventViewModel =
            ViewModelProvider(this).get(About_usViewModel::class.java)

        _binding = FragmentAddEventBinding.inflate(inflater, container, false)
        val root: View = binding.root
        //Save the current Users username to a local variable
        val username = GlobalVars.Companion.username
        binding.confirmBtn.setOnClickListener {
            // gets the description and time from the calendar view and stores it in variables
            val eventDesc = binding.eventInput.text.toString()
            val time : String = binding.calendarView.date.toString()
            //stores a reference of the cloud firestore database in variable db
            val db: FirebaseFirestore = FirebaseFirestore.getInstance()
            //stores event information in a hashmap
            val event = hashMapOf(
                "EventDescription" to eventDesc,
                "Time" to time
            )
            //Goes to the Events collection under the selected user and creates it if needed
            //adds the hashmap of the information to the current users events collection
            db.collection("Users").document(username).collection("Events").add(event)

        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}


