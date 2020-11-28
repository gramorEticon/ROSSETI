package com.eticon.rosseti.OrderFragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import android.widget.AdapterView.OnItemSelectedListener
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment

import com.eticon.rosseti.R
import com.eticon.rosseti.dataClasses.EditDistanceRecursive
import com.eticon.rosseti.dataClasses.UserApplication
import com.eticon.rosseti.dataClasses.order
import com.eticon.rosseti.livedata.applicationUserLiveData


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class CreateOrderDesriptionFragment : Fragment() {

    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }
    lateinit var polozenit:TextView
    lateinit var res:TextView
    lateinit var effect:TextView
    lateinit var sp:Spinner
    lateinit var next:ConstraintLayout
    lateinit var down:ConstraintLayout
    lateinit var back:ImageView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_create_order_desription, container, false)
        initView(view)
        back.setOnClickListener {
            activity!!.supportFragmentManager!!.beginTransaction()!!.replace(R.id.fl_content, OrderFragment()).commit()
        }
        down.setOnClickListener {
            activity!!.supportFragmentManager!!.beginTransaction()!!.replace(R.id.fl_content, CreateOrderStartFragment()).commit()
        }

        sp.setOnItemSelectedListener(object : OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?,
                                        itemSelected: View, selectedItemPosition: Int, selectedId: Long) {
                val choose = resources.getStringArray(R.array.animals)
                order.category = choose[selectedItemPosition]

            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        })

        next.setOnClickListener {
            order.problems  = polozenit.text.toString()
            order.decision = res.text.toString()
            order.effect = effect.text.toString()
            var f = EditDistanceRecursive
            var orders = applicationUserLiveData.value as MutableList<UserApplication>
            for (o in orders){
                if ( f.calculateTwo(o.name, order.name)<10){
                    activity!!.supportFragmentManager!!.beginTransaction()!!.replace(R.id.fl_content, CreateOrderPlagiatFragment()).commit()
                }
            }
            activity!!.supportFragmentManager!!.beginTransaction()!!.replace(R.id.fl_content, CreateOrderAuthorFragment()).commit()


        }

        return view
    }
    fun initView(view:View){
        polozenit = view.findViewById(R.id.polozenie)
        res = view.findViewById(R.id.reshenie)
        effect = view.findViewById(R.id.effect)
        sp = view.findViewById(R.id.spinner)
        val adapter: ArrayAdapter<*> = ArrayAdapter.createFromResource(activity!!.baseContext, R.array.animals, android.R.layout.simple_spinner_item)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        sp.adapter = adapter
        next =view.findViewById(R.id.next)
        down =view.findViewById(R.id.down)
        back = view.findViewById(R.id.back_btn)

    }
    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            CreateOrderDesriptionFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}