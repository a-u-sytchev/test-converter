package curruncy.converter.repository

import curruncy.converter.domain.exchange_rates.ExchangeRates
import curruncy.converter.domain.exchange_rates.ExchangeRatesApiResponse
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.awaitBody

@Service
class ExchangeRatesRepository(
    @Qualifier("exchangeRatesApiClient") private val client: WebClient
) {
    suspend fun getExchangeRatesByCurrency(currencyId: String): ExchangeRates {
        return client.get().uri("exchange-rates?currency={currencyId}", currencyId).retrieve()
            .awaitBody<ExchangeRatesApiResponse>().data
    }
}
