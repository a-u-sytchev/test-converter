package curruncy.converter.services.currency

import curruncy.converter.config.currency_api.CurrencyApiProperties
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate


@Service
class CurrencyApiDataFetcher(
    val currencyApiProperties: CurrencyApiProperties,
    val restClient: RestTemplate
) {
    /**
     * Список валют, где id является названием валюты в стандарте ISO 4217
     */
    /*
        Здесь мы понимаем, что изменение списка валют маловероятно, в связи с чем позволяем себе использовать
        делегат lazy, что "дергать" данные из API только 1 раз, а потом брать данные из инициализированной переменной.
        В то же время, на prod я бы использовал инструменты кеширования со сроком жизни кеша, чтоб время от времени
        обновлять данные, так как вероятность из обновления все же существует.
    */
    val currencyUnitList: List<String> by lazy {
        val response = restClient.getForObject(currencyApiProperties.url, CurrencyApiResponse::class.java)!!.data
        response.map { it.id }
    }
}
