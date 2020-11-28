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
import com.eticon.rosseti.adapters.EtapAdapter
import com.eticon.rosseti.adapters.RashodAdapter
import com.eticon.rosseti.dataClasses.UserApplication
import com.eticon.rosseti.dataClasses.order
import com.eticon.rosseti.livedata.applicationUserLiveData

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [CreateOrderSrokFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CreateOrderSrokFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    lateinit var recyclerView: RecyclerView
    lateinit var add: ConstraintLayout
    lateinit var next: ConstraintLayout
    lateinit var down: ConstraintLayout
    lateinit var btn_back: ImageView
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
        var view = inflater.inflate(R.layout.fragment_create_order_srok, container, false)
        initView(view)
        next.setOnClickListener {
            order.user_id = 1
            order.dateStart = "11.12.2020"
            order.status = "Экспертиза"
            var data = applicationUserLiveData.value
            if (data != null){
                data.add(order)
                applicationUserLiveData.value = data
            }
            else {
                applicationUserLiveData.value = mutableListOf(order)
            }
            activity!!.supportFragmentManager!!.beginTransaction()!!.replace(R.id.fl_content, OrderFragment()).commit()
        }
        down.setOnClickListener {
            activity!!.supportFragmentManager!!.beginTransaction()!!.replace(R.id.fl_content, CreateOrderRashodragment()).commit()
        }
        btn_back.setOnClickListener {
            activity!!.supportFragmentManager!!.beginTransaction()!!.replace(R.id.fl_content, OrderFragment()).commit()
        }
        var data = order.stages
        recyclerView.layoutManager = LinearLayoutManager(activity?.applicationContext, LinearLayoutManager.VERTICAL, false)
        var adapter = EtapAdapter(data)
        recyclerView.adapter = adapter
        add.setOnClickListener {
            activity!!.supportFragmentManager!!.beginTransaction()!!.replace(R.id.fl_content, CreateOrderAddSrokFragment()).commit()
        }
        return view
    }
    fun initView(view:View){
        recyclerView = view.findViewById(R.id.recycler_srok)
        add = view.findViewById(R.id.add_srok)
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
         * @return A new instance of fragment CreateOrderSrokFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            CreateOrderSrokFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}