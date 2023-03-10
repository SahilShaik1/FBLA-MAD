package com.example.fbla_project_s.ui.calendar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fbla_project_s.Event
import com.example.fbla_project_s.EventAdapter
import com.example.fbla_project_s.GlobalVars
import com.example.fbla_project_s.R
import com.example.fbla_project_s.databinding.CalendarScreenBinding
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase

class CalendarFragment : Fragment() {

    private var _binding: CalendarScreenBinding? = null
    private lateinit var recyclerView: RecyclerView
    private lateinit var eventList: ArrayList<Event>
    private lateinit var eventAdapter: EventAdapter
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View {
        // sets up view binding

        _binding = CalendarScreenBinding.inflate(inflater, container, false)
        val root: View = binding.root
        recyclerView = binding.eventRecycler
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.eventRecycler)
        recyclerView.layoutManager = LinearLayoutManager(this.context)
        recyclerView.setHasFixedSize(true)

        eventList = arrayListOf()

        eventAdapter = EventAdapter(eventList)
        recyclerView.adapter = eventAdapter
        EventChangeListener()
    }

    private fun EventChangeListener() {
        //whenever an event is changed, a this will update it to calendar view
        val db = Firebase.firestore
        val username = GlobalVars.Companion.username
        db.collection("Users").document(username).collection("Events")
            .get()
            .addOnSuccessListener {result ->
                    for (data in result){
                        val event: Event? = data.toObject(Event::class.java)
                        if (event != null) {
                            eventList.add(event)
                        }
                    }
                    recyclerView.adapter = EventAdapter(eventList)
                }
            .addOnFailureListener{
                Toast.makeText(requireContext(), "Failure" ,Toast.LENGTH_SHORT).show()
            } /*{ value, error ->
                if (error != null) {
                    Toast.makeText(requireActivity(), error.message.toString(), Toast.LENGTH_SHORT)
                        .show()
                }
                for (dc: DocumentChange in value?.documentChanges!!) {
                    if (dc.type == DocumentChange.Type.ADDED) {
                        eventList.add(dc.document.toObject(Event::class.java))
                    }
                }
                eventAdapter.notifyDataSetChanged()
            }*/
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}