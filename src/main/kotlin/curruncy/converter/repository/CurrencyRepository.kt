package curruncy.converter.repository

import curruncy.converter.domain.currency.CurrencyApiResponse
import curruncy.converter.domain.currency.CurrencyUnit
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Service
import org.springframework.web.client.RestClient
import org.springframework.web.client.body

@Service
class CurrencyRepository(
    @Qualifier("currencyApiRestClient") private val client: RestClient
) {
    val currencyUnitList: List<CurrencyUnit> by lazy {
        client.get().uri("currencies").retrieve().body<CurrencyApiResponse>()!!.data
    }

    fun getCurrencyIdList(): List<String> {
        return currencyUnitList.map { it.id }
     }
}
