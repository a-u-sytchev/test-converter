package curruncy.converter.domain

data class ExchangeRates(
    val currency: String,
    val rates: Map<String, Double>
)
