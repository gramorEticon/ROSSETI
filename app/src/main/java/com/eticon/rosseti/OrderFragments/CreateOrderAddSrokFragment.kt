package com.eticon.rosseti.OrderFragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.constraintlayout.widget.ConstraintLayout
import com.eticon.rosseti.R
import com.eticon.rosseti.dataClasses.Cost
import com.eticon.rosseti.dataClasses.Stage
import com.eticon.rosseti.dataClasses.order

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [CreateOrderAddSrokFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CreateOrderAddSrokFragment : Fragment() {
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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view =  inflater.inflate(R.layout.fragment_create_order_add_srok, container, false)
        var btn = view.findViewById<ConstraintLayout>(R.id.save)
        btn.setOnClickListener {
            var name  = view.findViewById<EditText>(R.id.name).text.toString()
            var srok = view.findViewById<EditText>(R.id.srok).text.toString().toInt()
            order.stages.add(Stage(name = name, days = srok))
            activity!!.supportFragmentManager!!.beginTransaction()!!.replace(R.id.fl_content, CreateOrderSrokFragment()).commit()
        }
        return view
    }


    companion object {
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            CreateOrderAddSrokFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}