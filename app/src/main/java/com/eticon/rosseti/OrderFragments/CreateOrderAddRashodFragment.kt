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
import com.eticon.rosseti.dataClasses.order

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [CreateOrderAddRashodFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CreateOrderAddRashodFragment : Fragment() {
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
        var view = inflater.inflate(R.layout.fragment_create_order_add_rashod, container, false)
        var btn = view.findViewById<ConstraintLayout>(R.id.save)
        btn.setOnClickListener {
            var statia = view.findViewById<EditText>(R.id.statia).text.toString()
            var sum = view.findViewById<EditText>(R.id.summa).text.toString().toInt()
            order.costs.add(Cost(name = statia, sumMax = sum))
            activity!!.supportFragmentManager!!.beginTransaction()!!.replace(R.id.fl_content, CreateOrderRashodragment()).commit()
        }
        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment CreateOrderAddRashodFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            CreateOrderAddRashodFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}