package Lawnnanny.HttpClient

import java.io._
import org.scalatest.FunSpec
import org.scalatest._
import Lawnnanny.HttpClient._
import scalaj.http._
import org.scalamock.scalatest.MockFactory

class HttpClientSpec extends FunSpec with Matchers with MockFactory  {
    describe("HttpClient") {
        val httpClient: HttpClient = implicitly[HttpClient]

        describe("post") {
            val testUrl = "testURl"
            val argument1 = "argument1"
            val value1 = "value1"
            val resultString = "resultString"
            val testCode = 200
            val testHeaders: Map[String, IndexedSeq[String]] = Map()
            val testBodyString = "body"
            val testBody : Seq[(String, String)] = Seq(argument1 -> value1)
            val testHttpRequest = HttpRequestInfo(testUrl, testBody)

            implicit val mockHttpMethod: HttpMethod[HttpRequest] = mock[HttpMethod[HttpRequest]]

            val mockHttpRequest1 = mock[HttpRequest]

            val mockHttpRequest2 = mock[HttpRequest]

            val fakeResponse : HttpResponse[String] = new HttpResponse[String](testBodyString, testCode, testHeaders)

            (mockHttpRequest2.asString _)
                .expects()
                .returning(fakeResponse)

            (mockHttpRequest1.postForm (_: Seq[(String, String)]))
                .expects(where {
                    (arguments: Seq[(String, String)]) => {
                        arguments.contains(argument1 -> value1)
                    }
                })
                .returning(mockHttpRequest2)

            (mockHttpMethod.apply _)
                .expects(where {
                    (uri: String) => {
                        uri == testUrl
                    }
                })
                .returning(mockHttpRequest1)

            it("Should execute the HTTPClient") {
                val returnedResponse = httpClient.post(testHttpRequest)
                assert(fakeResponse.body == testBodyString)
            }
        }
    }
}