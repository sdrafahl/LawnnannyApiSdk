package Lawnnanny.User

import org.scalatest.FunSpec
import org.scalatest._
import Lawnnanny.User.UserAPIImplementation._
import cats.effect.IO

class UserAPISpec extends FunSpec with Matchers   {
    describe("UserAPI") {
        describe("registerUser") {
            val testUsername = "testUserName"
            val testPassword = "secret"
            val testEmail = "test@mail.com"
            val userApiImplentation: UserAPIImplementation = implicitly[UserAPIImplementation]

            it("Should return a IO of a string") {
                val returnedPromise = userApiImplentation.registerUser(testUsername, testPassword, testEmail)
                assert(returnedPromise.isInstanceOf[IO[_]])
            }
        }
    }
}