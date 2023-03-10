package com.example.fbla_project_s.ui.about_us

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CalendarView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.fbla_project_s.databinding.FragmentAboutUsBinding
import com.example.fbla_project_s.ui.add_event.About_usViewModel


class about_usFragment : Fragment() {

    private var _binding: FragmentAboutUsBinding? = null

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
        //send user to selected pages
        _binding = FragmentAboutUsBinding.inflate(inflater, container, false)
        val root: View = binding.root
        binding.instagram.setOnClickListener{
            val uri = "https://www.instagram.com/southforsythhs/?hl=en"
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(uri))
            requireContext().startActivity(intent)
        }
        binding.twitter.setOnClickListener{
            val uri = "https://twitter.com/SouthForsythHS"
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(uri))
            requireContext().startActivity(intent)
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}


