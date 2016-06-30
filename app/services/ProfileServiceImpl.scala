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


  class Profiles(tag: Tag) extends Table[Profile](tag, "public.profile") {
    // Auto Increment the id primary key column
    def id = column[Long]("id", O.PrimaryKey, O.AutoInc)
    // The name can't be null
    def email = column[String]("email")
    // the * projection (e.g. select * ...) auto-transforms the tupled
    // column values to / from a User
    def * = (id, email) <> ((Profile.apply _).tupled, Profile.unapply)
  }


  private val profiles = TableQuery[Profiles]

  override def findProfileById(id: Long) : Future[Option[Profile]] = {
    db.run(profiles.filter(_.id === id).result.headOption)
  }

  override def findProfileByEmail(email: String) : Future[Option[Profile]] = {
    db.run(profiles.filter(_.email === email).result.headOption)
  }

  override def createProfile(entity: Profile) : Future[Any] = {

    findProfileByEmail(entity.email).flatMap {
      p =>  if( p.isEmpty ) {
        db.run(profiles += entity).map( r => () )
      } else Future.successful(None)
    }

  }


}
