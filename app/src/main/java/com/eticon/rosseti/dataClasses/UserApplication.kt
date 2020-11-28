package com.eticon.rosseti.dataClasses

import android.util.Log
import androidx.annotation.VisibleForTesting
import com.eticon.rosseti.livedata.applicationUserLiveData
import org.json.JSONArray
import org.json.JSONObject

//Дата класс которыйв себе содержит заявку
data class UserApplication (
    var id:Int = 0,
    var user_id:Int = 0,
    var subcategory:String = "",
    var org:String = "",
    var dateStart:String = "",
    var status:String = "",
    var userList:MutableList<SubscribeUser> = mutableListOf(),
    var name:String = "",
    var category:String = "",
    var problems:String = "",
    var decision:String = "",
    var effect:String = "",
    var costs:MutableList<Cost> =  mutableListOf(),
    var stages : MutableList<Stage> =  mutableListOf(),
    var awards:MutableList<Award> =  mutableListOf(),
    var comments:MutableList<Comment> =  mutableListOf()
)

//Дата класс необходимый для реализации затрат
data class Cost(
    var number:Int = 0,
    var name:String = "",
    var sumMax:Int = 0
)
//Дата класс этапов внедрения
data class Stage(
    var number:Int = 0 ,
    var name:String = "",
    var days:Int = 0
)
//Дата класс вознаграждения
data class Award(
    var name:String =  "",
    var award: Int =  0
)

//Список участников инициативы
data class SubscribeUser(
    var name:String = ""
)
//Список возможных категорий для заявки
var categoryAppList = listOf<String>(
    "Эксплуатация подстанций (подстанционного оборудования)",
    "Эксплуатация магистральных сетей",
    "Эксплуатация распределительных сетей",
    "Капитальное строительство, реконструкция, проектирование",
    "Эксплуатация зданий, сооружений, специальной техники",
    "Оперативно-диспетчерское управление",
    "Релейная защита и противоаварийная автоматика",
    "Информационные технологии, системы связи",
    "Мониторинг и диагностика",
    "Контроль качества и учёт электроэнергии",
    "Производственная безопасность и охрана труда",
    "Технологическое присоединение",
    "Аварийно-восстановительные работы",
    "Экология, энергоэффективность, снижение потерь",
    "Совершенствование системы управления",
    "Дополнительные (нетарифные) услуги"
)
//Список возможных статусов для заявки
var statusAppList = listOf<String>(
    "Экспертиза",
    "Доработка",
    "Признано",
    "Отклонено",
    "Идёт опытное применение",
    "Опытное применение завершено успешно",
    "Опытное применение завершено неуспешно",
    "Тиражирование завершено успешно",
    "Тиражирование завершено неуспешно"
)

fun awardToJSOnString(){


}

var order = UserApplication()