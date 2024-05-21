package curruncy.converter.coinbase

import org.springframework.boot.context.properties.ConfigurationProperties
import java.time.Duration

@ConfigurationProperties("api.coinbase")
data class CoinbaseProperties(
    val baseUrl: String,
    val timeout: Duration
)
