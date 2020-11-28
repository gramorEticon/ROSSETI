package com.eticon.rosseti.adapters


import android.view.LayoutInflater
import android.view.SurfaceView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.eticon.rosseti.R
import com.eticon.rosseti.dataClasses.SubscribeUser
import com.eticon.rosseti.dataClasses.UserApplication
import org.w3c.dom.Text

class AuthorAdapter (var items: List<SubscribeUser>) : RecyclerView.Adapter<AuthorAdapter.MainHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)  = MainHolder(
            LayoutInflater.from(parent.context).inflate(
                    R.layout.element_author, parent, false))

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        holder.bind(items[position], position)

    }

    inner class MainHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val name = itemView.findViewById<TextView>(R.id.name)


        fun bind(item: SubscribeUser, position: Int) {
            name.text ="#"+ (position+1).toString() + " " + item.name
            /*itemView.setOnClickListener {
                if (adapterPosition != RecyclerView.NO_POSITION) callback.onItemClicked(items[adapterPosition])

            }*/

        }
    }



}