package services

import com.google.inject.Inject
import model.service.ProfileService
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.driver.JdbcProfile
import slick.lifted.{TableQuery, Tag}
import slick.model.Table


/**
  * Created by Dmitry on 13.02.2016.
  */


class ProfileServiceImpl @Inject()(dbConfigProvider: DatabaseConfigProvider)
  extends ProfileService
    with HasDatabaseConfigProvider[JdbcProfile] {


  import driver.api._

  case class Profile(id: Int, email: String )


  class Profiles(tag: Tag) extends Table[Profile](tag, "profile") {
    // Auto Increment the id primary key column
    def id = column[Int]("id", O.PrimaryKey, O.AutoInc)
    // The name can't be null
    def email = column[String]("email")
    // the * projection (e.g. select * ...) auto-transforms the tupled
    // column values to / from a User
    def * = (id, email) <> (Profile.tupled, Profile.unapply)
  }



  private val profiles = TableQuery[Profiles]

  def findProfileById(id: Long) = {
    db.run(profiles.filter(_.id === 101).result.headOption)
  }

  /*def findProfileByEmail(email: String) = {
    DB.withConnection { implicit conn =>
      SQL("SELECT id, email" +
        " from public.profile where email = {email}").on(
        'email -> email
      ).as(User.fromDb.singleOpt)
    }

  }

  def createProfile(entity: User): Option[Long] = {

    if( findProfileByEmail(entity.email).isEmpty ) {
      DB.withConnection { implicit conn =>
        SQL("INSERT INTO public.profile(email) values({email})").on(
          'email -> entity.email
        ).executeInsert()
      }
    } else None

  }*/


}
