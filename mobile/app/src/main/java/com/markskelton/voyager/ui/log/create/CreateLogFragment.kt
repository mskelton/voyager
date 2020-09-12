package com.markskelton.voyager.ui.log.create

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.markskelton.voyager.R
import com.markskelton.voyager.databinding.FragmentCreateLogBinding

class CreateLogFragment : Fragment() {
    private lateinit var binding: FragmentCreateLogBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCreateLogBinding.inflate(layoutInflater)

        initializeDropdown(binding.project)
        initializeDropdown(binding.vehicle)
        initializeCreateButton()

        return binding.root
    }

    private fun initializeDropdown(dropdown: AutoCompleteTextView) {
        val items = listOf("Material", "Design", "Components", "Android")
        val adapter = ArrayAdapter(requireContext(), R.layout.list_item, items)
        dropdown.setAdapter(adapter)
    }

    private fun initializeCreateButton() {
        binding.createButton.setOnClickListener {
            findNavController().navigateUp()
        }
    }
}
