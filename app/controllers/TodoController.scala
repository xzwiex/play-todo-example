package controllers


import com.google.inject.Inject
import model.db.Todo
import play.api.libs.json.Json
import play.api.mvc._
import security.Secured
import services.TodoServiceImpl

class TodoController @Inject() ( todoService: TodoServiceImpl ) extends Controller with Secured  {

  implicit val context = scala.concurrent.ExecutionContext.Implicits.global

  def todoList =  Action.async { request =>
      todoService.todoList.map {
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
