package services

import com.google.inject.Inject
import helpers.FakeAppGenerator
import model.db.Profile
import org.junit.runner.RunWith
import org.specs2.mutable.Specification
import org.specs2.runner.JUnitRunner
import play.api.db.slick.DatabaseConfigProvider
import play.api.test.Helpers._
import play.api.test.{FakeRequest, WithApplication}

/**
  * Created by xzwiex on 06.07.16.
  */

@RunWith(classOf[JUnitRunner])
class TestProfileService extends Specification {

  implicit val context = scala.concurrent.ExecutionContext.Implicits.global


  "ProfileService" should {

    "createProfile" in new WithApplication(FakeAppGenerator.application) {

      val dbConfig = app.injector.instanceOf(classOf[DatabaseConfigProvider])



      val profileService = new ProfileServiceImpl(dbConfig)

      private val testProfile: Profile = Profile(0, "test@test.com", "Unknonwn name")
      profileService.createProfile( testProfile ).map { r =>
        1 must equalTo(1)
      }

    }

    "render the index page" in new WithApplication{
      val home = route(FakeRequest(GET, "/")).get

      status(home) must equalTo(OK)
      contentType(home) must beSome.which(_ == "text/html")
      contentAsString(home) must contain ("Your new application is ready.")
    }
  }
}
