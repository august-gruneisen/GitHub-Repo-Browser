package com.augustg.githubrepobrowser.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.augustg.githubrepobrowser.GitHubViewModel
import com.augustg.githubrepobrowser.databinding.IssueCardBinding

class IssuesAdapter(
    private val viewModel: GitHubViewModel,
    private val issueClickListener: (View, Int) -> Unit
) : RecyclerView.Adapter<IssuesAdapter.ViewHolder>() {

    inner class ViewHolder(private var binding: IssueCardBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int, lifecycleOwner: LifecycleOwner) {
            binding.position = position
            binding.viewModel = viewModel
            binding.lifecycleOwner = lifecycleOwner
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater: LayoutInflater = LayoutInflater.from(parent.context)
        val binding = IssueCardBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.bind(position, holder.itemView.context as LifecycleOwner)

        holder.itemView.setOnClickListener {
            issueClickListener(holder.itemView, position)
        }

    }

    override fun getItemCount(): Int {
        return viewModel.issues.value!!.size
    }

}