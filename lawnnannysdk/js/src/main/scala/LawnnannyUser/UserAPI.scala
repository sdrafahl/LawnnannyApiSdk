package example

import scala.scalajs.js.annotation._
import Lawnnanny.User.UserAPI

@JSExportTopLevel("HelloWorld")
object HelloWorld {
  @JSExport
  def sayHello(): String = {
    UserAPI.sayHello
  }
}