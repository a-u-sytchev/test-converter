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
        // TODO: Это реализуется проще:
        //       `return currencyApiDataFetcher.currencyUnitList.containsAll(currencyIdList)`
        return currencyIdList.filter { it in currencyApiDataFetcher.currencyUnitList }.size == currencyIdList.size
    }
}
