package curruncy.converter

import org.springframework.boot.context.properties.ConfigurationProperties
import java.time.Duration

@ConfigurationProperties("cache")
data class CacheProperties(
    val currencyList: CacheExpire,
    val currencyRate: Cache,
) {
    data class Cache(
        val maximumSize: Long,
        val expireAfterWrite: Duration,
    )

    data class CacheExpire(
        val expireAfterWrite: Duration,
    )
}
