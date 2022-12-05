package org.alebiadz.budget.tracker.web.controller.exception

import com.fasterxml.jackson.module.kotlin.MissingKotlinParameterException
import org.alebiadz.budget.tracker.dto.error.PropertyValidationErrorDto
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class ExceptionControllerAdvice {

    @ExceptionHandler(MissingKotlinParameterException::class)
    fun handle(e: MissingKotlinParameterException): ResponseEntity<List<PropertyValidationErrorDto>> {

        val error = PropertyValidationErrorDto(e.parameter.name!!, listOf("must not be null"))
        val body = listOf(error)
        return ResponseEntity.badRequest().body(body)
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handle(e: MethodArgumentNotValidException): ResponseEntity<List<PropertyValidationErrorDto>> {

        val errorsByField = e.bindingResult.fieldErrors.groupBy({ it.field }, { it.defaultMessage!! })
        val body = errorsByField.map { PropertyValidationErrorDto(it.key, it.value) }
        return ResponseEntity.badRequest().body(body)
    }

    @ExceptionHandler(Exception::class)
    fun handle(e: Exception): ResponseEntity<String> {

        return ResponseEntity.internalServerError().body(e.message)
    }
}