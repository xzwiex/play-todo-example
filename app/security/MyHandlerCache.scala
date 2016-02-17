package security

import javax.inject.Singleton

import be.objectify.deadbolt.scala.{HandlerKey, DeadboltHandler}
import be.objectify.deadbolt.scala.cache.HandlerCache

/**
 * @author Steve Chaloner (steve@objectify.be)
 */
@Singleton
class MyHandlerCache extends HandlerCache {

  val defaultHandler: DeadboltHandler = new MyDeadboltHandler

  override def apply(): DeadboltHandler = defaultHandler

  override def apply(handlerKey: HandlerKey) = defaultHandler
}
