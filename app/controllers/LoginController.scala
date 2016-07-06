package controllers

import be.objectify.deadbolt.scala.cache.HandlerCache
import com.google.inject.Inject
import model.{SiteProfile, UserInfo}
import model.db.Profile
import model.service.JWTService
import play.api.Logger
import play.api.libs.json.Json
import play.api.libs.ws.{WS, WSClient, WSResponse}
import play.api.mvc._
import services.{JWTServiceImpl, ProfileServiceImpl}

import scala.concurrent.Future

class LoginController @Inject() (
                                  jWTService: JWTService,
                                  handlers: HandlerCache,
                                  profileService: ProfileServiceImpl,
                                  ws: WSClient) extends Controller {

  implicit val context = scala.concurrent.ExecutionContext.Implicits.global



  /**
    * Login or register user with Google oAuth
    *
    * @param token: String
    *
    * */

  def googleLogin(token: String) = Action.async { implicit request =>

    Logger.debug(s"Fetch google data for token $token")

    val googleRequest: Future[WSResponse] = ws
      .url("https://www.googleapis.com/oauth2/v3/tokeninfo")
      .withQueryString("id_token" -> token)
      .get()


    googleRequest.flatMap { response =>

        Logger.debug(s"Google oAuth response: ${response.json}")

        val email  = (response.json \ "email").as[String]
        profileService.findProfileByEmail(email).flatMap {
          profile =>
            profile.map(Future.successful).getOrElse {
              /*TODO: fetch name from JSON*/
              profileService.createProfile(new Profile(0, email, "Unknown name"))
                .map( _.getOrElse( throw new RuntimeException("db error")) )
            }
        }
    }.map { profile =>
      Ok(Json.toJson( UserInfo(true, Some( jWTService.encode(SiteProfile.fromDto(profile)) )) ))
    }

  }

}
