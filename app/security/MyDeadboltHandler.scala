package security

import be.objectify.deadbolt.scala.models.Subject
import be.objectify.deadbolt.scala.{AuthenticatedRequest, DeadboltHandler, DynamicResourceHandler}
import model.SiteProfile
import pdi.jwt.JwtJson
import play.{Configuration, Logger}
import play.api.mvc.{Request, Result, Results}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent._

/**
 *
 * @author Steve Chaloner (steve@objectify.be)
 */
class MyDeadboltHandler(dynamicResourceHandler: Option[DynamicResourceHandler] = None)
                       (implicit val configuration: Configuration) extends DeadboltHandler {

  def beforeAuthCheck[A](request: Request[A]) = Future(None)

  override def getDynamicResourceHandler[A](request: Request[A]): Future[Option[DynamicResourceHandler]] = {
    Future(dynamicResourceHandler.orElse(Some(new MyDynamicResourceHandler())))
  }

  def getSubject[A](request: AuthenticatedRequest[A]): Future[Option[Subject]] = {

    //JwtJson.decode()
    //Logger.debug(s"JWT key: ${configuration.getString("app.jwt.key")}")
    Future.successful(
      request.session.get("profileId").map { value =>
        new SiteProfile(value.toInt, value)
      }
    )

  }

  override def onAuthFailure[A](request: AuthenticatedRequest[A]): Future[Result] = {
    Future {Results.Forbidden(views.html.accessFailed())}
  }
}