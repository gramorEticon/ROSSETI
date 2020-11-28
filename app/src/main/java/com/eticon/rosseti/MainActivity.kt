package com.eticon.rosseti

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.eticon.rosseti.api.ApiMethods

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