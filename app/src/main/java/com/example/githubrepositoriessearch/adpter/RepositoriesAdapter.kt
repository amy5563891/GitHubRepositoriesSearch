package com.example.githubrepositoriessearch.adpter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.githubrepositoriessearch.databinding.ItemRepositoryBinding
import com.example.githubrepositoriessearch.model.Repository
import com.example.githubrepositoriessearch.viewmodel.MainViewModel

class RepositoriesAdapter(private var repoList: List<Repository>, private val viewModel: MainViewModel) : RecyclerView.Adapter<RepositoriesAdapter.ViewHolder>() {

    class ViewHolder constructor(private val binding: ItemRepositoryBinding) :RecyclerView.ViewHolder(binding.root) {

        fun bind(viewModel: MainViewModel, repo: Repository) {
            binding.viewModel = viewModel
            binding.repository = repo
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemRepositoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val repo = repoList!![position]
        holder.bind(viewModel, repo)
    }

    override fun getItemCount(): Int {
        return repoList.size
    }

    fun setRepoList(repoList : List<Repository>){
        this.repoList = repoList as ArrayList<Repository>
        notifyDataSetChanged()
    }

}