package services

import com.google.inject.Inject
import model.SiteProfile
import model.db.Profile
import model.service.ProfileService
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.driver.JdbcProfile
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import scala.concurrent.Future




/**
  * Created by Dmitry on 13.02.2016.
  */


class ProfileServiceImpl @Inject()(protected val dbConfigProvider: DatabaseConfigProvider)
  extends ProfileService
    with HasDatabaseConfigProvider[JdbcProfile] {


  import driver.api._


  class Profiles(tag: Tag) extends Table[Profile](tag, "profile") {
    // Auto Increment the id primary key column
    def id = column[Long]("id", O.PrimaryKey, O.AutoInc)
    // The name can't be null
    def email = column[String]("email")
    // the * projection (e.g. select * ...) auto-transforms the tupled
    // column values to / from a User
    def * = (id, email) <> (Profile.tupled, Profile.unapply)
  }



  private val profiles = TableQuery[Profiles]

  def findProfileById(id: Long) = {
    db.run(profiles.filter(_.id === id).result.headOption)
  }

  def findProfileByEmail(email: String) = {
    db.run(profiles.filter(_.email === email).result.headOption)
  }

  def createProfile(entity: SiteProfile) = {

    findProfileByEmail(entity.email).flatMap {
      p =>  if( p.isEmpty ) {
        db.run(profiles += Profile(entity.id, entity.email)).map( r => () )
      } else Future.successful(None)
    }



  }


}
