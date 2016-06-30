package model


import be.objectify.deadbolt.scala.models.{Permission, Role, Subject}
import model.db.Profile

/**
 *
 * @author Steve Chaloner (steve@objectify.be)
 */

class SiteProfile(val id: Long, val email: String) extends Subject  {

  override def identifier: String = this.email

  override def permissions: List[Permission] = List.empty

  override def roles: List[Role] = List.empty
}

object SiteProfile {
  def fromDto(dto:Profile) = {
    new SiteProfile(dto.id, dto.email)
  }

}


class UserPermission(val value: String) extends Permission {
  def getValue: String = value
}
