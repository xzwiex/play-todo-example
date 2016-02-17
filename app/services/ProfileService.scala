package services

import anorm._
import model.{User, TodoEntity}
import play.api.Play.current
import play.api.db._


/**
 * Created by Dmitry on 13.02.2016.
 */


class ProfileService {

  def findProfileById(id: Long) = {
    DB.withConnection { implicit conn =>
      SQL("SELECT id, email from public.profile where id = {id}").on(
        'id -> id
      ).as(User.fromDb.singleOpt)
    }

  }

  def findProfileByEmail(email: String) = {
    DB.withConnection { implicit conn =>
      SQL("SELECT id, email from public.profile where email = {email}").on(
        'email -> email
      ).as(User.fromDb.singleOpt)
    }

  }

  def addTodo(entity: User): Option[Long] = {

    if( findProfileByEmail(entity.email).isEmpty ) {
      DB.withConnection { implicit conn =>
        SQL("INSERT INTO public.profile(email) values({email})").on(
          'email -> entity.email
        ).executeInsert()
      }
    } else None

  }


}
