package controllers

import be.objectify.deadbolt.scala.cache.HandlerCache
import com.google.inject.Inject
import model.{SiteProfile, UserInfo}
import model.db.Profile
import play.api.Logger
import play.api.libs.json.Json
import play.api.libs.ws.{WS, WSClient, WSResponse}
import play.api.mvc._
import security.JWTService
import services.ProfileServiceImpl

import scala.concurrent.Future

class LoginController @Inject() (
                                  jWTService: JWTService,
                                  handlers: HandlerCache,
                                  profileService: ProfileServiceImpl,
                                  ws: WSClient) extends Controller {

  implicit val context = scala.concurrent.ExecutionContext.Implicits.global

 /* /*Todo*/
  def ulogin(token: String) = Action.async { implicit request =>

    Logger.debug(s"Fetch ulogin data for token $token")

    val uloginRequest: Future[WSResponse] = ws
      .url("https://ulogin.ru/token.php")
      .withQueryString("host" -> "localhost", "token" -> token)
      .get()


    uloginRequest.flatMap {
      response =>
        val email  = (response.json \ "email").as[String]
        profileService.findProfileByEmail(email).flatMap {
          profile =>
            profile.map(Future.successful).getOrElse {

              profileService.createProfile(new Profile(0, email, "Unknown name"))
                .flatMap {
                r => profileService.findProfileByEmail(email)
                  .map(_.getOrElse( throw new RuntimeException("db error")))
              }

            }
        }
    }.map { profile =>
      jWTService.signResult(Ok(Json.toJson(Map("profile" -> profile.email))), profile)
    }

  }*/

  def googleLogin(token: String) = Action.async { implicit request =>

    Logger.debug(s"Fetch google data for token $token")

    val uloginRequest: Future[WSResponse] = ws
      .url("https://www.googleapis.com/oauth2/v3/tokeninfo")
      .withQueryString("id_token" -> token)
      .get()


    uloginRequest.flatMap {
      response =>

        Logger.debug(s"response: ${response.json}")
        val email  = (response.json \ "email").as[String]
        profileService.findProfileByEmail(email).flatMap {
          profile =>
            profile.map(Future.successful).getOrElse {

              profileService.createProfile(new Profile(0, email, "Unknown name"))
                .flatMap {
                  r => profileService.findProfileByEmail(email)
                    .map(_.getOrElse( throw new RuntimeException("db error")))
                }

            }
        }


    }.map { profile =>
      Ok(Json.toJson( UserInfo(true, Some( jWTService.encode(SiteProfile.fromDto(profile)) )) ))
    }

  }

}
