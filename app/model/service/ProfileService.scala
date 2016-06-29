package model.service

import com.google.inject.ImplementedBy
import model.db.Profile
import services.ProfileServiceImpl

import scala.concurrent.Future

/**
 * Created by Dmitry on 17.02.2016.
 */
@ImplementedBy(classOf[ProfileServiceImpl])
trait ProfileService {

  def findProfileById(id: Long) : Future[Option[Profile]]

  def findProfileByEmail(email: String) : Future[Option[Profile]]

  def createProfile(entity: Profile) : Future[Any]

}
