package security

import javax.inject.Singleton

import be.objectify.deadbolt.scala.{DeadboltHandler, HandlerKey}
import be.objectify.deadbolt.scala.cache.HandlerCache
import com.google.inject.Inject
import model.service.JWTService
import play.Configuration

/**
 * @author Steve Chaloner (steve@objectify.be)
 */
@Singleton
class DefaultHandlerCache @Inject() (implicit val jwtService: JWTService) extends HandlerCache {

  val defaultHandler: DeadboltHandler = new DefaultDeadboltHandler

  override def apply(): DeadboltHandler = defaultHandler

  override def apply(handlerKey: HandlerKey) = defaultHandler
}
