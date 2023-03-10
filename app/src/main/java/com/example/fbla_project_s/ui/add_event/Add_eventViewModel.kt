package com.example.fbla_project_s.ui.add_event

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class Add_eventViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Settings"
    }
    val text: LiveData<String> = _text
}