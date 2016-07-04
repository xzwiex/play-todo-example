package security

import be.objectify.deadbolt.scala.models.Subject
import be.objectify.deadbolt.scala.{AuthenticatedRequest, DeadboltHandler, DynamicResourceHandler}
import model.SiteProfile
import play.Configuration
import play.api.mvc.{Request, Result, Results}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent._

/**
 *
 * @author Steve Chaloner (steve@objectify.be)
 */
class DefaultDeadboltHandler(dynamicResourceHandler: Option[DynamicResourceHandler] = None)
                       (implicit val jwtService: JWTService) extends DeadboltHandler {

  def beforeAuthCheck[A](request: Request[A]) = Future(None)

  override def getDynamicResourceHandler[A](request: Request[A]): Future[Option[DynamicResourceHandler]] = {
    Future(dynamicResourceHandler.orElse(Some(new DefaultDynamicResourceHandler())))
  }

  def getSubject[A](request: AuthenticatedRequest[A]): Future[Option[Subject]] = {


    Future.successful(
      request.headers.get("Authorization").flatMap { header =>
        jwtService.decode( header.replace("Bearer ", "") ).map( p => SiteProfile.fromJwt(p.profile))
      }

    )

  }

  override def onAuthFailure[A](request: AuthenticatedRequest[A]): Future[Result] = {
    Future {Results.Forbidden(views.html.accessFailed())}
  }
}