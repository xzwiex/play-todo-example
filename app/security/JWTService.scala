package security

import javax.inject.Inject

import model.db.Profile
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

  def signResult(r: Result, p: Profile) = {

    val token = encode(p)

    r.withHeaders(( "Authorization", s"Bearer $token" ))
  }

  def encode(p: Profile) = {

    val claim = Json.toJson(p).toString()

    JwtJson.encode(claim, key, algo)
  }

  def decode(token: String) = {
    JwtJson.decodeJson(token, key, Seq(algo))
  }
}