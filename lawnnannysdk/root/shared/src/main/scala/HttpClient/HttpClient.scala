package Lawnnanny.HttpClient

import scalaj.http._ 

case class HttpRequestInfo (url: String, body: Seq[(String, String)])

abstract class HttpClient {
    def post(request: HttpRequestInfo)(implicit httpMethod: HttpMethod[HttpRequest]) : HttpResponse[String]
}

object HttpClient {
    implicit def apply : HttpClient = new HttpClient {
        def post(request: HttpRequestInfo)(implicit httpMethod: HttpMethod[HttpRequest]) : HttpResponse[String] = {
            httpMethod(request.url).postForm(request.body).asString
        }
    }
}

abstract class HttpMethod[F] {
    def apply(uri: String) : F
}

object HttpMethod {
    implicit def apply : HttpMethod[HttpRequest] = new HttpMethod[HttpRequest] {
        def apply(uri: String) = Http(uri)
    }
}