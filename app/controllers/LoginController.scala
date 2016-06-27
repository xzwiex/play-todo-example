package controllers

import be.objectify.deadbolt.scala.cache.HandlerCache
import com.google.inject.Inject
import model.SiteProfile$

import play.api.Logger
import play.api.libs.json.Json
import play.api.libs.ws.{WSResponse, WS}
import play.api.mvc._
import play.api.Play.current
import services.ProfileServiceImpl

import scala.concurrent.Future

class LoginController @Inject() (handlers: HandlerCache, profileService: ProfileServiceImpl) extends Controller {

  implicit val context = scala.concurrent.ExecutionContext.Implicits.global

 /* /*Todo*/
  def ulogin(token: String) = Action.async {     implicit request =>

    Logger.debug(s"Fetch ulogin data for token $token")

    val uloginRequest: Future[WSResponse] = WS.url("https://ulogin.ru/token.php")
      .withQueryString("host" -> "localhost", "token" -> token).get()


    uloginRequest.map {
      response =>
        val email  = (response.json \ "email").as[String]

        val dbProfile : User = profileService.findProfileByEmail(email).getOrElse {
          val profileId = profileService.createProfile(new User(0, email))

          profileId.flatMap(profileService.findProfileById).getOrElse(throw new Error("DB error"))
        }


        Ok(Json.toJson(Map("profile" -> dbProfile.email))).withSession("profileId" -> dbProfile.id.toString)
    }

  }
*/
}
