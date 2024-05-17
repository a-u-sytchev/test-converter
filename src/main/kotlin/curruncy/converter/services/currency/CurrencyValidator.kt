package curruncy.converter.services.currency

import org.springframework.stereotype.Service

@Service
class CurrencyValidator(
    val currencyApiDataFetcher: CurrencyApiDataFetcher
) {
    /**
     * Проверяется что все указанные id валют реальные и соответствуют стандарту ISO 4217
     */
    fun allCurrenciesIdIsValid(currencyIdList: List<String>): Boolean {
        return currencyIdList.filter { it in currencyApiDataFetcher.currencyUnitList }.size == currencyIdList.size
    }
}
