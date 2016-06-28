package model.db

import play.api.libs.json.Json

/**
  * Created by xzwiex on 26.06.16.
  */

case class Profile(id: Long, email: String )

object Profile {
  implicit val fmt = Json.format[Profile]
}
