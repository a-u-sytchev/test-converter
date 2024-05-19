package curruncy.converter.services.currency

import curruncy.converter.domain.currency.ConvertCurrencyRequest
import curruncy.converter.domain.currency.ConvertedTargetCurrencyAmount
import curruncy.converter.repository.ExchangeRatesRepository
import org.springframework.stereotype.Service
import java.math.RoundingMode


@Service
class CurrencyExchangeCalculator(
    val exchangeRatesRepository: ExchangeRatesRepository
) {
    suspend fun calculateConversionCostForTargetCurrencies(data: ConvertCurrencyRequest): List<ConvertedTargetCurrencyAmount> {
        val exchangeRates = exchangeRatesRepository.getExchangeRatesByCurrency(data.sourceCurrency)
        return data.targetCurrency.map { ConvertedTargetCurrencyAmount(
            it,
            exchangeRates.rates[it]?.multiply(data.amount)?.setScale(2, RoundingMode.FLOOR)
                ?: throw IllegalArgumentException("Не обнаружена целевая валюта \"$it\" в списке котировок для исходной валюты \"${exchangeRates.currency}\".")
        ) }
    }
}
