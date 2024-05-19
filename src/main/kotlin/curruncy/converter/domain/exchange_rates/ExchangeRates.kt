package curruncy.converter.domain.exchange_rates

import curruncy.converter.config.serializer.BigDecimalSerializer
import kotlinx.serialization.Serializable
import java.math.BigDecimal

@Serializable
data class ExchangeRates(
    val currency: String,
    val rates: Map<String, @Serializable(with = BigDecimalSerializer::class) BigDecimal>
)
