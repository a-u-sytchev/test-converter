package curruncy.converter.domain.currency

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonNames

@Serializable
data class CurrencyUnit(
    val id: String,
    val name: String,
    @property:JsonNames("min_size") val minSize: Double
)
