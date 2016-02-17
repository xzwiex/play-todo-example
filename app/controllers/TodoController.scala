package controllers

import be.objectify.deadbolt.scala.DeadboltActions
import com.google.inject.Inject
import model.service.TodoService
import model.{TodoEntity, Todo}
import play.api.libs.json.{JsError, Json}
import play.api.mvc._
import services.TodoServiceImpl

class TodoController @Inject() (todoService: TodoService, deadbolt: DeadboltActions) extends Controller {

  def todoList = deadbolt.SubjectPresent() {
    Action {
      val todos = todoService.todoList.map(Todo.fromDbEntity)
      val json = Json.toJson(todos)
      Ok(json)
    }
  }

  def addEntry() = Action(parse.json) { request =>
    val result = request.body.validate[Todo].map {
      case (todo) =>
        val entityId = todoService.addTodo(TodoEntity(todo))

        val entity = todoService.findTodoById(entityId.get)

        val dto: Option[Todo] = entity.map(Todo.fromDbEntity)
        Json.toJson(dto)
    }

    Ok(result.get)

  }

  def updateEntry() = Action(parse.json) { request =>
    val result = request.body.validate[Todo].map {
      case (todo) =>

        if (todo.id.isEmpty) {
          /*TODO*/
          Json.toJson(todo)
        } else {
          todoService.updateTodo(TodoEntity(todo))
          Json.toJson(todo)
        }

    }

    Ok(result.get)

  }
}
