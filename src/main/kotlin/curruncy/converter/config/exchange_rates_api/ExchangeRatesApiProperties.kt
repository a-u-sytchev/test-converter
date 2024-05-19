package curruncy.converter.config.exchange_rates_api

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties("api.exchange-rates")
data class ExchangeRatesApiProperties(
    val url: String,
    val timeout: Long
)
