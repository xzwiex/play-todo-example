package services
import play.api.db._
import play.api.Play.current
import anorm._


/**
 * Created by Dmitry on 13.02.2016.
 */
class TodoService {

  def todoList = {


    DB.withConnection { conn =>

      SQL("SELECT id, text, finished from public.todo order by weight").foldWhile(List[Int, String, Boolean]) {
        (list, row) =>
          li
      }

    }
  }
}
