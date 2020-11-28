package com.eticon.rosseti.OrderFragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.eticon.rosseti.R
import com.eticon.rosseti.dataClasses.order


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class CreateOrderStartFragment : Fragment() {

    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }
    lateinit var name:TextView
    lateinit var org:TextView
    lateinit var rg_gr:RadioGroup
    lateinit var next:ConstraintLayout
    lateinit var down:ImageView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_create_order_start, container, false)
        initView(view)
        next.setOnClickListener {
            order.name = name.text.toString()
            order.org = org.text.toString()
            order.subcategory = view.findViewById<RadioButton>(rg_gr.checkedRadioButtonId).text.toString()
            Log.d("PRINT", order.toString())
           activity!!.supportFragmentManager!!.beginTransaction()!!.replace(R.id.fl_content, CreateOrderDesriptionFragment()).commit()

        }
        down.setOnClickListener {
            activity!!.supportFragmentManager!!.beginTransaction()!!.replace(R.id.fl_content, OrderFragment()).commit()

        }

        return view
    }

    fun initView(view:View){
        name = view.findViewById(R.id.name)
        org = view.findViewById(R.id.organization)
        rg_gr = view.findViewById(R.id.rg_gr)
        next = view.findViewById(R.id.next)
        down = view.findViewById(R.id.back_btn)

    }
    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            CreateOrderStartFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}