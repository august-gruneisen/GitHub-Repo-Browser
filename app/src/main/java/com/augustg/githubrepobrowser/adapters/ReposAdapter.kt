package com.augustg.githubrepobrowser.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.augustg.githubrepobrowser.GitHubViewModel
import com.augustg.githubrepobrowser.databinding.RepoCardBinding
import kotlinx.android.synthetic.main.repo_card.view.*

/**
 *
 * I'm looking forward to explaining my implementation of this adapter in person!
 *
 */

class ReposAdapter(
    private val viewModel: GitHubViewModel,
    private val repoLongClickListener: (View, String) -> Unit
) : RecyclerView.Adapter<ReposAdapter.ViewHolder>() {

    inner class ViewHolder(private var binding: RepoCardBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int, lifecycleOwner: LifecycleOwner) {
            binding.position = position
            binding.viewModel = viewModel
            binding.lifecycleOwner = lifecycleOwner
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater: LayoutInflater = LayoutInflater.from(parent.context)
        val binding = RepoCardBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.bind(position, holder.itemView.context as LifecycleOwner)

        holder.itemView.setOnClickListener {
            showHideDetails(holder)
        }

        holder.itemView.setOnLongClickListener{
            repoLongClickListener(it, holder.itemView.name_field.text.toString())
            true
        }
    }

    override fun getItemCount(): Int {
        return viewModel.repos.value!!.size
    }

    private fun showHideDetails(holder: ViewHolder) {
        if (holder.itemView.details_layout.visibility == View.GONE) {
            holder.itemView.details_layout.visibility = View.VISIBLE
            holder.itemView.bottom_line.visibility = View.VISIBLE
        } else {
            holder.itemView.details_layout.visibility = View.GONE
            holder.itemView.bottom_line.visibility = View.GONE
        }
    }

}