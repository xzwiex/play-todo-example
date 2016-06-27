package security

import be.objectify.deadbolt.scala.{DeadboltHandler, DynamicResourceHandler}
import model.{SiteProfile, SiteProfile$}
import play.api.mvc.{Request, Result, Results}
import be.objectify.deadbolt.core.models.Subject

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent._

/**
 *
 * @author Steve Chaloner (steve@objectify.be)
 */
class MyDeadboltHandler(dynamicResourceHandler: Option[DynamicResourceHandler] = None) extends DeadboltHandler {

  def beforeAuthCheck[A](request: Request[A]) = Future(None)

  override def getDynamicResourceHandler[A](request: Request[A]): Future[Option[DynamicResourceHandler]] = {
    Future(dynamicResourceHandler.orElse(Some(new MyDynamicResourceHandler())))
  }

  override def getSubject[A](request: Request[A]): Future[Option[Subject]] = {

    Future.successful(
      request.session.get("profileId").map { value =>
        new SiteProfile(value.toInt, value)
      }
    )

  }

  def onAuthFailure[A](request: Request[A]): Future[Result] = {
    Future {Results.Forbidden(views.html.accessFailed())}
  }
}