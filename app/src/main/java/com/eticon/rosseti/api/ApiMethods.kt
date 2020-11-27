package com.eticon.rosseti.api

import android.util.Log
import com.eticon.rosseti.dataClasses.UserData
import com.eticon.rosseti.livedata.userProfile
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONObject

class ApiMethods(){
    var api = Api()
    fun get(url:String):String{
        var client = OkHttpClient()
        var request = Request.Builder().url(url).build()
        var result = client.newCall(request).execute().body?.string().toString()
        return result
    }
    fun getUser(){
        GlobalScope.launch(Dispatchers.IO) {
           var str =  get(api.adress + api.user_list+api.id+"1")
           var user = UserData()
            var obj = JSONObject(str)
            user.firstName = obj.getString("firstName")
            user.lastName = obj.getString("lastName")
            user.id = obj.getString("id")
            user.DZO = obj.getString("DZO")
            user.spez = obj.getString("spec")
           withContext(Dispatchers.Main){
                userProfile.value = user
               Log.d("userprofile", userProfile.value.toString())
           }
        }
    }
}