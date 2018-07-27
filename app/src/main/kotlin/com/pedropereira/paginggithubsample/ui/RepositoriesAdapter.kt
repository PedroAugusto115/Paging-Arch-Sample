package com.pedropereira.paginggithubsample.ui

import android.arch.paging.PagedListAdapter
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.pedropereira.paginggithubsample.R
import com.pedropereira.paginggithubsample.model.Repository
import kotlinx.android.synthetic.main.repository_list_item.view.*

class RepositoriesAdapter: PagedListAdapter<Repository, RepositoriesAdapter.GitRepositoryViewHolder>(repositoryDiff) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GitRepositoryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.repository_list_item, parent, false)
        return GitRepositoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: GitRepositoryViewHolder, position: Int) {
        val repo = getItem(position)
        holder.title.text = repo?.name
    }

    class GitRepositoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title = itemView.title
    }

    companion object {
        val repositoryDiff = object : DiffUtil.ItemCallback<Repository>() {
            override fun areContentsTheSame(oldItem: Repository, newItem: Repository): Boolean =
                    oldItem == newItem

            override fun areItemsTheSame(oldItem: Repository, newItem: Repository): Boolean =
                    oldItem.id == newItem.id
        }
    }
}
