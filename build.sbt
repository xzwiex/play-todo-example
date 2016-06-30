name := """todo-example"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  jdbc,
  cache,
  ws,
  specs2 % Test,
  /*"com.typesafe.slick" %% "slick" % "3.1.1",*/
  "com.typesafe.play" %% "play-slick" % "2.0.2",
  "be.objectify" %% "deadbolt-scala" % "2.5.0"
)

/*pipelineStages := Seq(rjs)*/

resolvers += Resolver.url("Objectify Play Repository", url("http://deadbolt.ws/releases/"))(Resolver.ivyStylePatterns)
resolvers += "scalaz-bintray" at "http://dl.bintray.com/scalaz/releases"
resolvers += "maven-central" at "http://central.maven.org/maven2"

// Play provides two styles of routers, one expects its actions to be injected, the
// other, legacy style, accesses its actions statically.
routesGenerator := InjectedRoutesGenerator
