package curruncy.converter.domain

import com.fasterxml.jackson.annotation.JsonProperty

data class CurrencyUnit(
    val id: String,
    val name: String,
    @JsonProperty("min_size") val minSize: Double
)