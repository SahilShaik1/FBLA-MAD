package com.example.fbla_project_s

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.*
import kotlin.collections.ArrayList

class EventAdapter(private val eventList:ArrayList<Event>) : RecyclerView.Adapter<EventAdapter.EventViewHolder>() {
    //to get information to create an event object
    class EventViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        val eventDesc: TextView = itemView.findViewById(R.id.eventDescLabel)
        val eventDate: TextView = itemView.findViewById(R.id.eventDateLabel)
    }

    //creates a view holder to hold the event
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.event_item, parent, false)
        return EventViewHolder(itemView)
    }
    //sets text to list of all events
    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        holder.eventDesc.text = eventList[position].EventDescription.toString()
        holder.eventDate.text = eventList[position].Time.toString()
    }
    //returns size of the eventList
    override fun getItemCount(): Int {
        return eventList.size
    }
}