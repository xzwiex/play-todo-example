package controllers

import be.objectify.deadbolt.scala.cache.HandlerCache
import be.objectify.deadbolt.scala.{ActionBuilders, DeadboltActions}
import com.google.inject.Inject
import model.{SiteTodo, SiteProfile}
import model.db.Todo
import play.api.libs.json.Json
import play.api.mvc._
import services.TodoServiceImpl

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future


class TodoController @Inject() (
                                 todoService: TodoServiceImpl,
                                 deadbolt: DeadboltActions, handlers: HandlerCache, actionBuilder: ActionBuilders
                                 ) extends Controller {


  def todoList =  deadbolt.SubjectPresent()() { request =>
    val profileId = request.subject.map(_.asInstanceOf[SiteProfile].id).get
    todoService.todoList(Some(profileId)).map {
      todos =>
        val json = Json.toJson(todos.map(SiteTodo.fromDto))
        Ok(json)
    }
  }



  def addEntry() =  deadbolt.SubjectPresent()(parse.json) { request =>

    val profileId = request.subject.map(_.asInstanceOf[SiteProfile].id).get
    val toCreate = request.body.validate[SiteTodo].get

    val entity = new Todo(0, toCreate.text, toCreate.finished, 0, profileId)

    todoService.addTodo(entity).map( todo => Ok(Json.toJson(SiteTodo.fromDto(todo))) )

  }


  def updateEntry() = deadbolt.SubjectPresent()(parse.json) { request =>

    val todo = request.body.validate[SiteTodo].get
    val profileId = request.subject.map(_.asInstanceOf[SiteProfile].id).get

    todoService.findTodoById( todo.id ).flatMap {
      _.map {
        dbTodo =>

          if (dbTodo.profileId != profileId) {
            Future.successful(Forbidden(Json.toJson(Map("status" -> "Access denied!") )))
          }
          else {

            val toUpdate = dbTodo.copy( finished = todo.finished, text = todo.text )
            todoService.updateTodo(toUpdate).map(x => Ok(Json.toJson(SiteTodo.fromDto(toUpdate))))
          }

      }.getOrElse( Future.successful(NotFound("Entity not found") ) )

      /**/
    }


  }
}
