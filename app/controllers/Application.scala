package controllers

import be.objectify.deadbolt.scala.DeadboltActions
import be.objectify.deadbolt.scala.cache.HandlerCache
import com.google.inject.Inject
import play.api.Configuration
import play.api.libs.json.Json
import play.api.mvc._

import scala.concurrent.Future

class Application @Inject() (
                              configuration : Configuration,
                              handlers: HandlerCache,
                              deadbolt: DeadboltActions) extends Controller {

  def index = deadbolt.WithAuthRequest()() { authRequest =>
    Future.successful {
      val googleId = configuration.getString("app.oauth.google.id").get
      Ok(views.html.index(handlers.apply(), googleId))
    }
  }

}
