name := """todo-example"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.6"

libraryDependencies ++= Seq(
  jdbc,
  cache,
  ws,
  evolutions,
  specs2 % Test,
  "com.typesafe.play" %% "anorm" % "2.4.0",
  "org.webjars" %% "webjars-play" % "2.4.0-1",
  "org.webjars" % "angularjs" % "1.5.0",
  "org.webjars" % "bootstrap" % "3.3.6",
  "be.objectify" %% "deadbolt-scala" % "2.4.3"
)

pipelineStages := Seq(rjs)

resolvers += Resolver.url("Objectify Play Repository", url("http://deadbolt.ws/releases/"))(Resolver.ivyStylePatterns)
resolvers += "scalaz-bintray" at "http://dl.bintray.com/scalaz/releases"
resolvers += "maven-central" at "http://central.maven.org/maven2"

// Play provides two styles of routers, one expects its actions to be injected, the
// other, legacy style, accesses its actions statically.
routesGenerator := InjectedRoutesGenerator
