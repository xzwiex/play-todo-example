package model

import anorm.SqlParser._
import anorm.~
import play.api.libs.json.Json

/**
 * Created by Dmitry on 13.02.2016.
 */
case class Todo(id: Option[Long], text: String, finished: Boolean, weight: Int)

object Todo {

  def fromDbEntity(dbEntity: TodoEntity) = new Todo(Some(dbEntity.id), dbEntity.text, dbEntity.finished, dbEntity.weight)

  implicit val fmt = Json.format[Todo]
}


case class TodoEntity(id: Long, text: String, finished: Boolean, weight : Int)

object TodoEntity {
  val fromDb = {
    get[Long]("id") ~ get[String]("text") ~ get[Boolean]("finished")~ get[Int]("weight") map {
      case id~text~finished~weight => TodoEntity(id,text,finished,weight)
    }
  }

  def apply(dto:Todo) =  new TodoEntity(dto.id.getOrElse(0), dto.text, dto.finished, dto.weight)


}