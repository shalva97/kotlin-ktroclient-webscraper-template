import io.ktor.client.*
import io.ktor.client.engine.*
import io.ktor.client.engine.okhttp.*
import io.ktor.client.features.cookies.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import misc.RawCookieStore
import misc.ignoreAllSSLErrors

val client = HttpClient(OkHttp) {
    install(HttpCookies) {
        storage = RawCookieStore(AcceptAllCookiesStorage())
    }
    install(JsonFeature) {
        serializer = KotlinxSerializer(kotlinx.serialization.json.Json {
            ignoreUnknownKeys = true
            isLenient = true
        })
    }
//    engine {
//        proxy = ProxyBuilder.http("https://192.168.0.104:8888")
//        config {
//            ignoreAllSSLErrors()
//        }
//    }
}