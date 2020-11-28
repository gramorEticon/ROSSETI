package com.eticon.rosseti.OrderFragments

import android.media.Image
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
import com.eticon.rosseti.adapters.RaspredAdapter
import com.eticon.rosseti.dataClasses.Award
import com.eticon.rosseti.dataClasses.order

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [CreateOrderRaspredelenieFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CreateOrderRaspredelenieFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null
    lateinit var recyclerView:RecyclerView
    lateinit var next:ConstraintLayout
    lateinit var down:ConstraintLayout
    lateinit var back:ImageView
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
        var view = inflater.inflate(R.layout.fragment_create_order_raspredelenie, container, false)
        initView(view)
        back.setOnClickListener {
            activity!!.supportFragmentManager!!.beginTransaction()!!.replace(R.id.fl_content, OrderFragment()).commit()
        }
        down.setOnClickListener {
            activity!!.supportFragmentManager!!.beginTransaction()!!.replace(R.id.fl_content, CreateOrderAuthorFragment()).commit()
        }
        next.setOnClickListener {
            activity!!.supportFragmentManager!!.beginTransaction()!!.replace(R.id.fl_content, CreateOrderRashodragment()).commit()
        }
       var data =  getAwards()
        recyclerView.layoutManager = LinearLayoutManager(activity?.applicationContext, LinearLayoutManager.VERTICAL, false)
        var adapter = RaspredAdapter(data)
        recyclerView.adapter = adapter

        return view
    }

    fun initView(view:View){
        recyclerView = view.findViewById(R.id.recycler_raspred)
        back= view.findViewById(R.id.back_btn)
        next = view.findViewById(R.id.next)
        down = view.findViewById(R.id.down)
    }
    fun getAwards():MutableList<Award>{
        var users = order.userList
        var proz = 100/users.size
        var awardList = mutableListOf<Award>()
        for (us in users){
            awardList.add(Award(name = us.name, award = proz))
        }
        return awardList
    }
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment CreateOrderRaspredelenieFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            CreateOrderRaspredelenieFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}