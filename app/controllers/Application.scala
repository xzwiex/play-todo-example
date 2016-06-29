package controllers

import be.objectify.deadbolt.scala.DeadboltActions
import be.objectify.deadbolt.scala.cache.HandlerCache
import com.google.inject.Inject
import play.api.libs.json.Json
import play.api.mvc._

import scala.concurrent.Future

class Application @Inject() (handlers: HandlerCache,deadbolt: DeadboltActions) extends Controller {

  def index = deadbolt.WithAuthRequest()() { implicit authRequest =>
    Future.successful {
      Ok(views.html.index(handlers.apply()))
    }
  }


  def login = Action {
    implicit request =>
      val json = Json.toJson("OK")
      Ok(json).withSession(
        "profile" -> "test@mail.com"
      )
  }
}
