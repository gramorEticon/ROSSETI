package com.eticon.rosseti.ForumFragments

import android.app.Activity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.eticon.rosseti.R
import com.eticon.rosseti.adapters.DisscusAdapter
import com.eticon.rosseti.adapters.ForumAdapter
import com.eticon.rosseti.dataClasses.Comment
import com.eticon.rosseti.livedata.forum
import java.text.SimpleDateFormat
import java.util.*


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class DisscusFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    lateinit var btn_back:ImageView
    lateinit var like: TextView
    lateinit var dislike: TextView
    lateinit var name: TextView
    lateinit var decs: TextView
    lateinit var recycler: RecyclerView
    lateinit var edit: EditText
    lateinit var send: ConstraintLayout

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
        val view = inflater.inflate(R.layout.fragment_disscus, container, false)
        val pos = arguments!!.getInt("forum_id")
        btn_back = view.findViewById<ImageView>(R.id.back_btn)
        name = view.findViewById(R.id.label)
        decs = view.findViewById(R.id.desc)
        like = view.findViewById(R.id.like_count)
        dislike = view.findViewById(R.id.dislike_count)
        recycler = view.findViewById(R.id.recycler_msg)
        edit = view.findViewById(R.id.msg)
        send = view.findViewById(R.id.send_msg)



        btn_back.setOnClickListener{
            activity!!.supportFragmentManager.beginTransaction().replace(R.id.fl_content, ForumFragment()).commit()
        }

        var data = forum.value?.get(pos)

        if (data != null) {
            like.text = data.likes.toString()
            dislike.text = data.likes.toString()
            name.text = "Обсуждение " + data.name
            decs.text = data.description
        }
        var comment = data?.comments as MutableList<Comment>

        recycler.layoutManager = LinearLayoutManager(activity?.applicationContext, LinearLayoutManager.VERTICAL, false)
        var adapter = DisscusAdapter(comment)
        recycler.adapter = adapter

        send.setOnClickListener{
            val message = edit.text.toString()
            if(message.isNotEmpty()){
                val sdf = SimpleDateFormat("hh:mm")
                val currentDate = sdf.format(Date())
                edit.setText("")
                val imm =
                        context?.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(view?.windowToken, 0)

                val d = forum.value
                if(d!=null){
                    d[pos].comments.add(Comment(0, 0, message,  "Я", currentDate))
                }
                data = forum.value?.get(pos)
                comment = data?.comments!!
                recycler.layoutManager = LinearLayoutManager(activity?.applicationContext, LinearLayoutManager.VERTICAL, false)
                adapter = DisscusAdapter(comment)
                recycler.adapter = adapter
            }
        }


        Log.d("Tag", data.toString())

        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment DisscusFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            DisscusFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}