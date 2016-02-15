package controllers
import com.google.inject.Inject
import model.Todo
import play.api.libs.json.{JsError, Json}
import play.api.mvc._
import services.{TodoEntity, TodoService}

class TodoController @Inject() (todoService: TodoService) extends Controller{

  def todoList = Action {
    val todos = todoService.todoList.map( Todo.fromDbEntity(_) )
    val json = Json.toJson(todos)
    Ok(json)
  }

  def addEntry = Action(parse.json) { request =>
    val result = request.body.validate[Todo].map {
      case (todo) =>
        val entityId = todoService.addTodo( TodoEntity(todo) )

        val entity = todoService.findTodoById(entityId)

        val dto: Option[Todo] = entity.map(Todo.fromDbEntity(_))
        Json.toJson( dto )
    }

    Ok(result.get)

  }

}
