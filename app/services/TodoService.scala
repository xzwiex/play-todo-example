package services

import model.Todo
import play.api.db._
import play.api.Play.current

import anorm._
import anorm.SqlParser._


/**
 * Created by Dmitry on 13.02.2016.
 */


case class TodoEntity(id: Long, text: String, finished: Boolean, weight : Int)

object TodoEntity {
  val fromDb = {
    get[Long]("id") ~ get[String]("text") ~ get[Boolean]("finished")~ get[Int]("weight") map {
      case id~text~finished~weight => TodoEntity(id,text,finished,weight)
    }
  }

  def apply(dto:Todo) =  TodoEntity(dto.id.getOrElse(0), dto.text, dto.finished, dto.weight)


}


class TodoService {

  def todoList = {
    DB.withConnection { conn =>
      SQL("SELECT id, text, finished, weight from public.todo order by weight").as(TodoEntity.fromDb.*)
    }
  }

  def findTodoById(id: Int) = {
    DB.withConnection { conn =>
      SQL("SELECT id, text, finished, weight from public.todo where id = {id}").on(
        'id -> id
      ).as(TodoEntity.fromDb.singleOpt)
    }

  }

  def addTodo(entity: TodoEntity) = {
    DB.withConnection { conn =>
      SQL("INSERT INTO public.todo(text, finished, weight) values({text},{finished}, {weight)").on(
        'text -> entity.text,
        'finished -> entity.finished,
        'weight -> entity.weight
      ).executeUpdate()
    }
  }
}
