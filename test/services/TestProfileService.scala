package services

import helpers.FakeAppGenerator
import model.db.Profile
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
class TestProfileService extends Specification {

  implicit val context = scala.concurrent.ExecutionContext.Implicits.global


  "ProfileService" should {

    "should create and find profile by email and id" in new WithApplication(FakeAppGenerator.application)  {

      val dbConfig = app.injector.instanceOf(classOf[DatabaseConfigProvider])


      val profileService = new ProfileServiceImpl(dbConfig)

      private val testProfile: Profile = Profile(0, "test@test.com", "Unknonwn name")
      private val testProfile2: Profile = Profile(0, "test@test.com1", "Unknonwn name2")

      Await.result(profileService.createProfile( testProfile2 ), 5.seconds)

      Await.result(profileService.createProfile( testProfile ), 5.seconds)

      val createdProfile =   Await.result(profileService.findProfileByEmail(testProfile.email), 5.seconds)

      createdProfile.isDefined shouldEqual true

      val toEquals = testProfile.copy(id = createdProfile.get.id )
      toEquals shouldEqual createdProfile.get

      val byId = Await.result(profileService.findProfileById( toEquals.id ), 5.seconds )

      byId.isDefined shouldEqual true
      toEquals shouldEqual byId.get


    }
  }
}
