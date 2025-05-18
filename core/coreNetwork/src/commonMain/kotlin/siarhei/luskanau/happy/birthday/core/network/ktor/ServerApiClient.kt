package siarhei.luskanau.happy.birthday.core.network.ktor

import iarhei.luskanau.happy.birthday.core.network.SERVER_HOST
import iarhei.luskanau.happy.birthday.core.network.SERVER_PORT
import io.ktor.client.HttpClient
import io.ktor.client.plugins.websocket.WebSockets
import io.ktor.client.plugins.websocket.webSocket
import io.ktor.http.HttpMethod
import io.ktor.websocket.Frame
import io.ktor.websocket.close
import io.ktor.websocket.readText
import kotlin.coroutines.cancellation.CancellationException
import kotlin.time.Duration.Companion.seconds
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.serialization.json.Json
import org.koin.core.annotation.Single
import siarhei.luskanau.happy.birthday.core.network.ktor.model.AnniversaryDto

@Single
internal class ServerApiClient {

    private val httpClient: HttpClient by lazy {
        HttpClient {
            install(WebSockets)
        }
    }

    private val json: Json by lazy {
        Json {
            ignoreUnknownKeys = true
            prettyPrint = true
        }
    }

    suspend fun listenAnniversaryData(callback: (AnniversaryDto?) -> Unit) {
        while (true) {
            try {
                println("Connecting WebSocket")
                httpClient.webSocket(
                    method = HttpMethod.Get,
                    host = SERVER_HOST,
                    port = SERVER_PORT,
                    path = "/nanit"
                ) {
                    println("Listen incoming messages from WebSocket")
                    while (isActive) {
                        val textFrame = incoming.receive() as? Frame.Text
                        val jsonText = textFrame?.readText()
                        println("Message received: $jsonText")
                        val anniversaryDto = jsonText?.let { json.decodeFromString<AnniversaryDto>(it) }
                        callback.invoke(anniversaryDto)
                    }
                    close()
                }
            } catch (e: CancellationException) {
                throw e // cancellation exception is rethrown
            } catch (error: Throwable) {
                callback.invoke(null)
            }
            println("delay 3 seconds")
            delay(3.seconds)
        }
    }
}
