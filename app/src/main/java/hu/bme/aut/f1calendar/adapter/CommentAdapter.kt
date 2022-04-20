package hu.bme.aut.f1calendar.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import hu.bme.aut.f1calendar.databinding.CommentRowBinding
import hu.bme.aut.f1calendar.databinding.FragmentDetailsBinding
import hu.bme.aut.f1calendar.model.Comment

class CommentAdapter(private var comments: ArrayList<Comment>, private val fragmentDetailsBinding: FragmentDetailsBinding):
    RecyclerView.Adapter<CommentAdapter.ViewHolder>() {
    inner class ViewHolder(val binding: CommentRowBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(comment: Comment) {
            binding.comment = comment
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val binding = CommentRowBinding.inflate(LayoutInflater.from(viewGroup.context),viewGroup, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.bind(comments[position])
        viewHolder.binding.comment!!.comment = comments[position].comment
    }

    override fun getItemCount() = comments.size

    @SuppressLint("NotifyDataSetChanged")
    fun setComments(comments: ArrayList<Comment>) {
        this.comments = comments
        notifyDataSetChanged()
    }

    fun findOneByPos(position: Int): Comment{
        return comments[position]
    }

}