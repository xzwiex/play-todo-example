package security

import java.util.Date
import javax.inject.Inject

import model.SiteProfile
import model.db.{JwtInfo, JwtProfile, Profile}
import pdi.jwt.{JwtAlgorithm, JwtJson}
import play.Configuration
import play.api.libs.json.Json
import play.api.mvc.Result

/**
 * Created by zwie on 01.07.16.
 */

class JWTService @Inject()(val configuration: Configuration) {

  val key = configuration.getString("app.jwt.key")
  val algo = JwtAlgorithm.HS256

  /*def signResult(r: Result, p: Profile) = {

    val token = encode(JwtInfo(p, new Date))

    r.withHeaders(( "Authorization", s"Bearer $token" ))
  }
*/
  def encode(p: SiteProfile) = {

    val claim = Json.toJson(JwtInfo(JwtProfile.fromSiteProfile(p), new Date)).toString()

    JwtJson.encode(claim, key, algo)
  }

  def decode(token: String): Option[JwtInfo] = {

    JwtJson.decodeJson(token, key, Seq(algo)).map {
      o => o.validate[JwtInfo].get
    }.toOption
  }
}