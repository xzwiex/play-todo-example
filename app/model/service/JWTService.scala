package model.service

import com.google.inject.ImplementedBy
import model.{JwtInfo, SiteProfile}
import services.{JWTServiceImpl, ProfileServiceImpl}

/**
  * Created by xzwiex on 06.07.16.
  */

@ImplementedBy(classOf[JWTServiceImpl])
trait JWTService {

  def encode(p: SiteProfile): String

  def decode(token: String): Option[JwtInfo]
}
