package org.pixelndice.table.pixelsite.constraint

import org.apache.commons.beanutils.BeanUtils

import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext

class FieldMatchValidator : ConstraintValidator<FieldMatch, Any> {

    private var firstFieldName: String? = null
    private var secondFieldName: String? = null
    private var message: String? = null

    override fun initialize(constraintAnnotation: FieldMatch) {
        firstFieldName = constraintAnnotation.first
        secondFieldName = constraintAnnotation.second
        message = constraintAnnotation.message
    }

    override fun isValid(value: Any, context: ConstraintValidatorContext): Boolean {
        var valid = true
        try {
            val firstObj = BeanUtils.getProperty(value, firstFieldName)
            val secondObj = BeanUtils.getProperty(value, secondFieldName)

            valid = firstObj == null && secondObj == null || firstObj != null && firstObj == secondObj
        } catch (ignore: Exception) {
            // ignore
        }

        if (!valid) {
            context.buildConstraintViolationWithTemplate(message)
                    .addPropertyNode(firstFieldName)
                    .addConstraintViolation()
                    .disableDefaultConstraintViolation()
        }

        return valid
    }
}