package controllers

import com.google.inject.Inject
import play.api.libs.json.Json
import play.api.mvc._

import scala.concurrent.Future

class Application @Inject() () extends Controller {

  def index = Action.async { implicit authRequest =>
    Future.successful {
      Ok(views.html.index())
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
