package model

import play.api.libs.json.Json
import services.TodoEntity

/**
 * Created by Dmitry on 13.02.2016.
 */
case class Todo(id: Option[Long], text: String, finished: Boolean, weight: Int)

object Todo {

  def fromDbEntity(dbEntity: TodoEntity) = new Todo(Some(dbEntity.id), dbEntity.text, dbEntity.finished, dbEntity.weight)

  implicit val fmt = Json.format[Todo]
}
