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


  class Todos(tag: Tag) extends Table[Todo](tag, "public.todo") {
    // Auto Increment the id primary key column
    def id = column[Long]("id", O.PrimaryKey, O.AutoInc)

    def text = column[String]("text")

    def finished = column[Boolean]("finished")

    def weight = column[Int]("weight")
    // the * projection (e.g. select * ...) auto-transforms the tupled
    // column values to / from a User
    def * = (id, text, finished, weight) <> ((Todo.apply _).tupled, Todo.unapply)
  }


  private val todos = TableQuery[Todos]

  override def todoList: Future[Seq[Todo]]= {

    val query = (for {
      todo <- todos
    } yield todo).sortBy(_.weight)

    db.run( query.result )

  }

  override def findTodoById(id: Long): Future[Option[Todo]] = {
    db.run(todos.filter(_.id === id).result.headOption)
  }

  override def addTodo(entity: Todo): Future[Unit] = {
    db.run(todos += entity).map( _ => () )
  }

  override def updateTodo(entity: Todo): Future[Unit] = {
    db.run(todos.filter(_.id === entity.id).update(entity)).map(_ => ())
  }

}
