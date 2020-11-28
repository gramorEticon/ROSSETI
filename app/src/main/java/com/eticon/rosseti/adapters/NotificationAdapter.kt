package com.eticon.rosseti.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.eticon.rosseti.R
import com.eticon.rosseti.dataClasses.Notification

class NotificationAdapter (var items: List<Notification>) : RecyclerView.Adapter<NotificationAdapter.MainHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)  = MainHolder(
            LayoutInflater.from(parent.context).inflate(
                    R.layout.element_notification, parent, false))


    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        holder.bind(items[position], position)

    }

    inner class MainHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val name = itemView.findViewById<TextView>(R.id.name)
        private val date = itemView.findViewById<TextView>(R.id.date)
        private val text = itemView.findViewById<TextView>(R.id.text)



        fun bind(item: Notification, position: Int) {
            name.text = item.name
            date.text = item.date
            text.text = item.text

        }
    }



}