package com.markskelton.voyager.ui.projects

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.markskelton.voyager.AppExecutors
import com.markskelton.voyager.databinding.FragmentProjectsBinding
import com.markskelton.voyager.repositories.ProjectRepository
import com.markskelton.voyager.ui.SimpleListAdapter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ProjectsFragment : Fragment() {

    private lateinit var binding: FragmentProjectsBinding
    private lateinit var viewAdapter: SimpleListAdapter
    private lateinit var viewManager: LinearLayoutManager
    private val viewModel: ProjectListViewModel by viewModels()

    @Inject
    lateinit var projectRepository: ProjectRepository

    @Inject
    lateinit var appExecutors: AppExecutors

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProjectsBinding.inflate(layoutInflater)

        initializeRecyclerView()
        initializeAddButton()

        return binding.root
    }

    private fun initializeRecyclerView() {
        viewManager = LinearLayoutManager(context)
        viewAdapter = SimpleListAdapter()

        binding.projectRecyclerView.apply {
            // use this setting to improve performance if you know that changes
            // in content do not change the layout size of the RecyclerView
            setHasFixedSize(true)

            layoutManager = viewManager
            adapter = viewAdapter

            val itemDecoration = DividerItemDecoration(
                binding.projectRecyclerView.context,
                viewManager.orientation
            )
            addItemDecoration(itemDecoration)
        }

        viewModel.projects.observe(viewLifecycleOwner) { res ->
            viewAdapter.entities = res.map { project -> project.name }
            viewAdapter.notifyDataSetChanged()
            binding.resource = res
        }
    }

    private fun initializeAddButton() {
        binding.addProjectFab.setOnClickListener {
            val fragment = CreateProjectFragment()

            // When the user clicks create in the dialog, create a new project in the database
            fragment.setOnCreateProjectListener { name ->
                appExecutors.diskIO().execute {
                    projectRepository.create(name)
                }
            }

            fragment.show(parentFragmentManager, "create-project")
        }
    }
}
