package com.markskelton.voyager.ui.log.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.markskelton.voyager.R
import com.markskelton.voyager.databinding.FragmentLogListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LogListFragment : Fragment() {

    private lateinit var binding: FragmentLogListBinding
    private lateinit var viewAdapter: LogListAdapter
    private lateinit var viewManager: LinearLayoutManager
    private val viewModel: LogListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLogListBinding.inflate(layoutInflater)

        initializeRecyclerView()
        initializeAddButton()

        return binding.root
    }

    private fun initializeRecyclerView() {
        viewManager = LinearLayoutManager(context)
        viewAdapter = LogListAdapter()

        binding.recyclerView.apply {
            // use this setting to improve performance if you know that changes
            // in content do not change the layout size of the RecyclerView
            setHasFixedSize(true)

            layoutManager = viewManager
            adapter = viewAdapter

            val itemDecoration = DividerItemDecoration(
                binding.recyclerView.context,
                viewManager.orientation
            )
            addItemDecoration(itemDecoration)
        }

        viewModel.logs.observe(viewLifecycleOwner) { res ->
            viewAdapter.entities = res
            viewAdapter.notifyDataSetChanged()
            binding.resource = res
        }
    }

    private fun initializeAddButton() {
        binding.fab.setOnClickListener {
            findNavController().navigate(R.id.action_home_to_create_log)
        }
    }
}
