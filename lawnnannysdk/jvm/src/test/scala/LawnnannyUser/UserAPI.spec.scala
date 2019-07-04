package Lawnnanny.User

import org.scalatest.FunSpec
import org.scalatest._
import Lawnnanny.User._
import scala.concurrent.Future

class UserAPISpec extends FunSpec with Matchers   {
    describe("UserAPI") {
        describe("registerUser") {
            println("tests")
            val testUsername = "testUserName"
            val testPassword = "secret"
            val testEmail = "test@mail.com"

            it("Should return a future") {
                val returnedPromise = UserAPI.registerUser(testUsername, testPassword, testEmail)
                assert(false)
            }
        }
    }
}