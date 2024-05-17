package curruncy.converter.services.exchange_rates

import curruncy.converter.domain.CalculatedCurrencyCost
import curruncy.converter.domain.ExchangeRates
import java.math.BigDecimal
import java.math.RoundingMode
import java.text.DecimalFormat
import kotlin.math.round
import kotlin.math.roundToLong

/**
 * Расчитывает сколько можно купить каждой валюты на указанную сумму целевой валюты.
 * @param amount Сумма целевой валюты, для которой расчитывается конвертация
 */
fun ExchangeRates.convertAmount(amount: Double): CalculatedCurrencyCost {
    val convertedRates: MutableMap<String, Double> = mutableMapOf()
    for (rate in this.rates) {
        // С помощью BigDecimal приводим значение к денежному формату.
        convertedRates[rate.key] = BigDecimal(rate.value * amount)
            .setScale(2, RoundingMode.FLOOR).toDouble()
    }
    return CalculatedCurrencyCost(this.currency, convertedRates.toMap())
}
