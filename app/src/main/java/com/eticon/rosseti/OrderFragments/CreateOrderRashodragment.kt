package com.eticon.rosseti.OrderFragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.eticon.rosseti.R
import com.eticon.rosseti.adapters.AuthorAdapter
import com.eticon.rosseti.adapters.RashodAdapter
import com.eticon.rosseti.dataClasses.order


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class CreateOrderRashodragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    lateinit var recyclerView: RecyclerView
    lateinit var add:ConstraintLayout
    lateinit var next:ConstraintLayout
    lateinit var down:ConstraintLayout
    lateinit var btn_back:ImageView
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
        var view = inflater.inflate(R.layout.fragment_create_order_rashod, container, false)
        initView(view)
        next.setOnClickListener {
            activity!!.supportFragmentManager!!.beginTransaction()!!.replace(R.id.fl_content, CreateOrderSrokFragment()).commit()
        }
        down.setOnClickListener {
            activity!!.supportFragmentManager!!.beginTransaction()!!.replace(R.id.fl_content, CreateOrderRaspredelenieFragment()).commit()
        }
        btn_back.setOnClickListener {
            activity!!.supportFragmentManager!!.beginTransaction()!!.replace(R.id.fl_content, OrderFragment()).commit()
        }
        var data = order.costs
        recyclerView.layoutManager = LinearLayoutManager(activity?.applicationContext, LinearLayoutManager.VERTICAL, false)
        var adapter = RashodAdapter(data)
        recyclerView.adapter = adapter
        add.setOnClickListener {
            activity!!.supportFragmentManager!!.beginTransaction()!!.replace(R.id.fl_content, CreateOrderAddRashodFragment()).commit()
        }
        return view
    }
    fun initView(view:View){
        recyclerView = view.findViewById(R.id.recycler_rashod)
        add = view.findViewById(R.id.add_rashod)
        next = view.findViewById(R.id.next)
        down = view.findViewById(R.id.down)
        btn_back = view.findViewById(R.id.back_btn)
    }
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment CreateOrderRashodragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            CreateOrderRashodragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}