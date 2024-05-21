package curruncy.converter.domain.validator

import curruncy.converter.repository.CurrencyProvider
import jakarta.validation.Constraint
import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext
import jakarta.validation.Payload
import kotlin.reflect.KClass

@Target(AnnotationTarget.FIELD, AnnotationTarget.PROPERTY, AnnotationTarget.VALUE_PARAMETER, AnnotationTarget.FUNCTION,
    AnnotationTarget.LOCAL_VARIABLE, AnnotationTarget.PROPERTY_GETTER, AnnotationTarget.PROPERTY_SETTER,
    AnnotationTarget.TYPE
)
@Retention(AnnotationRetention.RUNTIME)
@Constraint(validatedBy = [CurrencyIdValidator::class])
annotation class CurrencyId(
    val message: String = "Указанный идентификатор валюты не существует или не соответствует стандарту ISO 4217.",
    val groups: Array<KClass<out Any>> = [],
    val payload: Array<KClass<out Payload>> = []
)

class CurrencyIdValidator(
    private val currencyProvider: CurrencyProvider,
): ConstraintValidator<CurrencyId, String> {
    override fun isValid(value: String, context: ConstraintValidatorContext): Boolean {
        return currencyProvider.currencyList().any { it.id == value }
    }
}

@Target(AnnotationTarget.FIELD, AnnotationTarget.PROPERTY, AnnotationTarget.VALUE_PARAMETER, AnnotationTarget.FUNCTION,
    AnnotationTarget.LOCAL_VARIABLE, AnnotationTarget.PROPERTY_GETTER, AnnotationTarget.PROPERTY_SETTER,
    AnnotationTarget.TYPE
)
@Retention(AnnotationRetention.RUNTIME)
@Constraint(validatedBy = [CurrencyIdListValidator::class])
annotation class CurrencyIdList(
    val message: String = "Не менее одного идентификатора валюты не существует или не соответствует стандарту ISO 4217.",
    val groups: Array<KClass<out Any>> = [],
    val payload: Array<KClass<out Payload>> = []
)

class CurrencyIdListValidator(
    private val currencyProvider: CurrencyProvider,
): ConstraintValidator<CurrencyIdList, Set<String>> {
    override fun isValid(value: Set<String>, context: ConstraintValidatorContext): Boolean {
        return currencyProvider.currencyList().map { it.id }.containsAll(value)
    }
}
