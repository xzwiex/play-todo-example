package model

import anorm.SqlParser._
import anorm.~
import be.objectify.deadbolt.core.models.{Permission, Subject}
import play.libs.Scala

/**
 *
 * @author Steve Chaloner (steve@objectify.be)
 */

class User(val id: Long, val email: String) extends Subject  {

  def getRoles: java.util.List[SecurityRole] = {
    Scala.asJava(Seq.empty)
  }

  def getPermissions: java.util.List[UserPermission] = {
    Scala.asJava(Seq.empty)
  }

  def getIdentifier: String = this.email
}

object User {
  val fromDb = {
    get[Long]("id") ~ get[String]("email") map {
      case id~email => new User(id,email)
    }
  }

}


class UserPermission(val value: String) extends Permission {
  def getValue: String = value
}
