package com.eticon.rosseti.ForumFragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.eticon.rosseti.R
import com.eticon.rosseti.adapters.AuthorAdapter
import com.eticon.rosseti.adapters.ForumAdapter
import com.eticon.rosseti.dataClasses.data
import com.eticon.rosseti.livedata.forum

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class ForumFragment : Fragment() {

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
        val view = inflater.inflate(R.layout.fragment_forum, container, false)



        Log.d("TAG", forum.value.toString())
        lateinit var recyclerView: RecyclerView
        recyclerView = view.findViewById(R.id.recycler_forum)
        recyclerView.layoutManager = LinearLayoutManager(activity?.applicationContext, LinearLayoutManager.VERTICAL, false)
        var adapter = ForumAdapter(forum.value!!)
        recyclerView.adapter = adapter



        return view
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ForumFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}