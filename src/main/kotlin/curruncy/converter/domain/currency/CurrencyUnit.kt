package curruncy.converter.domain.currency

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonNames

/**
 * FIXME: name и minSize нигде не используются. Их не обязательно маппить из ответа API.
 */
@Serializable
data class CurrencyUnit(
    val id: String,
    val name: String,
    @property:JsonNames("min_size") val minSize: Double
)
