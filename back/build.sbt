name := "back"

version := "1.0"

lazy val `back` = (project in file(".")).enablePlugins(PlayScala)

resolvers += "scalaz-bintray" at "https://dl.bintray.com/scalaz/releases"
resolvers += "Akka Snapshot Repository" at "http://repo.akka.io/snapshots/"
resolvers += "Atlassian Releases" at "https://maven.atlassian.com/public/"

scalaVersion := "2.12.2"

libraryDependencies ++= Seq(
  guice,
  ws,
)