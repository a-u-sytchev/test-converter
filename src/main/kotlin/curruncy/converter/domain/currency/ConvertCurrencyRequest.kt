package curruncy.converter.domain.currency

import curruncy.converter.config.serializer.BigDecimalSerializer
import curruncy.converter.domain.validator.CurrencyId
import curruncy.converter.domain.validator.CurrencyIdList
import jakarta.validation.constraints.Min
import kotlinx.serialization.Serializable
import org.springframework.validation.annotation.Validated
import java.math.BigDecimal

@Validated
@Serializable
class ConvertCurrencyRequest {
    @field:CurrencyId lateinit var sourceCurrency: String
    @field:Min(0) @Serializable(with = BigDecimalSerializer::class) lateinit var amount: BigDecimal
    @field:CurrencyIdList lateinit var targetCurrency: List<String>
}
