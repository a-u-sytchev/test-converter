package curruncy.converter.rest

import curruncy.converter.domain.currency.ConvertCurrencyRequest
import curruncy.converter.services.currency.CurrencyExchangeCalculator
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@CrossOrigin // Пока что принимаются запросы с любого IP
@RestController
@RequestMapping("/api/v1/exchange")
class ExchangeController(
    val currencyExchangeCalculator: CurrencyExchangeCalculator
) {

    @GetMapping
    suspend fun getCalculatedConversionCostForTargetCurrencies(
            @Valid data: ConvertCurrencyRequest): ResponseEntity<*> {
        return ResponseEntity.ok(currencyExchangeCalculator.calculateConversionCostForTargetCurrencies(data))
    }
}
