package model

import play.api.libs.json.Json

/**
 * Created by Dmitry on 13.02.2016.
 */
case class Todo(id: Int, text: String, finished: Boolean, weight: Int)

object Todo {
  implicit val fmt = Json.format[Todo]

  def generateTodos(count: Int) = {
    (1 to count).toList.map {
      idx => Todo(idx, s"TodoItem $idx", false, idx)
    }
  }
}
