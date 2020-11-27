package com.eticon.rosseti.dataClasses
//Дата класс элемента чат
data class ChatsData (
    var id: Int = 0,
    var name:String = "",
    var description:String = "",
    var apps:UserApplication = UserApplication(),
    var likes:Int = 0,
    var dislikes:Int = 0,
    var comments:List<Comment> = listOf()


)
//Дата класс элемента комментарий
data class Comment(
    var id:Int = 0,
    var userId:Int = 0,
    var text:String = "",
    var date:String = "",
    var name:String = ""

)