package curruncy.converter.rest.io

import java.math.BigDecimal

data class ConvertedTargetCurrencyAmount(
    val currency: String,
    val amount: BigDecimal,
)
