package com.eticon.rosseti.api
//Класс описания апи методов
data class Api (
    var adress:String = "http://192.168.100.6:8000/",
    var user_list:String = "user-list/",
    var id:String = "?user_id=",
    var task_list:String = "task-list/",
    var task_add:String = "task-add/",
    var check:String = "check/"
)


