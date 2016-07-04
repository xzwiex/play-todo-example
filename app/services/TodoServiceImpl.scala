package services

import com.google.inject.Inject
import model.db.Todo
import model.service.TodoService
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.driver.JdbcProfile


import scala.concurrent.Future


/**
 * Created by Dmitry on 13.02.2016.
 */

class TodoServiceImpl @Inject()(protected val dbConfigProvider: DatabaseConfigProvider)
  extends TodoService
    with HasDatabaseConfigProvider[JdbcProfile] {

  implicit val context = scala.concurrent.ExecutionContext.Implicits.global

  import driver.api._


  class Todos(tag: Tag) extends Table[Todo](tag, "todo") {
    // Auto Increment the id primary key column
    def id = column[Long]("id", O.PrimaryKey, O.AutoInc)

    def text = column[String]("text")

    def profileId = column[Long]("profile_id")

    def finished = column[Boolean]("finished")

    def weight = column[Int]("weight")
    // the * projection (e.g. select * ...) auto-transforms the tupled
    // column values to / from a User
    def * = (id, text, finished, weight, profileId) <> ((Todo.apply _).tupled, Todo.unapply)
  }


  private val todos = TableQuery[Todos]

  private val insertQuery = todos returning todos.map(_.id) into ((item, id) => item.copy(id = id))


  override def todoList(profileId: Option[Long] = None): Future[Seq[Todo]]= {

    val query = (for {
      todo <- todos
    } yield todo).filter {
      todo => profileId.map( todo.profileId === _ ).getOrElse(true:Rep[Boolean])
    }.sortBy(_.weight)

    db.run( query.result )

  }

  override def findTodoById(id: Long): Future[Option[Todo]] = {
    db.run(todos.filter(_.id === id).result.headOption)
  }

  override def addTodo(entity: Todo): Future[Todo] = {
    val action = insertQuery += entity
    db.run(action)
  }

  override def updateTodo(entity: Todo): Future[Unit] = {
    db.run(todos.filter(_.id === entity.id).update(entity)).map(_ => ())
  }

}
