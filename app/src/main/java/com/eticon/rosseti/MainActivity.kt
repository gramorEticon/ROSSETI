package com.eticon.rosseti

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import com.eticon.rosseti.api.ApiMethods
import com.eticon.rosseti.dataClasses.awardToJSOnString
import com.eticon.rosseti.livedata.applicationUserLiveData
import org.json.JSONArray
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var api = ApiMethods()
        api.getUser()
        api.getAppList()



      var intent = Intent(this , NavigatorActivity::class.java)
        startActivity(intent)
        finish()
    }
}