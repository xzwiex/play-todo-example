package services

import model.Todo

/**
 * Created by Dmitry on 13.02.2016.
 */
class TodoService {
  def todoList = Todo.generateTodos(10)
}
