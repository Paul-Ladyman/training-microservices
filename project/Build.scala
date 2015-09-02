import sbt.Keys._
import sbt.{Build => SbtBuild, _}


object Build extends SbtBuild {
  val ScalaVersion = "2.10.4"
  val Json4sVersion = "3.2.10"

  lazy val project = Project (
    "microservices",
    file("."),
    settings =
      Project.defaultSettings ++
        Seq(
          resolvers += "BBC Forge Maven Releases" at "https://dev.bbc.co.uk/maven2/releases/",
          resolvers += "BBC Forge Artifactory" at "https://dev.bbc.co.uk/artifactory/bbc-repos/",
          resolvers += "rediscala" at "http://dl.bintray.com/etaty/maven"
        ) ++
        Seq(
          organization := "bbc",
          name := "microservices",
          version := "1.0.0",
          scalaVersion := ScalaVersion,
          resolvers += Classpaths.typesafeReleases,
          parallelExecution in Test := false,
          fork in Test := true,
          libraryDependencies ++= Seq(
            // Source dependencies
            "org.scalatest" %% "scalatest" % "2.2.4" % "test",
            "org.mockito" %  "mockito-core" % "1.9.5" % "test,it",
            "com.rabbitmq" % "amqp-client" % "3.3.4",
            "org.json4s" %% "json4s-native" % Json4sVersion,
            "org.json4s" %% "json4s-ext" % Json4sVersion
          )
        )
  ).configs(IntegrationTest).settings(Defaults.itSettings: _*)
}