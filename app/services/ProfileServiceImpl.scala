package services

import com.google.inject.Inject
import model.db.Profile
import model.service.ProfileService
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import slick.driver.JdbcProfile

import scala.concurrent.Future




/**
  * Created by Dmitry on 13.02.2016.
  */


class ProfileServiceImpl @Inject()(protected val dbConfigProvider: DatabaseConfigProvider)
  extends ProfileService
    with HasDatabaseConfigProvider[JdbcProfile] {


  import driver.api._


  /**
    * Profiles table Schema
    */


  class Profiles(tag: Tag) extends Table[Profile](tag, "profile") {
    // Auto Increment the id primary key column
    def id = column[Long]("id", O.PrimaryKey, O.AutoInc)
    // The name can't be null
    def email = column[String]("email")

    def name = column[String]("name")
    // the * projection (e.g. select * ...) auto-transforms the tupled
    // column values to / from a User
    def * = (id, email, name) <> ((Profile.apply _).tupled, Profile.unapply)
  }


  private val profiles = TableQuery[Profiles]

  private val insertQuery = profiles returning profiles.map(_.id) into ((item, id) => item.copy(id = id))



  /**
  *
    * Find user by id
    *
    * @param id: Long
    *
  * */

  override def findProfileById(id: Long) : Future[Option[Profile]] = {
    db.run(profiles.filter(_.id === id).result.headOption)
  }

  /**
    *
    * Find user by email
    *
    * @param email: Email
    *
    * */

  override def findProfileByEmail(email: String) : Future[Option[Profile]] = {
    db.run(profiles.filter(_.email === email).result.headOption)
  }

  /**
    *
    * Create user by email (if he doesn't exists - return None)
    *
    * @param entity: Profile
    *
    * */

  override def createProfile(entity: Profile) : Future[Option[Profile]] = {

    findProfileByEmail(entity.email).flatMap {
      _.map( p => Future.successful(None) ).getOrElse {
        val action = insertQuery += entity
        db.run(action).map(x => Some(x))
      }
    }

  }


}
