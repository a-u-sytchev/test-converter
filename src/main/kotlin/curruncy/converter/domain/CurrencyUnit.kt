package curruncy.converter.domain

import com.fasterxml.jackson.annotation.JsonProperty

/**
 * TODO: Задача со звездочкой: использовать (везде в проекте) сериализацию Kotlin (https://kotlinlang.org/docs/serialization.html)
 */
data class CurrencyUnit(
    val id: String,
    val name: String,
    @JsonProperty("min_size") val minSize: Double
)
