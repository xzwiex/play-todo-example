package controllers
import com.google.inject.Inject
import model.Todo
import play.api.libs.json.Json
import play.api.mvc._
import services.{TodoEntity, TodoService}

class TodoController @Inject() (todoService: TodoService) extends Controller{

  def list = Action {
    val todos = todoService.todoList
    Ok(Json.toJson(todos))
  }

  def addEntry = Action(parse.json) { request =>
    val result = request.body.validate[Todo].flatMap {
      case (todo) =>

        val entityId = todoService.addTodo( TodoEntity(todo) )

        val entity = todoService.findTodoById(entityId)

        Ok( entity.map( e => Todo(e) ) )
    }
    
  }

}
