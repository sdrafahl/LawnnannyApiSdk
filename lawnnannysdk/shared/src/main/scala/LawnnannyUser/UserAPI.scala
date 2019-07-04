package Lawnnanny.User

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

object UserAPI {
  def registerUser(username: String, password: String, email: String) : Future[String] = Future { "test here" }
  def sayHello : String = "hello world"
}