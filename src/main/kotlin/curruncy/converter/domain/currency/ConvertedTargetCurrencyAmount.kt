package curruncy.converter.domain.currency

import curruncy.converter.config.serializer.BigDecimalSerializer
import kotlinx.serialization.Serializable
import java.math.BigDecimal

@Serializable
data class ConvertedTargetCurrencyAmount(
    val currency: String,
    @Serializable(with = BigDecimalSerializer::class) val amount: BigDecimal
)
