package curruncy.converter.domain

data class CalculatedCurrencyCost(
    val currency: String,
    val cost: Map<String, Double> // TODO: Возможно, стоит сразу везде использовать BigDecimal
)
