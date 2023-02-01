package com.example.githubrepositoriessearch.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.githubrepositoriessearch.R
import com.example.githubrepositoriessearch.adpter.RepositoriesAdapter
import com.example.githubrepositoriessearch.databinding.FragmentRepoSearchBinding
import com.example.githubrepositoriessearch.model.Repository
import com.example.githubrepositoriessearch.viewmodel.MainViewModel

class RepoSearchFragment  : Fragment() {
    private var binding: FragmentRepoSearchBinding? = null
    private val sharedViewModel: MainViewModel by activityViewModels()
    private lateinit var repoAdapter: RepositoriesAdapter
    private lateinit var repoList: List<Repository>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentBinding = FragmentRepoSearchBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.apply {
            binding = FragmentRepoSearchBinding.inflate(layoutInflater)
            binding?.viewModel = sharedViewModel
            viewModel?.getSearchResult()
            viewModel?.openItemEvent?.observe(viewLifecycleOwner) { event ->
                event.getContentIfNotHandled()?.let {
                    requireActivity().supportFragmentManager
                        .beginTransaction()
                        .replace(
                            com.google.android.material.R.id.container,
                            RepoDetailFragment.newInstance()
                        )
                        .addToBackStack(null)
                        .commit()
                }
            }
            viewModel?.observeRepoLiveData()?.observe(viewLifecycleOwner) { list ->
                repoList = list
                prepareRecyclerView()
            }
        }
    }

    private fun prepareRecyclerView() {

        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(context)

        repoAdapter = RepositoriesAdapter(repoList, sharedViewModel)
        repoAdapter.setRepoList(repoList)

        binding?.viewModel = sharedViewModel
        binding?.lifecycleOwner = this
        binding?.searchResult?.layoutManager = layoutManager
        binding?.searchResult?.adapter = repoAdapter
    }
    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}