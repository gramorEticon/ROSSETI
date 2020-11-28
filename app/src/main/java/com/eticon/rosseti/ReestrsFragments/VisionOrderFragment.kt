package com.eticon.rosseti.ReestrsFragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.eticon.rosseti.OrderFragments.OrderFragment
import com.eticon.rosseti.R
import com.eticon.rosseti.adapters.AuthorAdapter
import com.eticon.rosseti.adapters.EtapAdapter
import com.eticon.rosseti.adapters.RashodAdapter
import com.eticon.rosseti.livedata.liveMore


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [VisionOrderFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class VisionOrderFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null
    lateinit var name: TextView
    lateinit var org:TextView
    lateinit var category:TextView
    lateinit var category_dig:TextView
    lateinit var poloj:TextView
    lateinit var reshenie:TextView
    lateinit var effect:TextView
    lateinit var recycler_author: RecyclerView
    lateinit var recycler_zatrat:RecyclerView
    lateinit var recycler_srok:RecyclerView
    lateinit var back: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        var view  =  inflater.inflate(R.layout.fragment_vision_order, container, false)
        initView(view)
        Log.d("CLICK", "PPPP")
        liveMore.observe(this, Observer {
            var data = liveMore.value
            if (data!=null){
                name.text = data.name
                org.text = data.org
                category.text = data.category
                category_dig.text = data.subcategory
                poloj.text = data.problems
                reshenie.text = data.decision
                effect.text = data.effect
                if (data.userList.size >0){
                    recycler_author.layoutManager = LinearLayoutManager(activity?.applicationContext, LinearLayoutManager.VERTICAL, false)
                    var adapter = AuthorAdapter(data.userList)
                    recycler_author.adapter = adapter
                }
                if (data.costs.size> 0){
                    recycler_zatrat.layoutManager = LinearLayoutManager(activity?.applicationContext, LinearLayoutManager.VERTICAL, false)
                    var adapter = RashodAdapter(data.costs)
                    recycler_zatrat.adapter = adapter
                }
                if (data.stages.size > 0){
                    recycler_srok.layoutManager = LinearLayoutManager(activity?.applicationContext, LinearLayoutManager.VERTICAL, false)
                    var adapter = EtapAdapter(data.stages)
                    recycler_srok.adapter = adapter
                }

            }
        })

        back.setOnClickListener {
            Log.d("CLICK", "CLICK2")

            activity!!.supportFragmentManager?.beginTransaction()?.replace(R.id.fl_content, VisionOrderFragment())?.commit()

            //activity?.supportFragmentManager?.beginTransaction()?.remove(this)?.commit()
            //activity?.supportFragmentManager?.beginTransaction()?.add(R.id.fl_content, NewReestrFragment())?.commit()
            //activity?.supportFragmentManager?.beginTransaction()?.remove(this)?.commit()
            Log.d("CLICK", "CLICK3")
        }

        return view
    }

    fun initView(view: View){
        name = view.findViewById(R.id.name)
        org = view.findViewById(R.id.org)
        category = view.findViewById(R.id.category)
        category_dig = view.findViewById(R.id.category_dig)
        poloj = view.findViewById(R.id.poloj)
        reshenie = view.findViewById(R.id.reshenie)
        effect = view.findViewById(R.id.effect)
        recycler_author= view.findViewById(R.id.recycler_author)
        recycler_srok= view.findViewById(R.id.recycler_srok)
        recycler_zatrat= view.findViewById(R.id.recycler_zatrat)
        back = view.findViewById(R.id.back_btn)

    }
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment VisionOrderFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            VisionOrderFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}