package model

import be.objectify.deadbolt.core.models.{Permission, Subject}
import model.db.Profile
import play.libs.Scala

/**
 *
 * @author Steve Chaloner (steve@objectify.be)
 */

class SiteProfile(val id: Long, val email: String) extends Subject  {

  def getRoles: java.util.List[SecurityRole] = {
    Scala.asJava(Seq.empty)
  }

  def getPermissions: java.util.List[UserPermission] = {
    Scala.asJava(Seq.empty)
  }

  def getIdentifier: String = this.email
}

object SiteProfile {
  def fromDto(dto:Profile) = {
    new SiteProfile(dto.id, dto.email)
  }

}


class UserPermission(val value: String) extends Permission {
  def getValue: String = value
}
