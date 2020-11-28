package com.eticon.rosseti.OrderFragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.eticon.rosseti.R
import com.eticon.rosseti.adapters.OrdersAdapter
import com.eticon.rosseti.dataClasses.UserApplication
import com.eticon.rosseti.dataClasses.UserData
import com.eticon.rosseti.livedata.applicationUserLiveData
import com.eticon.rosseti.livedata.userProfile

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [OrderFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class OrderFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    lateinit var myOrderRecyclerView: RecyclerView
    lateinit var addOrder:ConstraintLayout

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
        var view = inflater.inflate(R.layout.fragment_order, container, false)
        var orders = mutableListOf<UserApplication>()
//        var user = userProfile.value as UserData
        var addOrder = view.findViewById<ConstraintLayout>(R.id.add_order)
        applicationUserLiveData.observe(this, Observer {
            var data = applicationUserLiveData.value
            if (data != null){
                for (d in data){
                    if (d.user_id == 1){
                        orders.add(d)
                        Log.d("Зашло", d.toString())
                    }
                }
                myOrderRecyclerView = view.findViewById(R.id.my_order_recycler)
                myOrderRecyclerView.layoutManager = LinearLayoutManager(activity?.applicationContext, LinearLayoutManager.VERTICAL, false)
                myOrderRecyclerView.adapter = OrdersAdapter(orders, activity!!)
            }
        })
        addOrder.setOnClickListener {
            activity!!.supportFragmentManager.beginTransaction()!!.replace(R.id.fl_content, CreateOrderStartFragment()).commit()
        }

        return view
    }

    companion object {

        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            OrderFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}