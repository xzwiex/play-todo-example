package controllers
import com.google.inject.Inject
import play.api.libs.json.Json
import play.api.mvc._
import services.TodoService

class TodoController @Inject() (todoService: TodoService) extends Controller{

  def list = Action {

    val todos = todoService.todoList
    Ok(Json.toJson(todos))
  }

}
