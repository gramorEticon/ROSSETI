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
    var comments:MutableList<Comment> = mutableListOf()


)
//Дата класс элемента комментарий
data class Comment(
    var id:Int = 0,
    var userId:Int = 0,
    var text:String = "",
    var date:String = "",
    var name:String = ""

)

val data = mutableListOf<ChatsData>(
    ChatsData(0, "Тест 1", "Описание тестового обсуждения 1","Категория 1",2,4, mutableListOf<Comment>(
            Comment(0,0,"Текст 1","Грива Егор","21:55"),
            Comment(0,0,"Текст 2", "Грива Егор", "21:59"),
            Comment(0,0,"Текст 3", "Грива Егор", "22:34")
    )),
    ChatsData(1, "Тест 2", "Описание тестового обсуждения 2", "Категория 2",4,7, mutableListOf<Comment>()),
    ChatsData(2, "Тест 3", "Описание тестового обсуждения 3", "Категория 3",6,5, mutableListOf<Comment>()),
    ChatsData(3, "Тест 4", "Описание тестового обсуждения 4", "Категория 4",13,12, mutableListOf<Comment>()),
    ChatsData(4, "Тест 5", "Описание тестового обсуждения 5", "Категория 5",1,2, mutableListOf<Comment>())
)