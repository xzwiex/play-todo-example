package controllers

import be.objectify.deadbolt.scala.cache.HandlerCache
import com.google.inject.Inject
import play.api.libs.json.Json
import play.api.mvc._

class LoginController @Inject() (handlers: HandlerCache) extends Controller {

  /*Todo*/
  def ulogin = Action {
    implicit request =>
      Ok(views.html.index)
  }

}
