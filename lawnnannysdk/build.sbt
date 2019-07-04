name := "Foo root project"

scalaVersion in ThisBuild := "2.12.4"

lazy val root = project.in(file(".")).
  aggregate(fooJS, fooJVM).
  settings(
    publish := {},
    publishLocal := {}
  )

lazy val foo = crossProject.in(file(".")).
  settings(
    name := "foo",
    version := "0.1-SNAPSHOT",
    libraryDependencies += "org.typelevel" %% "cats-effect" % "1.3.1",
    libraryDependencies += "org.apache.spark" %% "spark-core" % "2.4.3"
  ).
  jvmSettings(
    libraryDependencies += "org.scalactic" %% "scalactic" % "3.0.0",
    libraryDependencies +=  "org.scalatest" %% "scalatest" % "3.0.8" % "test",
    libraryDependencies += "io.spray" %%  "spray-json" % "1.3.5",
    resolvers += "Sonatype OSS Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots",
    libraryDependencies += "org.scalamock" %% "scalamock" % "4.3.0" % Test
  ).
  jsSettings(
  )

lazy val fooJVM = foo.jvm
lazy val fooJS = foo.js