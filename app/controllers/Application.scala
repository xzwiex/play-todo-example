package controllers

import be.objectify.deadbolt.scala.cache.HandlerCache
import com.google.inject.Inject
import play.api.libs.json.Json
import play.api.mvc._

class Application @Inject() (handlers: HandlerCache) extends Controller {

  def index = Action {
    implicit request =>
      Ok(views.html.index("Your new application is ready.", handlers.apply()))
  }


  def login = Action {
    implicit request =>
      val json = Json.toJson("OK")
      Ok(json).withSession(
        "profile" -> "test@mail.com"
      )
  }
}
