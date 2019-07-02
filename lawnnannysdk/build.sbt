enablePlugins(ScalaJSPlugin)

name := "Scala.js Tutorial"
scalaVersion := "2.12.8"

scalaJSLinkerConfig ~= { _.withModuleKind(ModuleKind.ESModule) }
