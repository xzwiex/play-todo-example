package controllers

import model.UserInfo
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}

/**
  * Created by xzwiex on 01.07.16.
  */
class UserInfoController extends Controller {
  def userInfo = Action {
    val json = Json.toJson(UserInfo(false))
    Ok(json)
  }
}
