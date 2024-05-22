package curruncy.converter.services.currency

import curruncy.converter.repository.CurrencyProvider
import curruncy.converter.rest.io.ConvertedTargetCurrencyAmount
import org.springframework.stereotype.Service
import java.math.BigDecimal
import java.math.RoundingMode

@Service
class CurrencyExchangeCalculator(
    private val currencyProvider: CurrencyProvider,
) {
    suspend fun calculateConversionCostForTargetCurrencies(
        sourceCurrencyId: String,
        targetCurrencyId: Set<String>,
        amount: BigDecimal,
    ): List<ConvertedTargetCurrencyAmount> {
        val exchangeRates = currencyProvider.rates(sourceCurrencyId)

        return targetCurrencyId.map {
            ConvertedTargetCurrencyAmount(
                it,
                exchangeRates[it]
                    ?.multiply(amount)
                    ?.setScale(2, RoundingMode.FLOOR)
                    ?: throw IllegalArgumentException("Не обнаружена целевая валюта \"$it\" в списке котировок для исходной валюты \"$sourceCurrencyId\".")
            )
        }
    }
}
