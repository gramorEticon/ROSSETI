package com.eticon.rosseti.OrderFragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.eticon.rosseti.R


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
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_create_order_desription, container, false)
        initView(view)
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