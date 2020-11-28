package com.eticon.rosseti.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.eticon.rosseti.R
import com.eticon.rosseti.dataClasses.Award
import com.eticon.rosseti.dataClasses.SubscribeUser
import com.eticon.rosseti.dataClasses.order

class RaspredAdapter (var items: MutableList<Award>) : RecyclerView.Adapter<RaspredAdapter.MainHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)  = MainHolder(
            LayoutInflater.from(parent.context).inflate(
                    R.layout.element_raspredelenie, parent, false))

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        holder.bind(items[position])

    }

    inner class MainHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val name = itemView.findViewById<TextView>(R.id.name)
        private val procent = itemView.findViewById<TextView>(R.id.procent)
        private val seekBar =  itemView.findViewById<SeekBar>(R.id.seek)

        fun bind(item: Award) {
            name.text = item.name
            procent.text = item.award.toString()
            seekBar.progress = item.award
            seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
                override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {

                }

                override fun onStartTrackingTouch(seekBar: SeekBar?) {

                }

                override fun onStopTrackingTouch(seekBar: SeekBar?) {
                    var p = procent.text.toString().toInt()
                    p = p - seekBar!!.progress
                    procent.text = seekBar!!.progress.toString()
                    p = p / items.size
                    for (i in 0..items.size-1){
                        if (name.text.toString() == items[i].name){
                            items[i].award -= p
                        }else {
                            items[i].award += p
                        }
                    }
                    notifyDataSetChanged()
                }

            })
            order.awards = items


        }
    }



}