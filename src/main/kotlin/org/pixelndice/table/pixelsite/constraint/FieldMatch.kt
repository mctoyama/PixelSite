package org.pixelndice.table.pixelsite.constraint

import javax.validation.Payload
import javax.validation.Constraint
import java.lang.annotation.Documented
import java.lang.annotation.ElementType
import java.lang.annotation.Retention
import java.lang.annotation.ElementType.ANNOTATION_TYPE
import java.lang.annotation.ElementType.TYPE
import java.lang.annotation.RetentionPolicy.RUNTIME
import kotlin.reflect.KClass

@Target(AnnotationTarget.CLASS, AnnotationTarget.FILE, AnnotationTarget.ANNOTATION_CLASS)
@Constraint(validatedBy = arrayOf(FieldMatchValidator::class))
annotation class FieldMatch(val message: String = "", val groups: Array<KClass<*>> = arrayOf(), val payload: Array<KClass<out Payload>> = arrayOf(), val first: String, val second: String)

annotation class FieldsMatches(val value: Array<FieldMatch>)