package com.markskelton.voyager.ui.vehicles

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.markskelton.voyager.AppExecutors
import com.markskelton.voyager.databinding.FragmentVehiclesBinding
import com.markskelton.voyager.repositories.VehicleRepository
import com.markskelton.voyager.ui.SimpleListAdapter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class VehiclesFragment : Fragment() {

    private lateinit var binding: FragmentVehiclesBinding
    private lateinit var viewAdapter: SimpleListAdapter
    private lateinit var viewManager: LinearLayoutManager
    private val viewModel: VehicleListViewModel by viewModels()

    @Inject
    lateinit var vehicleRepository: VehicleRepository

    @Inject
    lateinit var appExecutors: AppExecutors

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentVehiclesBinding.inflate(layoutInflater)

        initializeRecyclerView()
        initializeAddButton()

        return binding.root
    }

    private fun initializeRecyclerView() {
        viewManager = LinearLayoutManager(context)
        viewAdapter = SimpleListAdapter()

        binding.vehicleRecyclerView.apply {
            // use this setting to improve performance if you know that changes
            // in content do not change the layout size of the RecyclerView
            setHasFixedSize(true)

            layoutManager = viewManager
            adapter = viewAdapter

            val itemDecoration = DividerItemDecoration(
                binding.vehicleRecyclerView.context,
                viewManager.orientation
            )
            addItemDecoration(itemDecoration)
        }

        viewModel.vehicles.observe(viewLifecycleOwner) { res ->
            viewAdapter.entities = res.map { vehicle -> vehicle.name }
            viewAdapter.notifyDataSetChanged()
            binding.resource = res
        }
    }

    private fun initializeAddButton() {
        binding.addVehicleFab.setOnClickListener {
            val fragment = CreateVehicleFragment()

            // When the user clicks create in the dialog, create a new vehicle in the database
            fragment.setOnCreateVehicleListener { name ->
                appExecutors.diskIO().execute {
                    vehicleRepository.create(name)
                }
            }

            fragment.show(parentFragmentManager, "create-vehicle")
        }
    }
}
