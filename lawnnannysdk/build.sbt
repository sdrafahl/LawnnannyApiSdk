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
    libraryDependencies += "org.typelevel" %% "cats-effect" % "1.3.1"
  ).
  jvmSettings(
    libraryDependencies += "org.scalactic" %% "scalactic" % "3.0.0",
    libraryDependencies +=  "org.scalatest" %% "scalatest" % "3.0.8" % "test",
  ).
  jsSettings(
  )

lazy val fooJVM = foo.jvm
lazy val fooJS = foo.js