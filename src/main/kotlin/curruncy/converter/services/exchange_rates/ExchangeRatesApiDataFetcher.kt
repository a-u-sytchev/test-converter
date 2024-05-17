package curruncy.converter.services.exchange_rates

import curruncy.converter.config.exchange_rates_api.ExchangeRatesApiProperties
import curruncy.converter.domain.ExchangeRates
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate

@Service
class ExchangeRatesApiDataFetcher(
    val exchangeRatesApiProperties: ExchangeRatesApiProperties,
    val restClient: RestTemplate
) {
    fun getExchangeRatesByCurrency(currencyId: String): ExchangeRates {
        val url = "${exchangeRatesApiProperties.url}?currency=$currencyId"
        val response = restClient.getForObject(url, ExchangeRatesApiResponse::class.java)!!.data
        return response
    }
}
