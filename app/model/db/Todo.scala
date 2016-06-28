package model.db

import play.api.libs.json.Json

/**
  * Created by xzwiex on 28.06.16.
  */
case class Todo(id: Long, text: String, finished: Boolean, weight : Int)

object Todo {
  implicit val fmt = Json.format[Todo]
}