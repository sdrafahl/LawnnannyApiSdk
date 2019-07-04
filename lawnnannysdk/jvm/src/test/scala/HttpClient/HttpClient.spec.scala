package Lawnnanny.HttpClient

import java.io._
import org.scalatest.FunSpec
import org.scalatest._
import org.apache.http.impl.client.DefaultHttpClient
import org.apache.http.client.methods.HttpPost
import org.apache.http.impl.client.AbstractHttpClient
import org.apache.http.client.methods.HttpUriRequest
import org.apache.commons._
import org.apache.http._
import org.apache.http.client._
import Lawnnanny.HttpClient._
import org.scalamock.scalatest.MockFactory

class HttpClientSpec extends FunSpec with Matchers with MockFactory  {
    describe("HttpClient") {
        describe("post") {
            val testUrl = "testURl"
            val testBody = "testBody"
            val testHttpRequest = HttpRequest(testUrl, testBody)

            val httpClient: HttpClient = implicitly[HttpClient]
            implicit val mockedHttpClient : DefaultHttpClientWrapper = mock[DefaultHttpClientWrapper]

            val asdf = new HttpPost()

            it("Should execute the HTTPClient") {
                (mockedHttpClient.execute _)
                    .expects(where {
                        (httpPost: HttpPost) => {
                            val uri : String = httpPost.getURI.toString
                            uri == testUrl
                        }
                    })
                
                val post: HttpPost = new HttpPost(testUrl)
                httpClient.post(testHttpRequest)(mockedHttpClient)
            }
        }
    }
}