package model.db

import javax.inject.{Singleton, Inject}
import scala.concurrent.Future
import play.api.db.slick.{HasDatabaseConfigProvider, DatabaseConfigProvider}
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import slick.driver.JdbcProfile

/**
  * Created by xzwiex on 21.06.16.
  */

trait UsersComponent {
  class UserTable extends Table(tag: Tag)[(Int, String)](tag, "public.profile")  {


  }

}

