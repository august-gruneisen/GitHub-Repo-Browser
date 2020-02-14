package com.augustg.githubrepobrowser.view

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.cardview.widget.CardView
import com.augustg.githubrepobrowser.R
import kotlinx.android.synthetic.main.repo_card.view.*

class RepoCard @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : CardView(context, attrs, defStyleAttr) {

    private var detailsVisible = false

    init {
        val inflater = LayoutInflater.from(context)
        inflater.inflate(R.layout.repo_card, this)

        this.setOnClickListener {
            if (detailsVisible)
                showDetails(false)
            else
                showDetails(true)
        }

        showDetails(detailsVisible)
    }

    fun showDetails(show: Boolean) {
        if (show) {
            details_layout.visibility = View.VISIBLE
            bottom_line.visibility = View.VISIBLE
            detailsVisible = true
        } else {
            details_layout.visibility = View.GONE
            bottom_line.visibility = View.GONE
            detailsVisible = false
        }
    }

    /** For binding data **/

    fun setIcon(icon: Drawable?) {
        repo_icon.setImageDrawable(icon)
    }

    fun setOwner(owner: String) {
        owner_field.text = "$owner/"
    }

    fun setName(name: String) {
        name_field.text = name
    }

    fun setDescription(description: String) {
        description_field.text = description
    }

    fun setStarCount(count: Int) {
        star_field.text = count.toString()
    }

    fun setWatcherCount(count: Int) {
        watcher_field.text = count.toString()
    }

    fun setIssueCount(count: Int) {
        issue_field.text = count.toString()
    }

}