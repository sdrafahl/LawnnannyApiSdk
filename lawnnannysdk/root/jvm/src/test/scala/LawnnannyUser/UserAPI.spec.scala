package Lawnnanny.User

import org.scalatest.FunSpec
import org.scalatest._
import Lawnnanny.User.UserAPIImplementation._
import scalaj.http._
import org.scalamock.scalatest.MockFactory
import cats.effect.IO
import Lawnnanny.HttpClient._

class UserAPISpec extends FunSpec with Matchers with MockFactory   {
    describe("UserAPI") {
        describe("registerUser") {
            val testUsername = "testUserName"
            val testPassword = "secret"
            val testEmail = "test@mail.com"
            val lambdaUrl = "lambddas.lawnnanny.com"
            val testCode = 200
            val testHeaders: Map[String, IndexedSeq[String]] = Map()
            val testBodyString = "body"
            val userApiImplentation: UserAPIImplementation = implicitly[UserAPIImplementation]

            implicit val mockHttpClient = mock[HttpClient]

            val fakeResponse : HttpResponse[String] = new HttpResponse[String](testBodyString, testCode, testHeaders)

            (mockHttpClient.post (_:HttpRequestInfo)(_:HttpMethod[HttpRequest]))
                .expects(where {
                    (requestInfo: HttpRequestInfo, httpMethod: HttpMethod[HttpRequest]) => {
                        requestInfo.url == lambdaUrl
                    }
                })
                .returning(fakeResponse)

            it("Should make a call to the registration API") {
                val returnedPromise = userApiImplentation.registerUser[IO](testUsername, testPassword, testEmail)
                assert(testBodyString == returnedPromise.unsafeRunSync())
            }
        }
    }
}