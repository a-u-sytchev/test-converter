package curruncy.converter.repository

import com.github.benmanes.caffeine.cache.Cache
import com.github.benmanes.caffeine.cache.Caffeine
import curruncy.converter.CacheProperties
import curruncy.converter.coinbase.CoinbaseClient
import curruncy.converter.coinbase.io.CurrencyUnit
import kotlinx.coroutines.runBlocking
import org.springframework.stereotype.Service
import java.math.BigDecimal

@Service
class CurrencyProvider(
    private val coinbaseClient: CoinbaseClient,
    cacheProps: CacheProperties,
) {
    private val currencyListCache: Cache<String, List<CurrencyUnit>> = Caffeine.newBuilder()
        .maximumSize(1)
        .expireAfterAccess(cacheProps.currencyList.expireAfterWrite)
        .build()

    private val ratesCache: Cache<String, Map<String, BigDecimal>> = Caffeine.newBuilder()
        .maximumSize(1)
        .expireAfterAccess(cacheProps.currencyList.expireAfterWrite)
        .build()

    fun currencyList(): List<CurrencyUnit> = currencyListCache.get("currencyList") {
        runBlocking { coinbaseClient.currencies() }
    }

    fun rates(currencyId: String): Map<String, BigDecimal> = ratesCache.get(currencyId) { key ->
        runBlocking { coinbaseClient.exchangeRates(key) }
    }
}
