package curruncy.converter.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.client.RestTemplate

@Configuration
class RestClientConfig {

    /**
     * FIXME: У тебя вызовы внешнего API разбросаны везде по коду.
     *        Хорошая практика - сделать какой-нибудь `RatesClient`, который инкапсулировал бы в себе всю работу с
     *        внешним сервисом и предоставлял бы остальному коду необходимые публичные методы.
     *
     * TODO: Задача со звездочкой: использовать WebClient (https://docs.spring.io/spring-boot/docs/current/reference/html/io.html#io.rest-client.webclient)
     */
    @Bean
    fun restClient(): RestTemplate {
        return RestTemplate()
    }
}
