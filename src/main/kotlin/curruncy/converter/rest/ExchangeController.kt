package curruncy.converter.rest

import curruncy.converter.services.currency.CurrencyValidator
import curruncy.converter.services.exchange_rates.ExchangeCalculator
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@CrossOrigin // Пока что принимаются запросы с любого IP
@RestController
@RequestMapping("/api/v1/exchange")
class ExchangeController(
    val currencyValidator: CurrencyValidator,
    val exchangeCalculator: ExchangeCalculator
) {
    @GetMapping
    fun getCalculatedExchangeRates(
        @RequestParam(value = "currency") currencyIdList: List<String>,
        @RequestParam(value = "amount") amount: Double
    ): ResponseEntity<*> {
        // По-хорошему здесь еще бы возвращать ошибки если не указано ни одной целевой валюты или суммы для конвертации,
        // но в задании этого не требуется.
        if (!currencyValidator.allCurrenciesIdIsValid(currencyIdList)) return ResponseEntity.badRequest()
            .body("Неверно указан идентификатор одной или нескольких валют.")
        return ResponseEntity.ok(exchangeCalculator.calculateExchangeRatesForCurrencyList(currencyIdList, amount))
    }
}
