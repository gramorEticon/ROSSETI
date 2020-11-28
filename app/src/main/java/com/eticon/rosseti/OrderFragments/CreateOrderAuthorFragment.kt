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
import com.eticon.rosseti.adapters.AuthorAdapter
import com.eticon.rosseti.adapters.OrdersAdapter
import com.eticon.rosseti.dataClasses.order
import com.eticon.rosseti.livedata.applicationUserLiveData
import com.eticon.rosseti.livedata.userListData
import okhttp3.internal.notifyAll

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [CreateOrderAuthorFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CreateOrderAuthorFragment : Fragment() {
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
    lateinit var add_btn: ConstraintLayout
    lateinit var next:ConstraintLayout
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        var view = inflater.inflate(R.layout.fragment_create_order_author, container, false)
        var authors = order.userList
        initView(view)
        userListData.observe(this, Observer {
            var data = userListData.value
            Log.d("LLLL", data.toString())
            if (data!=null){
                recyclerView.layoutManager = LinearLayoutManager(activity?.applicationContext, LinearLayoutManager.VERTICAL, false)
                var adapter = AuthorAdapter(data)
                recyclerView.adapter = adapter
            }
        })

        next.setOnClickListener {
            activity!!.supportFragmentManager!!.beginTransaction()!!.replace(R.id.fl_content, CreateOrderRaspredelenieFragment()).commit()
        }
        add_btn.setOnClickListener {
            activity!!.supportFragmentManager!!.beginTransaction()!!.replace(R.id.fl_content, CreateOrderAddAuthorFragment()).commit()
        }
        return view
    }

    fun initView(view:View){
        recyclerView = view.findViewById(R.id.recycler_author)
        add_btn = view.findViewById(R.id.add_author)
        next = view.findViewById(R.id.next)

    }
    companion object {
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                CreateOrderAuthorFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }
}