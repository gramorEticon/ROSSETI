package com.eticon.rosseti.adapters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.eticon.rosseti.ForumFragments.DisscusFragment
import com.eticon.rosseti.R
import com.eticon.rosseti.dataClasses.Comment

class DisscusAdapter (var items: List<Comment>) : RecyclerView.Adapter<DisscusAdapter.MainHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)  = MainHolder(
            LayoutInflater.from(parent.context).inflate(
                    R.layout.element_msg, parent, false))


    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        holder.bind(items[position], position)

    }

    inner class MainHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val name = itemView.findViewById<TextView>(R.id.name)
        private val date = itemView.findViewById<TextView>(R.id.date)
        private val text = itemView.findViewById<TextView>(R.id.text)

        fun bind(item: Comment, position: Int) {
            name.text = item.date
            text.text = item.text
            date.text = item.name
        }
    }



}