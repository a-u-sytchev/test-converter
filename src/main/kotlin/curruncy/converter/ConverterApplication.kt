package curruncy.converter

import curruncy.converter.config.currency_api.CurrencyApiProperties
import curruncy.converter.config.exchange_rates_api.ExchangeRatesApiProperties
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication

// Добавил @EnableConfigurationProperties, чтоб использовать @ConfigurationProperties. Другого способа пока что не нашел.
@SpringBootApplication
@EnableConfigurationProperties(CurrencyApiProperties::class, ExchangeRatesApiProperties::class)
class ConverterApplication

fun main(args: Array<String>) {
	runApplication<ConverterApplication>(*args)
}
