package controllers

import helpers.FakeAppGenerator
import org.junit.runner.RunWith
import org.specs2.mutable.Specification
import org.specs2.runner.JUnitRunner
import play.api.test.Helpers._
import play.api.test.{FakeRequest, WithApplication}
/**
 * Created by zwie on 06.07.16.
 */

@RunWith(classOf[JUnitRunner])
class ControllersSpec  extends Specification {


  "Controllers" should {

    "send 404 on a bad request" in new WithApplication(FakeAppGenerator.application) {
      route(app, FakeRequest(GET, "/boum")) must beSome.which(status(_) == NOT_FOUND)
    }

    "render the index page" in new WithApplication(FakeAppGenerator.application) {
      val home = route(app, FakeRequest(GET, "/")).get

      status(home) must equalTo(OK)
      contentType(home) must beSome.which(_ == "text/html")
      contentAsString(home) must contain("Todo application example")
    }
    /*

     "todo requests without auth should return 403"  in new WithApplication(FakeAppGenerator.application) {
       route(app, FakeRequest(GET, "/todo/list")) must beSome.which (status(_) == FORBIDDEN)
       route(app, FakeRequest(PUT, "/todo/add")) must beSome.which (status(_) == FORBIDDEN)
       route(app, FakeRequest(POST, "/todo/update")) must beSome.which (status(_) == FORBIDDEN)

     }*/

  }
}