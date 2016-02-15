package services

import model.{TodoEntity, Todo}
import play.api.db._
import play.api.Play.current

import anorm._
import anorm.SqlParser._


/**
 * Created by Dmitry on 13.02.2016.
 */





class TodoService {

  def todoList = {
    DB.withConnection { implicit  conn =>
      SQL("SELECT id, text, finished, weight from public.todo order by weight").as(TodoEntity.fromDb.*)
    }
  }

  def findTodoById(id: Long) = {
    DB.withConnection { implicit conn =>
      SQL("SELECT id, text, finished, weight from public.todo where id = {id}").on(
        'id -> id
      ).as(TodoEntity.fromDb.singleOpt)
    }

  }

  def addTodo(entity: TodoEntity): Option[Long] = {
    DB.withConnection { implicit conn =>
      SQL("INSERT INTO public.todo(text, finished, weight) values({text},{finished},{weight})").on(
        'text -> entity.text,
        'finished -> entity.finished,
        'weight -> entity.weight
      ).executeInsert()
    }
  }

  def updateTodo(entity: TodoEntity) = {
    DB.withConnection { implicit conn =>
      SQL("UPDATE public.todo set text={text}, finished={finished}, weight={weight} where id={id}").on(
        'text -> entity.text,
        'finished -> entity.finished,
        'weight -> entity.weight,
        'id -> entity.id
      ).executeUpdate()
    }
  }
}
