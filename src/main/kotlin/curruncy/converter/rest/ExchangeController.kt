package curruncy.converter.rest

import curruncy.converter.rest.io.ConvertCurrencyRequest
import curruncy.converter.rest.io.ConvertedTargetCurrencyAmount
import curruncy.converter.services.currency.CurrencyExchangeCalculator
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.*

@CrossOrigin // Пока что принимаются запросы с любого IP
@RestController
@RequestMapping("/api/v1")
class ExchangeController(
    val currencyExchangeCalculator: CurrencyExchangeCalculator,
) {
    @GetMapping("/exchange")
    @ResponseBody
    suspend fun getCalculatedConversionCostForTargetCurrencies(
        @Valid params: ConvertCurrencyRequest,
    ): List<ConvertedTargetCurrencyAmount> {
        return currencyExchangeCalculator.calculateConversionCostForTargetCurrencies(
            sourceCurrencyId = params.sourceCurrency,
            targetCurrencyId = params.targetCurrency,
            amount = params.amount,
        )
    }
}
