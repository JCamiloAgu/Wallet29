package com.camilo.wallet29.ui.configurations

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.camilo.wallet29.R

class ConfigurationsFragment : Fragment() {

    private lateinit var configurationsViewModel: ConfigurationsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        configurationsViewModel =
            ViewModelProviders.of(this).get(ConfigurationsViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_configurations, container, false)
        val textView: TextView = root.findViewById(R.id.text_gallery)
        configurationsViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }
}
