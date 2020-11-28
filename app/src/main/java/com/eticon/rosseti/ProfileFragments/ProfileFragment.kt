package com.eticon.rosseti.ProfileFragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import com.eticon.rosseti.R
import com.eticon.rosseti.livedata.userProfile

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class ProfileFragment : Fragment() {

    private var param1: String? = null
    private var param2: String? = null
    lateinit var fio:TextView
    lateinit var dzo:TextView
    lateinit var dolz:TextView
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
        var view = inflater.inflate(R.layout.fragment_profile, container, false)
        initViews(view)
        //истользование лайв даты
        userProfile.observe(this, Observer {
            var profile = userProfile.value
            if (profile != null){
                fio.text = profile.lastName + " "+ profile.firstName
                dzo.text = profile.DZO
                dolz.text = profile.spez
            }
        })
        return view
    }
    //Инициальзация всех вью фрагмента
    fun initViews(view:View){
        fio = view.findViewById(R.id.fio)
        dzo = view.findViewById(R.id.dzo)
        dolz = view.findViewById(R.id.dolz)
    }
    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ProfileFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}