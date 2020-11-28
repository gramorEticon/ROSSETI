package com.eticon.rosseti.ReestrsFragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.eticon.rosseti.OrderFragments.CreateOrderStartFragment
import com.eticon.rosseti.R
import com.eticon.rosseti.adapters.OrdersAdapter
import com.eticon.rosseti.livedata.applicationUserLiveData
import com.eticon.rosseti.livedata.liveMore

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ReestrFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ReestrFragment : Fragment() {
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
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_reestr, container, false)
        initView(view)
        liveMore.observe(this, Observer {
            var data = liveMore.value
            if (data!=null){
                activity!!.supportFragmentManager.beginTransaction()!!.replace(R.id.fl_content, VisionOrderFragment()).commit()
            }
        })
        applicationUserLiveData.observe(this, Observer {
            var data = applicationUserLiveData.value
            if (data != null){
                recyclerView.layoutManager = LinearLayoutManager(activity?.applicationContext, LinearLayoutManager.VERTICAL, false)
                recyclerView.adapter = OrdersAdapter(data)
                }

            })
        return view
    }
    fun initView(view:View){
        recyclerView = view.findViewById(R.id.recycler_reestr)
    }

    companion object {
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ReestrFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}