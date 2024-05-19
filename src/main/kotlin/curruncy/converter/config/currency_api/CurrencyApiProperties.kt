package curruncy.converter.config.currency_api

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties("api.currency")
data class CurrencyApiProperties(
    val url: String,
    val timeout: Long
)
