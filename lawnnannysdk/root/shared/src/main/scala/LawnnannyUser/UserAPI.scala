package Lawnnanny.User

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import cats.effect.IO
import cats.Monad
import cats.Functor
import cats.implicits._
import Lawnnanny.HttpClient._

trait UserAPIImplementation {
    def registerUser[F[+_] : Monad](username: String, password: String, email: String)(implicit client: HttpClient) : F[String]
}

object UserAPIImplementation {
    val lambdaUrl = "lambddas.lawnnanny.com"
    implicit def apply : UserAPIImplementation = new UserAPIImplementation {
        def registerUser[F[+_] : Monad](username: String, password: String, email: String)(implicit client: HttpClient) : F[String] = {
            val httpRequestInfo = HttpRequestInfo(lambdaUrl, Seq("username" -> username, "password" -> password, "email" -> email))
            for {
                response <- client.post(httpRequestInfo).body.pure[F]
            } yield response
        }
    }
}
  