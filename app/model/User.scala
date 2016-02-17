package model

import be.objectify.deadbolt.core.models.Subject
import play.libs.Scala

/**
 *
 * @author Steve Chaloner (steve@objectify.be)
 */

class User(val userName: String) extends Subject  {
  def getRoles: java.util.List[SecurityRole] = {
    Scala.asJava(List(new SecurityRole("foo"),
                      new SecurityRole("bar")))
  }

  def getPermissions: java.util.List[UserPermission] = {
    Scala.asJava(List(new UserPermission("printers.edit")))
  }

  def getIdentifier: String = userName
}
