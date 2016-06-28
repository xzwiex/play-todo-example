package controllers

import be.objectify.deadbolt.scala.DeadboltActions
import com.google.inject.Inject
import model.db.Todo
import model.service.TodoService
import play.api.libs.json.{JsError, Json}
import play.api.mvc._
import services.TodoServiceImpl

class TodoController @Inject() (todoService: TodoService, deadbolt: DeadboltActions) extends Controller {


  def todoList =  deadbolt.SubjectPresent() {
    Action.async { implicit request =>
      todoService.todoList.map {
        todos =>
          val json = Json.toJson(todos)
          Ok(json)
      }
    }
  }



  def addEntry() = Action.async(parse.json) { implicit  request =>

    val todo = request.body.validate[Todo].get

    todoService.addTodo(todo).map( x => Ok("") )

  }


  def updateEntry() = Action.async(parse.json) { request =>
    val todo = request.body.validate[Todo].get

    todoService.updateTodo(todo).map( x => Ok("") )
  }
}
