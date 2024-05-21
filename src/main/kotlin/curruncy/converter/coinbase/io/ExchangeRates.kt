package curruncy.converter.coinbase.io

import curruncy.converter.serialization.BigDecimalSerializer
import kotlinx.serialization.Serializable
import java.math.BigDecimal

@Serializable
data class ExchangeRates(
    val currency: String,
    val rates: Map<String, @Serializable(with = BigDecimalSerializer::class) BigDecimal>
)
