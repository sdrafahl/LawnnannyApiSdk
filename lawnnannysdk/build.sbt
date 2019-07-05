import sbtcrossproject.CrossPlugin.autoImport.{crossProject, CrossType}

val sharedSettings = Seq(
  scalaVersion := "2.12.8",
  libraryDependencies += "org.typelevel" %% "cats-effect" % "1.3.1",
  libraryDependencies +=  "org.scalaj" %% "scalaj-http" % "2.4.2",
  libraryDependencies += "org.scalamock" %% "scalamock" % "4.3.0" % Test,
  resolvers += "Sonatype OSS Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots",
  libraryDependencies += "org.scalactic" %% "scalactic" % "3.0.0",
  libraryDependencies +=  "org.scalatest" %% "scalatest" % "3.0.8" % "test",
)

lazy val bar =
  crossProject(JSPlatform, JVMPlatform)
    .crossType(CrossType.Full)
    .settings(sharedSettings)
    .jsSettings()
    .jvmSettings(
    )

lazy val barJS     = bar.js
lazy val barJVM    = bar.jvm
