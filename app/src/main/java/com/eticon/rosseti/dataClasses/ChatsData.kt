package com.eticon.rosseti.dataClasses
//Дата класс элемента чат
data class ChatsData (
    var id: Int = 0,
    var name:String = "",
    var description:String = "",
    var category: String="",
    //var apps:UserApplication = UserApplication(),
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

val data = listOf<ChatsData>(
    ChatsData(0, "Тест 1", "Описание тестового обсуждения 1","Категория 1",2,4, listOf<Comment>()),
    ChatsData(0, "Тест 2", "Описание тестового обсуждения 2", "Категория 2",4,7, listOf<Comment>()),
    ChatsData(0, "Тест 3", "Описание тестового обсуждения 3", "Категория 3",6,5, listOf<Comment>()),
    ChatsData(0, "Тест 4", "Описание тестового обсуждения 4", "Категория 4",13,12, listOf<Comment>()),
    ChatsData(0, "Тест 5", "Описание тестового обсуждения 5", "Категория 5",1,2, listOf<Comment>())
)