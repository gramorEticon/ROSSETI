package com.eticon.rosseti.api

import android.util.Log
import com.eticon.rosseti.dataClasses.*
import com.eticon.rosseti.livedata.applicationUserLiveData
import com.eticon.rosseti.livedata.userProfile
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.internal.concurrent.formatDuration
import org.json.JSONArray
import org.json.JSONObject
//Класс взаимодействия с апи сервера
class ApiMethods(){
    var api = Api()
    fun get(url:String):String{
        var client = OkHttpClient()
        var request = Request.Builder().url(url).build()
        var result = client.newCall(request).execute().body?.string().toString()
        return result
    }
    //Метод для получения текущего пользователя
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
    //метод получения всех заявок
    fun getAppList(){
        GlobalScope.launch(Dispatchers.IO){
            var str = get(api.adress + api.task_list)
            var dataList = mutableListOf<UserApplication>()
            var jsMas = JSONArray(str)
            for (i in 0..jsMas.length()-1){
                var obj = jsMas.getJSONObject(i)
                var temp = UserApplication()
                temp.name = obj.getString("name")
                temp.category = obj.getString("category")
                temp.user_id = obj.getString("user_id").toInt()
                temp.dateStart = obj.getString("dateStart")
                temp.status = obj.getString("status")
                temp.problems = obj.getString("problems")
                temp.decision = obj.getString("decision")
                temp.effect = obj.getString("effect")
                var ar = obj.getString("userList").split(",")
                var userList = mutableListOf<SubscribeUser>()
                for (a in ar){
                    userList.add(SubscribeUser(
                            name = a
                    ))
                }
                temp.userList = userList
                var costArrJSON = obj.getJSONArray("costs")
                var costArr = mutableListOf<Cost>()
                for (i in 0..costArrJSON.length()-1){
                    costArr.add(Cost(number = costArrJSON.getJSONObject(i).getInt("number"),
                                        name =  costArrJSON.getJSONObject(i).getString("name"),
                                        sumMax =  costArrJSON.getJSONObject(i).getInt("sumMax")

                    ))
                }
                temp.costs = costArr
                var stagesArrJSON = obj.getJSONArray("stages")
                var stagesArr = mutableListOf<Stage>()
                for (i in 0..stagesArrJSON.length()-1){
                    stagesArr.add(Stage(number = stagesArrJSON.getJSONObject(i).getInt("number"),
                            name =  stagesArrJSON.getJSONObject(i).getString("name"),
                            days =  stagesArrJSON.getJSONObject(i).getInt("days")
                    ))
                }
                temp.stages = stagesArr
                var awardsArrJson = obj.getJSONArray("awards")
                var awardArr = mutableListOf<Award>()
                for (i in 0..stagesArrJSON.length()-1){
                    awardArr.add(Award(name =  awardsArrJson.getJSONObject(i).getString("user_id"),
                            award =  awardsArrJson.getJSONObject(i).getInt("award")
                    ))
                }
                temp.awards = awardArr
                dataList.add(temp)
            }

            withContext(Dispatchers.Main){
                applicationUserLiveData.value = dataList
                Log.d("ZAZ", applicationUserLiveData.value.toString())
            }
        }

    }
}