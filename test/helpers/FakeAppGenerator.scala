package helpers

import java.io.File

import play.api.Mode
import play.api.inject.guice.GuiceApplicationBuilder

/**
  * Created by xzwiex on 06.07.16.
  */

object FakeAppGenerator {

  def application = new GuiceApplicationBuilder()
    .in(new File("conf/application.test.conf"))
    .in(Mode.Test)
    .build
}
