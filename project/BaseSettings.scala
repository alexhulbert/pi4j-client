import sbt.Keys._
import sbt._

object BaseSettings {

  lazy val javaagent = "-javaagent:" + System.getProperty("user.home") + "/.ivy2/cache/org.aspectj/aspectjweaver/jars/aspectjweaver-" + Version.aspectj + ".jar"

  lazy val defaultSettings =
    Seq(
      organization := "org.kaloz.pi4j.client",
      description := "Pi4j Client Project",
      scalaVersion := "2.11.7",
      licenses := Seq("Apache-2.0" -> url("http://www.apache.org/licenses/LICENSE-2.0.html")),
      homepage := Some(url("http://kaloz.org")),
      crossPaths := false,
      scalacOptions := Seq(
        "-encoding", "utf8",
        "-feature",
        "-unchecked",
        "-deprecation",
        "-target:jvm-1.8",
        "-language:postfixOps",
        "-language:implicitConversions"
      ),
      javacOptions := Seq(
        "-Xlint:unchecked",
        "-Xlint:deprecation"
      ),
      shellPrompt := { s => "[" + scala.Console.BLUE + Project.extract(s).currentProject.id + scala.Console.RESET + "] $ " }
    ) ++
      Resolvers.defaultSettings ++
      Aliases.aliases ++
      Publish.defaultSettings ++
      Release.defaultSettings

  //Required by Aspects - debug settings
  lazy val debugSettings = defaultSettings ++ Seq(
    javacOptions += "-g"
  )

  //Required by Aspects
  lazy val exampleSettings = defaultSettings ++ Seq(
    javaOptions in run ++= Seq(javaagent),
    fork in run := true
  )
}
