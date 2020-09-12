package com.markskelton.voyager.ui.reports

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.markskelton.voyager.R

class ReportsFragment : Fragment() {

    private lateinit var reportsViewModel: ReportsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        reportsViewModel =
            ViewModelProviders.of(this).get(ReportsViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_reports, container, false)
        val textView: TextView = root.findViewById(R.id.text_dashboard)
        reportsViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }
}
