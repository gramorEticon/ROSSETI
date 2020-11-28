package com.eticon.rosseti.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.eticon.rosseti.R
import com.eticon.rosseti.dataClasses.UserApplication
import org.w3c.dom.Text

class OrdersAdapter (var items: List<UserApplication>) : RecyclerView.Adapter<OrdersAdapter.MainHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)  = MainHolder(
            LayoutInflater.from(parent.context).inflate(
                    R.layout.element_order, parent, false))

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        holder.bind(items[position])
    }

    inner class MainHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val name = itemView.findViewById<TextView>(R.id.name)
        private val category = itemView.findViewById<TextView>(R.id.category)
        private val status = itemView.findViewById<TextView>(R.id.status)
        private val date = itemView.findViewById<TextView>(R.id.date)


        fun bind(item: UserApplication) {
            name.text = item.name
            category.text = item.category
            status.text = item.status
            date.text = item.dateStart
            /*itemView.setOnClickListener {
                if (adapterPosition != RecyclerView.NO_POSITION) callback.onItemClicked(items[adapterPosition])

            }*/

        }
    }



}