package model.service

import com.google.inject.ImplementedBy
import model.db.Todo
import services.TodoServiceImpl

import scala.concurrent.Future

/**
 * Created by Dmitry on 17.02.2016.
 */
@ImplementedBy(classOf[TodoServiceImpl])
trait TodoService {
  def todoList(profileId: Option[Long]): Future[Seq[Todo]]

  def findTodoById(id: Long): Future[Option[Todo]]

  def addTodo(entity: Todo): Future[Todo]

  def updateTodo(entity: Todo): Future[Unit]
}
