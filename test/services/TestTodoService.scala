package services

import helpers.FakeAppGenerator
import model.db.{Todo, Profile}
import org.junit.runner.RunWith
import org.specs2.mutable.Specification
import org.specs2.runner.JUnitRunner
import play.api.db.slick.DatabaseConfigProvider
import play.api.test.WithApplication

import scala.concurrent.Await
import scala.concurrent.duration._

/**
 * Created by xzwiex on 06.07.16.
 */

@RunWith(classOf[JUnitRunner])
class TestTodoService extends Specification {

  implicit val context = scala.concurrent.ExecutionContext.Implicits.global


  "TodoServiceImpl" should {

    "should fetch todos and filter by id" in new WithApplication(FakeAppGenerator.application)  {

      val dbConfig = app.injector.instanceOf(classOf[DatabaseConfigProvider])

      val todoservice = new TodoServiceImpl(dbConfig)
      val profileService = new ProfileServiceImpl(dbConfig)

      private val testProfile: Profile = Profile(0, "test@test.com", "Unknonwn name")
      private val testProfile2: Profile = Profile(0, "test@test.com1", "Unknonwn name2")

      val firstProfile = Await.result(profileService.createProfile( testProfile ), 5.seconds).get
      val secondProfile = Await.result(profileService.createProfile( testProfile2 ), 5.seconds).get


      (1 to 5) foreach {
        x =>
          Await.result(todoservice.addTodo( Todo( 0, s"Todo for user ${firstProfile.email} #$x", true, 0, firstProfile.id ) ), 5.seconds)
      }

      (1 to 3) foreach {
        x =>
          Await.result(todoservice.addTodo( Todo( 0, s"Todo for user ${secondProfile.email} #$x", true, 0, secondProfile.id ) ), 5.seconds)
      }

      val allTodos = Await.result(todoservice.todoList(None), 5.seconds)

      allTodos.size shouldEqual 8

      val firstProfileTodos = Await.result(todoservice.todoList(Some(firstProfile.id)), 5.seconds)

      firstProfileTodos.size shouldEqual 5

    }
  }
}
