package Lawnnanny.HttpClient

import org.apache.http.client.methods.HttpPost
import org.apache.http.impl.client.DefaultHttpClient
import org.apache.http._
import org.apache.http.client._
import org.apache.http.message.BasicNameValuePair
import org.apache.http.client.entity.UrlEncodedFormEntity
import org.apache.http.HttpResponse

case class HttpRequest (url: String, body: String)

abstract class DefaultHttpClientWrapper {
    def execute(post: HttpPost) : Unit
}

object DefaultHttpClientWrapper {
    val defaultClient : DefaultHttpClient = new DefaultHttpClient()
    implicit def apply : DefaultHttpClientWrapper = new DefaultHttpClientWrapper {
        def execute(post: HttpPost) : Unit = {
            println("inside of execute")
            defaultClient.execute(post)
        }
    }
}

abstract class HttpClient {
    def post(request: HttpRequest)(implicit client: DefaultHttpClientWrapper) : String
}

object HttpClient {
    implicit def apply : HttpClient = new HttpClient {
        def post(request: HttpRequest)(implicit client: DefaultHttpClientWrapper) : String = {
            val post: HttpPost = new HttpPost(request.url)
            client.execute(post)
            "asdfasd"
        }
    }
}