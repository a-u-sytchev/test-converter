package curruncy.converter.domain.currency

import kotlinx.serialization.Serializable

@Serializable
class CurrencyApiResponse(val data: List<CurrencyUnit>)