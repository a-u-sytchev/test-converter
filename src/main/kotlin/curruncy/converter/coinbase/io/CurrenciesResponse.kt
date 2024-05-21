package curruncy.converter.coinbase.io

import kotlinx.serialization.Serializable

@Serializable
class CurrenciesResponse(val data: List<CurrencyUnit>)
