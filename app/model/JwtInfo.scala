package model

import java.util.Date

import play.api.libs.json.Json

/**
  * Created by xzwiex on 03.07.16.
  */

case class JwtProfile( id: Long, email: String )

case class JwtInfo(profile: JwtProfile, created : Date)

object JwtProfile {
  def fromSiteProfile(p: SiteProfile) = JwtProfile(p.id, p.email)
}

object JwtInfo {
  implicit val profileFmt = Json.format[JwtProfile]
  implicit val fmt = Json.format[JwtInfo]
}
