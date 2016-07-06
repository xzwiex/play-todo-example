package helpers

import java.io.File

import com.google.inject.Inject
import org.slf4j.LoggerFactory
import play.api.cache.CacheApi
import play.api.db.DBApi
import play.api.db.evolutions.Evolutions
import play.api.inject.ApplicationLifecycle
import play.api.inject.guice.GuiceApplicationBuilder
import play.api.{Application, Logger, Mode}

import scala.concurrent.Future
import scala.concurrent.duration.Duration
import play.api.inject.bind

/**
 * Created by xzwiex on 06.07.16.
 */

object FakeAppGenerator {

  val log = LoggerFactory.getLogger(getClass)

  def application = {

    log.debug(s"Creating fake app...")


    new GuiceApplicationBuilder()
      .overrides(bind[CacheApi].to[FakeCache])
      .in(new File("conf/application.conf"))
      .configure("slick.dbs.default.db.url" -> "jdbc:postgresql://localhost:5432/todo_test")
      .configure("play.evolutions.autoApply" -> "true")
      .in(Mode.Test)
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



/*http://stackoverflow.com/questions/31041842/error-with-play-2-4-tests-the-cachemanager-has-been-shut-down-it-can-no-longe*/

class FakeCache extends CacheApi {
  override def set(key: String, value: Any, expiration: Duration): Unit = {}

  override def get[T](key: String)(implicit evidence$2: ClassManifest[T]): Option[T] = None

  override def getOrElse[A](key: String, expiration: Duration)(orElse: => A)(implicit evidence$1: ClassManifest[A]): A = orElse

  override def remove(key: String): Unit = {}
}