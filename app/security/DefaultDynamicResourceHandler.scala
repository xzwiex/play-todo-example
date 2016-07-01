package security

import be.objectify.deadbolt.scala.{AuthenticatedRequest, DeadboltHandler, DynamicResourceHandler}
import play.api.mvc.Request

import scala.collection.immutable.Map
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

/**
 *
 * @author Steve Chaloner (steve@objectify.be)
 */
class DefaultDynamicResourceHandler extends DynamicResourceHandler
{
  // todo implement this when demonstrating permissions
  def checkPermission[A](permissionValue: String, deadboltHandler: DeadboltHandler, request: Request[A]): Future[Boolean] = Future(false)

  override def isAllowed[A](name: String, meta: Option[Any],
                            deadboltHandler: DeadboltHandler, request: AuthenticatedRequest[A]): Future[Boolean] = {
    DefaultDynamicResourceHandler.handlers(name).isAllowed(name, meta,deadboltHandler, request)
  }

  override def checkPermission[A](permissionValue: String, meta: Option[Any],
                                  deadboltHandler: DeadboltHandler, request: AuthenticatedRequest[A]): Future[Boolean] = {
    Future.successful(true)
  }
}

object DefaultDynamicResourceHandler {
  val handlers: Map[String, DynamicResourceHandler] =
    Map( "pureLuck" -> new DefaultDynamicResourceHandler() )
}