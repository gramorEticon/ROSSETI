package com.eticon.rosseti.OrderFragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.eticon.rosseti.R
import com.eticon.rosseti.adapters.OrdersAdapter
import com.eticon.rosseti.dataClasses.EditDistanceRecursive
import com.eticon.rosseti.dataClasses.UserApplication
import com.eticon.rosseti.dataClasses.order
import com.eticon.rosseti.livedata.applicationUserLiveData

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [CreateOrderPlagiatFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CreateOrderPlagiatFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }
    lateinit var recyclerView: RecyclerView
    lateinit var next:ConstraintLayout
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_create_order_plagiat, container, false)
        initView(view)
        return view
    }
    fun initView(view:View){
        next = view.findViewById(R.id.next)
        recyclerView = view.findViewById(R.id.recycler_plagiat)
        recyclerView.layoutManager = LinearLayoutManager(activity?.applicationContext, LinearLayoutManager.VERTICAL, false)
        var f = EditDistanceRecursive
        var liveOrder = applicationUserLiveData.value as MutableList<UserApplication>
        var orders = mutableListOf<UserApplication>()
        for (o in liveOrder){
            if (f.calculateTwo(o.name, order.name)<10){
                orders.add(o)
            }
        }
        recyclerView.adapter = OrdersAdapter(orders, activity!!)
        next.setOnClickListener {
            activity!!.supportFragmentManager!!.beginTransaction()!!.replace(R.id.fl_content, CreateOrderDesriptionFragment()).commit()
        }
    }
    companion object {

        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            CreateOrderPlagiatFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}