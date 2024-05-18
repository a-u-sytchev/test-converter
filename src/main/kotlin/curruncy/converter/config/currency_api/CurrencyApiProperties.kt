package curruncy.converter.config.currency_api

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

/**
 * TODO: Тут можно использовать `@ConfigurationProperties("api.currency")`, чтобы маппить весь класс целиком.
 */
@Component
data class CurrencyApiProperties(
    @Value("\${api.currency.url}") val url: String
)
