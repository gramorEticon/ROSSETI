package com.eticon.rosseti.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.eticon.rosseti.R
import com.eticon.rosseti.dataClasses.ChatsData

class ForumAdapter (var items: List<ChatsData>) : RecyclerView.Adapter<ForumAdapter.MainHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)  = MainHolder(
        LayoutInflater.from(parent.context).inflate(
            R.layout.element_forum, parent, false))


    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        holder.bind(items[position], position)

    }

    inner class MainHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val name = itemView.findViewById<TextView>(R.id.name)
        private val category = itemView.findViewById<TextView>(R.id.category)
        private val desc = itemView.findViewById<TextView>(R.id.desc)
        private val like = itemView.findViewById<TextView>(R.id.like_count)
        private val dislike = itemView.findViewById<TextView>(R.id.dislike_count)
        private val comment = itemView.findViewById<TextView>(R.id.comment_count)


        fun bind(item: ChatsData, position: Int) {
            name.text = item.name
            category.text = item.category
            desc.text = item.description
            like.text = item.likes.toString()
            dislike.text = item.dislikes.toString()
            comment.text = item.comments.count().toString()


        }
    }



}