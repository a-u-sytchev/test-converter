package curruncy.converter.config.exchange_rates_api

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
data class ExchangeRatesApiProperties(
    @Value("\${api.exchange-rates.url}") val url: String
)
