package controllers

import be.objectify.deadbolt.scala.cache.HandlerCache
import be.objectify.deadbolt.scala.{ActionBuilders, DeadboltActions}
import com.google.inject.Inject
import model.db.Todo
import play.api.libs.json.Json
import play.api.mvc._
import services.TodoServiceImpl

class TodoController @Inject() (
                                 todoService: TodoServiceImpl,
                                 deadbolt: DeadboltActions, handlers: HandlerCache, actionBuilder: ActionBuilders
                                 ) extends Controller {

  implicit val context = scala.concurrent.ExecutionContext.Implicits.global

  def todoList =  /*deadbolt.SubjectPresent()()*/ Action.async { request =>
      todoService.todoList(None).map {
        todos =>
          val json = Json.toJson(todos)
          Ok(json)
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
