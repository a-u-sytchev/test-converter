package curruncy.converter.config.exchange_rates_api

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.client.reactive.ReactorClientHttpConnector
import org.springframework.web.reactive.function.client.WebClient
import reactor.netty.http.client.HttpClient
import java.time.Duration

@Configuration
class ExchangeRatesApiClientConfig(
    val props: ExchangeRatesApiProperties
) {

    @Bean("exchangeRatesApiClient")
    fun exchangeRatesApiClient(): WebClient {
        return WebClient.builder()
            .clientConnector(
                ReactorClientHttpConnector(
                    HttpClient.create()
                        .responseTimeout(Duration.ofMillis(props.timeout))
                )
            )
            .baseUrl(props.url)
            .build()
    }
}
