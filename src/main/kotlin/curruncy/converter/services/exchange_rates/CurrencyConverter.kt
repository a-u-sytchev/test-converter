package curruncy.converter.services.exchange_rates

import curruncy.converter.domain.CalculatedCurrencyCost
import curruncy.converter.domain.ExchangeRates
import java.text.DecimalFormat
import kotlin.math.round
import kotlin.math.roundToLong

/**
 * Конвертирует указанную сумму в каждую валюту и возвращает перечень стоимости целевой валюты по отношению к остальным.
 * @param amount Сумма, котора будет конвертирована
 */
fun ExchangeRates.convertAmount(amount: Double): CalculatedCurrencyCost {
    val convertedRates: MutableMap<String, Double> = mutableMapOf()
    for (rate in this.rates) {
        convertedRates[rate.key] = rate.value * amount
    }
    return CalculatedCurrencyCost(this.currency, convertedRates.toMap())
}
