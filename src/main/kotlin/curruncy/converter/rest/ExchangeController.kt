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
    /**
     * FIXME: Наверное, задача была неправильно понята или плохо сформулирована.
     *        Должно быть три параметра:
     *          1) исходная валюта (одна);
     *          2) сумма;
     *          3) список целевых валют;
     *        В ответе должны быть сконвертированные суммы только для целевых валют, т.е., если передали
     *          1) RUB
     *          2) 100
     *          3) [USD, EUR, BYN]
     *        то в ответе должно быть 3 суммы для USD, EUR и BYN.
     *        У тебя же получилось, что для списка исходных валют сумма конвертируется во все остальные существующие
     *        валюты.
     */
    @GetMapping
    fun getCalculatedExchangeRates(
        @RequestParam(value = "currency") currencyIdList: List<String>,
        @RequestParam(value = "amount") amount: Double
    ): ResponseEntity<*> {
        // По-хорошему здесь еще бы возвращать ошибки если не указано ни одной целевой валюты или суммы для конвертации,
        // но в задании этого не требуется.
        // TODO: Задача со звездочкой: сделать Spring-валидатор для проверки валют.
        if (!currencyValidator.allCurrenciesIdIsValid(currencyIdList)) return ResponseEntity.badRequest()
            .body("Неверно указан идентификатор одной или нескольких валют.")
        return ResponseEntity.ok(exchangeCalculator.calculateExchangeRatesForCurrencyList(currencyIdList, amount))
    }
}
