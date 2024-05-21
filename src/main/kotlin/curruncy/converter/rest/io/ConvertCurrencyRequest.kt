package curruncy.converter.rest.io

import curruncy.converter.domain.validator.CurrencyId
import curruncy.converter.domain.validator.CurrencyIdList
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import org.springframework.validation.annotation.Validated
import java.math.BigDecimal

@Validated
class ConvertCurrencyRequest(
    sourceCurrency: String?,
    amount: BigDecimal?,
    targetCurrency: Set<String>?,
) {
    @get:NotNull
    @get:CurrencyId
    val sourceCurrency: String = sourceCurrency ?: ""

    @get:NotNull
    @get:Min(0)
    val amount: BigDecimal = amount ?: BigDecimal.ZERO

    @get:NotNull
    @get:NotEmpty
    @get:CurrencyIdList
    val targetCurrency: Set<String> = targetCurrency ?: emptySet()
}
