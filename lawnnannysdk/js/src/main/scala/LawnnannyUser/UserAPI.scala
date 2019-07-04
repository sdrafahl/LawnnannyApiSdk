package example

import scala.scalajs.js.annotation._
import Lawnnanny.User.UserAPIImplementation

@JSExportTopLevel("HelloWorld")
object HelloWorld {
  @JSExport
  def sayHello(): String = {
    "hello"
  }
}