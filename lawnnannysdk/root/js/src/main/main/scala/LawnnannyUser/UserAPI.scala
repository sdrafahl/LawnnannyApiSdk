package example

import scala.scalajs.js.annotation._
import Lawnnanny.User.UserAPIImplementation
import Lawnnanny.User._
import cats.effect.IO

@JSExportTopLevel("LawnnannySDK")
object LawnnannySDK {
  @JSExport
  def registerUser(username: String, password: String, email: String)(implicit userAPIImplementation: UserAPIImplementation): IO[String] = {
    userAPIImplementation.registerUser[IO](username, password, email)
  }
}