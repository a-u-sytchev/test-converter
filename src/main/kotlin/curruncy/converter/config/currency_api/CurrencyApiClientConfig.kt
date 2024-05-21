package curruncy.converter.config.currency_api

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.client.reactive.ReactorClientHttpConnector
import org.springframework.web.client.RestClient
import org.springframework.web.reactive.function.client.WebClient
import reactor.netty.http.client.HttpClient
import java.time.Duration

@Configuration
class CurrencyApiClientConfig(
    val props: CurrencyApiProperties
) {

    /**
     * FIXME: У тебя один API-endpoint, но ты зачем-то создаешь для него два клиента и два конфига.
     * FIXME: Предыдущее замечание про инкапсуляцию вызовов в отдельном классе осталось непонятым.
     */
    @Bean("currencyApiReactiveClient")
    fun currencyApiReactiveClient(): WebClient {
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

    /*
         Для валидации валют использую RestClient, а не WebClient, так как не смог найти способ использовать
         ConstraintValidator с реактивными ответами. Фреймворк не позволяет использовать .block(), а для использования
         .awaitBody() необходимо сделать метод isValid() suspended fun.

         В то же время, WebClient можно будет использовать в том же репозитории, там где потребуется реактивное получение данных.

         FIXME: В Kotlin есть обертка `runBlocking {...}` для вызова suspend-методов из обычных.
    */
    @Bean("currencyApiRestClient")
    fun currencyApiRestClient(): RestClient {
        return RestClient.builder().baseUrl(props.url).build()
    }
}
