package Lawnnanny.User

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import cats.effect.IO

trait UserAPIImplementation {
    def registerUser(username: String, password: String, email: String) : IO[String]
}

object UserAPIImplementation {
    implicit def apply : UserAPIImplementation = new UserAPIImplementation {
        def registerUser(username: String, password: String, email: String) : IO[String] = IO {"test"}
    }
}
  