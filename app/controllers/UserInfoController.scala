package controllers

import be.objectify.deadbolt.scala.DeadboltActions
import com.google.inject.Inject
import model.{SiteProfile, UserInfo}
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

/**
  * Created by xzwiex on 01.07.16.
  */
class UserInfoController @Inject()(deadbolt: DeadboltActions) extends Controller {

  def userInfo = deadbolt.WithAuthRequest()() { request =>
    Future {

      val userInfo =  request.subject.map {
        subject =>
          val user = subject.asInstanceOf[SiteProfile]
          UserInfo(true)
      }.getOrElse( UserInfo(false)  )

      Ok( Json.toJson( userInfo ) )
    }
  }
}
