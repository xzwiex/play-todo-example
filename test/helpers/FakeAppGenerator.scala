package helpers

import java.io.File

import com.google.inject.Inject
import org.slf4j.LoggerFactory
import play.api.db.DBApi
import play.api.db.evolutions.Evolutions
import play.api.inject.ApplicationLifecycle
import play.api.inject.guice.GuiceApplicationBuilder
import play.api.{Application, Logger}

import scala.concurrent.Future


/**
 * Created by xzwiex on 06.07.16.
 */

object FakeAppGenerator {

  val log = LoggerFactory.getLogger(getClass)

  lazy val application = {

    log.debug(s"Creating fake app...")


    new GuiceApplicationBuilder()
      .in(new File("conf/application.conf"))
      .configure("slick.dbs.default.db.url" -> "jdbc:postgresql://localhost:5432/todo_test")
      .configure("play.evolutions.autoApply" -> "true")
      /*.bindings(bind[DatabaseClearer].toSelf.eagerly())*/
      .build
  }



}


/*https://groups.google.com/forum/#!msg/play-framework/4BMmhqR33Wg/kUMSruBl-p4J*/
class DatabaseClearer @Inject()(lifecycle: ApplicationLifecycle, app: Application) {

  val logger = Logger(this.getClass)

  lifecycle.addStopHook { () =>
    Future.successful {

      val db = app.injector.instanceOf(classOf[DBApi])

      Evolutions.cleanupEvolutions(db.databases().head)
      logger.info("Stopping")
      None
    }
  }
}