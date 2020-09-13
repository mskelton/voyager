package com.markskelton.voyager.ui.log.create

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import androidx.navigation.fragment.findNavController
import com.markskelton.voyager.R
import com.markskelton.voyager.databinding.FragmentCreateLogBinding
import com.markskelton.voyager.db.models.Log
import com.markskelton.voyager.db.models.NamedEntity
import com.markskelton.voyager.repositories.LogRepository
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CreateLogFragment : Fragment() {
    private lateinit var binding: FragmentCreateLogBinding
    private val viewModel: CreateLogViewModel by viewModels()

    @Inject
    lateinit var logRepository: LogRepository

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCreateLogBinding.inflate(layoutInflater)

        initializeDropdown(binding.project, viewModel.projects) {
            viewModel.selectedProject = it
        }

        initializeDropdown(binding.vehicle, viewModel.vehicles) {
            viewModel.selectedVehicle = it
        }

        initializeCreateButton()

        return binding.root
    }

    private fun <T : NamedEntity> initializeDropdown(
        dropdown: AutoCompleteTextView,
        entities: LiveData<List<T>>,
        onItemClickListener: (item: T) -> Unit
    ) {
        entities.observe(viewLifecycleOwner) { res ->
            val items = res.map { it.name }
            val adapter = ArrayAdapter(requireContext(), R.layout.list_item, items)

            dropdown.setAdapter(adapter)
            dropdown.setOnItemClickListener { _, _, position, _ ->
                onItemClickListener(res[position])
            }
        }
    }

    private fun initializeCreateButton() {
        binding.createButton.setOnClickListener {
            val project = viewModel.selectedProject
            val vehicle = viewModel.selectedVehicle
            val startMileage = binding.startMileage.text

            if (project != null && vehicle != null) {
                val log = Log(0, project.id, vehicle.id, startMileage.toString().toInt())
                logRepository.createLog(log)
            }

            findNavController().navigateUp()
        }
    }
}
