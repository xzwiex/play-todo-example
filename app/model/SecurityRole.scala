package model


import be.objectify.deadbolt.scala.models.Role

/**
 *
 * @author Steve Chaloner (steve@objectify.be)
 */

class SecurityRole(val roleName: String) extends Role  {
  override def name: String = roleName
}