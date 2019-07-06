import sbtcrossproject.CrossPlugin.autoImport.{crossProject, CrossType}
import java.io._

val sharedSettings = Seq(
  scalaVersion := "2.12.8",
  libraryDependencies += "org.typelevel" %% "cats-effect" % "1.3.1",
  libraryDependencies +=  "org.scalaj" %% "scalaj-http" % "2.4.2",
  libraryDependencies += "org.scalamock" %% "scalamock" % "4.3.0" % Test,
  resolvers += "Sonatype OSS Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots",
  libraryDependencies += "org.scalactic" %% "scalactic" % "3.0.0",
  libraryDependencies +=  "org.scalatest" %% "scalatest" % "3.0.8" % "test",
)

val cleanProject = taskKey[Unit]("Deletes files produced by the build, metals, such as generated sources, compiled classes, and task caches.")

cleanProject := {
  clean.value
  IO.delete(Set(
    "./src",
    "./target"
  ).map((fileName) => {
    new File(fileName)
  })
  )
}

lazy val root =
  crossProject(JSPlatform, JVMPlatform)
    .crossType(CrossType.Full)
    .settings(sharedSettings)
    .jsSettings()
    .jvmSettings()

lazy val rootJS     = root.js
lazy val rootJVM    = root.jvm
