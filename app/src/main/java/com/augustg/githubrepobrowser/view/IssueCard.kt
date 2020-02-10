package com.augustg.githubrepobrowser.view

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.Toast
import androidx.cardview.widget.CardView
import com.augustg.githubrepobrowser.R
import kotlinx.android.synthetic.main.issue_card.view.*

class IssueCard @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : CardView(context, attrs, defStyleAttr) {

    init {

        val inflater = LayoutInflater.from(context)
        inflater.inflate(R.layout.repo_card, this)

        this.setOnClickListener {
            Toast.makeText(context, "Toast", Toast.LENGTH_SHORT).show()
        }

    }

    /** For binding data **/

    fun setIcon(icon: Drawable?) {
        issue_icon.setImageDrawable(icon)
    }

    fun setTitle(title: String) {
        title_field.text = title
    }

    fun setDescription(number: Int, daysAgo: Int, user: String) {
        description_field.text = "#$number opened $daysAgo days ago by $user"
    }

    fun setCommentCount(count: Int) {
        comment_field.text = count.toString()
    }

}