name := """todo-example"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  ws,
  filters,
  specs2 % Test,
  "com.h2database" % "h2" % "1.4.192",
  "org.postgresql" % "postgresql" % "9.3-1100-jdbc4",
  "com.typesafe.play" %% "play-slick" % "2.0.2",
  "com.typesafe.play" %% "play-slick-evolutions" % "2.0.2",
  "com.pauldijou" %% "jwt-play-json" % "0.7.1",
  "be.objectify" %% "deadbolt-scala" % "2.5.0"
)

resolvers += Resolver.url("Objectify Play Repository", url("http://deadbolt.ws/releases/"))(Resolver.ivyStylePatterns)
resolvers += "scalaz-bintray" at "http://dl.bintray.com/scalaz/releases"
resolvers += "maven-central" at "http://central.maven.org/maven2"

// Play provides two styles of routers, one expects its actions to be injected, the
// other, legacy style, accesses its actions statically.
routesGenerator := InjectedRoutesGenerator
