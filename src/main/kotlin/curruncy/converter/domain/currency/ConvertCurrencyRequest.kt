package curruncy.converter.domain.currency

import curruncy.converter.config.serializer.BigDecimalSerializer
import curruncy.converter.domain.validator.CurrencyId
import curruncy.converter.domain.validator.CurrencyIdList
import jakarta.validation.constraints.Min
import kotlinx.serialization.Serializable
import org.springframework.validation.annotation.Validated
import java.math.BigDecimal

/**
 * TODO: Я не уверен, использует ли Spring Kotlin-сериализацию под капотом для параметров и ответов REST-контролеров.
 *       В принципе, тут можно обойтись без нее, Spring отлично все маппит сам. Но раз сделал - хорошо.
 */
@Validated
@Serializable
class ConvertCurrencyRequest {
    /**
     * FIXME: lateinit - это звоночек о том, что код неправильно спроектирован.
     *        В 99% случаев Kotlin позволяет обойтись без этого.
     */
    @field:CurrencyId lateinit var sourceCurrency: String
    /**
     * FIXME: Если в API не указать параметр `amount`, то возникает ошибка
     *        `kotlin.UninitializedPropertyAccessException: lateinit property amount has not been initialized`
     */
    @field:Min(0) @Serializable(with = BigDecimalSerializer::class) lateinit var amount: BigDecimal
    @field:CurrencyIdList lateinit var targetCurrency: List<String>
}
