package security

import javax.inject.Singleton

import be.objectify.deadbolt.scala.{DeadboltHandler, HandlerKey}
import be.objectify.deadbolt.scala.cache.HandlerCache
import com.google.inject.Inject
import play.Configuration

/**
 * @author Steve Chaloner (steve@objectify.be)
 */
@Singleton
class MyHandlerCache @Inject() (implicit val configuration: Configuration) extends HandlerCache {

  val defaultHandler: DeadboltHandler = new MyDeadboltHandler

  override def apply(): DeadboltHandler = defaultHandler

  override def apply(handlerKey: HandlerKey) = defaultHandler
}
