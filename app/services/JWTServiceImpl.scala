package services

import java.util.Date
import javax.inject.Inject

import model.service.JWTService
import model.{JwtInfo, JwtProfile, SiteProfile}
import pdi.jwt.{JwtAlgorithm, JwtJson}
import play.Configuration
import play.api.libs.json.Json

/**
  * Created by zwie on 01.07.16.
  */

class JWTServiceImpl @Inject()(val configuration: Configuration) extends JWTService{

  private val key = configuration.getString("app.jwt.key")
  private val algo = JwtAlgorithm.HS256


  /**
    *
    * Encode SiteProfile entity to token
    *
    * @param profile: SiteProfile
    *
    * */

  def encode(profile: SiteProfile): String = {
    val claim = Json.toJson(JwtInfo(JwtProfile.fromSiteProfile(profile), new Date)).toString()
    JwtJson.encode(claim, key, algo)
  }

  /**
    *
    * Try to decode SiteProfile entity from token string
    *
    * @param token : String
    *
    * */

  def decode(token: String): Option[JwtInfo] = {

    JwtJson.decodeJson(token, key, Seq(algo)).map {
      o => o.validate[JwtInfo].get
    }.toOption
  }
}