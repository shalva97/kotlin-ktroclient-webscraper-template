package misc

import io.ktor.client.features.cookies.*
import io.ktor.http.*

class RawCookieStore(private val cookiesStorage: CookiesStorage) : CookiesStorage by cookiesStorage {
    override suspend fun get(requestUrl: Url): List<Cookie> =
            cookiesStorage.get(requestUrl).map { it.copy(encoding = CookieEncoding.RAW) }
}