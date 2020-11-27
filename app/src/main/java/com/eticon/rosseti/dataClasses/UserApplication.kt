package com.eticon.rosseti.dataClasses

import androidx.annotation.VisibleForTesting

//Дата класс которыйв себе содержит заявку
data class UserApplication (
    var id:Int = 0,
    var user_id:Int = 0,
    var dateStart:String = "",
    var dateEnd:String = "",
    var Status:String = "",
    var userList:List<UserData> = listOf(),
    var name:String = "",
    var category:String = "",
    var problems:String = "",
    var decision:String = "",
    var effect:String = "",
    var costs:List<Cost> = listOf(),
    var stages : List<Stage> = listOf(),
    var awards:List<Award> = listOf()

)

//Дата класс необходимый для реализации затрат
data class Cost(
    var number:Int = 0,
    var name:String = "",
    var sum:Int = 0
)
//Дата класс этапов внедрения
data class Stage(
    var number:Int = 0 ,
    var name:String = "",
    var days:Int = 0
)

data class Award(
    var firstName: String = "",
    var lastName:String = "",
    var award: Int =  0
)