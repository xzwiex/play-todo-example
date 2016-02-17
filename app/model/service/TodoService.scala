package model.service

import com.google.inject.ImplementedBy
import model.TodoEntity
import services.TodoServiceImpl

/**
 * Created by Dmitry on 17.02.2016.
 */
@ImplementedBy(classOf[TodoServiceImpl])
trait TodoService {
  def todoList: Seq[TodoEntity]

  def findTodoById(id: Long): Option[TodoEntity]

  def addTodo(entity: TodoEntity): Option[Long]

  def updateTodo(entity: TodoEntity): Int
}
