package curruncy.converter.services.exchange_rates

import curruncy.converter.domain.CalculatedCurrencyCost
import curruncy.converter.domain.ExchangeRates
import org.springframework.stereotype.Service


@Service
class ExchangeCalculator(
    val exchangeRatesApiDataFetcher: ExchangeRatesApiDataFetcher
) {

    private fun calculateExchangeRatesByCurrency(currencyId: String, amount: Double): CalculatedCurrencyCost {
        // Получаем данные со списком котировок и используем функцию-расширение для конвертации
        return exchangeRatesApiDataFetcher.getExchangeRatesByCurrency(currencyId).convertAmount(amount)
    }

    fun calculateExchangeRatesForCurrencyList(currencyIdList: List<String>, amount: Double): List<CalculatedCurrencyCost> {
        return currencyIdList.map { calculateExchangeRatesByCurrency(it, amount) }
    }
}
