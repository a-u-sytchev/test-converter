package curruncy.converter.coinbase

import curruncy.converter.coinbase.io.CurrenciesResponse
import curruncy.converter.coinbase.io.CurrencyUnit
import curruncy.converter.coinbase.io.ExchangeRatesResponse
import io.netty.handler.logging.LogLevel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import org.springframework.http.client.reactive.ReactorClientHttpConnector
import org.springframework.http.codec.json.KotlinSerializationJsonDecoder
import org.springframework.http.codec.json.KotlinSerializationJsonEncoder
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.awaitBody
import reactor.netty.http.client.HttpClient
import reactor.netty.transport.logging.AdvancedByteBufFormat
import java.math.BigDecimal

@Service
class CoinbaseClient(
    webClientBuilder: WebClient.Builder,
    props: CoinbaseProperties,
) {
    private val json = Json {
        ignoreUnknownKeys = true
    }

    private val webClient = webClientBuilder
        .clientConnector(
            ReactorClientHttpConnector(
                HttpClient.create()
                    .wiretap(this::class.qualifiedName!!, LogLevel.DEBUG, AdvancedByteBufFormat.TEXTUAL)
                    .responseTimeout(props.timeout)
            )
        )
        .baseUrl(props.baseUrl)
        .codecs {
            it.defaultCodecs().kotlinSerializationJsonEncoder(KotlinSerializationJsonEncoder(json))
            it.defaultCodecs().kotlinSerializationJsonDecoder(KotlinSerializationJsonDecoder(json))
        }
        .build()

    suspend fun currencies(): List<CurrencyUnit> = withContext(Dispatchers.IO) {
        webClient
            .get()
            .uri("currencies")
            .retrieve()
            .awaitBody<CurrenciesResponse>()
            .data
    }

    suspend fun exchangeRates(currencyId: String): Map<String, BigDecimal> = withContext(Dispatchers.IO) {
        webClient
            .get()
            .uri {
                it.path("exchange-rates")
                    .queryParam("currency", currencyId)
                    .build()
            }
            .retrieve()
            .awaitBody<ExchangeRatesResponse>()
            .data.rates
    }
}
