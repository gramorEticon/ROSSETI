package com.eticon.rosseti.api

import android.util.Log
import com.eticon.rosseti.dataClasses.*
import com.eticon.rosseti.livedata.applicationUserLiveData
import com.eticon.rosseti.livedata.userProfile
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.FormBody
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.internal.concurrent.formatDuration
import okio.ByteString
import okio.Utf8
import org.json.JSONArray
import org.json.JSONObject
import java.nio.charset.Charset

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
  //  метод получения всех заявок
    fun getAppList(){
        GlobalScope.launch(Dispatchers.IO){
            var str = get(api.adress + api.task_list)
            Log.d("str", str)
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
            }
        }



    }
    fun post(url:String, body: FormBody.Builder ){
        var builder = Request.Builder()
        builder = builder.url(url)
        builder = builder.post(body.build())
        var client = OkHttpClient()
        val request = builder.build()
        val call = client.newCall(request)
        var r =  call.execute().body?.string().toString()
        Log.d("RESSS", r)
    }

  /*  fun insertUserApp(task:UserApplication){
        GlobalScope.launch(Dispatchers.IO) {
            var url = api.adress + api.task_add
            val formBodyBuilder = FormBody.Builder()
            formBodyBuilder.add("user_id", task.user_id.toString())
            formBodyBuilder.add("dateStart", task.dateStart)
            formBodyBuilder.add("status", "")
            formBodyBuilder.add("name", task.name)
            formBodyBuilder.add("category", task.category)
            formBodyBuilder.add("problems", task.problems)
            formBodyBuilder.add("decision", task.decision)
            formBodyBuilder.add("effect", task.effect)
            var costsStr = "["
            for (i in 0..task.costs.size-1){
                costsStr = costsStr + "{\"number\":${task.costs[i].number}, \"name\":${task.costs[i].name}, \"sumMax\":${task.costs[i].sumMax}}"
                if (i <task.costs.size-1) {
                    costsStr = costsStr + ","
                }
            }
            costsStr = costsStr + "]"
            Log.d("COSTS", costsStr)
          //  formBodyBuilder.add("costs", costsStr)
            var stageStr = "["
            for (i in 0..task.stages.size-1){
                stageStr = stageStr + "{\"number\":${task.stages[i].number}, \"name\":${task.stages[i].name}, \"days\":${task.stages[i].days}}"
                if (i <task.stages.size-1){
                    stageStr = stageStr + ","
                }

            }
            stageStr = stageStr + "]"
            Log.d("COSTS", stageStr)
           // formBodyBuilder.add("stages", stageStr)
            var awardsStr = "["
            for (i in 0..task.awards.size-1){
                awardsStr = awardsStr + "{\"name\":${task.awards[i].name}, \"award\":${task.awards[i].award}}"
                if (i <task.awards.size-1){
                    awardsStr = awardsStr + ","
                }

            }
            awardsStr = awardsStr + "]"
            Log.d("COSTS", awardsStr)
            formBodyBuilder.add("awards", awardsStr)
            var userstr = ""
            for (i in 0..task.userList.size-1){
                userstr = userstr+ task.userList[i].name
                if (i < task.userList.size-1){
                    userstr = userstr + ","
                }
            }
            formBodyBuilder.add("userList", userstr)
            post(api.adress + api.task_add, formBodyBuilder)
        }
    }*/
}