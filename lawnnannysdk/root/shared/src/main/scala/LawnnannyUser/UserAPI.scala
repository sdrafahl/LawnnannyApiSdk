package Lawnnanny.User

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import cats.effect.IO
import Lawnnanny.HttpClient._

trait UserAPIImplementation {
    def registerUser(username: String, password: String, email: String)(implicit client: HttpClient) : IO[String]
}

object UserAPIImplementation {
    val lambdaUrl = "lambddas.lawnnanny.com"
    implicit def apply : UserAPIImplementation = new UserAPIImplementation {
        def registerUser(username: String, password: String, email: String)(implicit client: HttpClient) : IO[String] = {
            IO {
                val httpRequestInfo = HttpRequestInfo(lambdaUrl, Seq("username" -> username, "password" -> password, "email" -> email))
                client.post(httpRequestInfo).body
            }
        }
    }
}
  