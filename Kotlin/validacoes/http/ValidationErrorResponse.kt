package br.com.tauto.profissionalparceiro.api.http

import org.springframework.context.support.DefaultMessageSourceResolvable
import org.springframework.validation.FieldError
import java.util.*
import java.util.stream.Collectors

/**
 * Classe ValidationErrorResponse.
 */
class ValidationErrorResponse {

    val messages: MutableList<ValidationFieldError> = ArrayList()

    /**
     * Método cria uma lista de errors
     *
     * @param fieldErrors List<FieldError>
     */
    fun mapFromErrorList(fieldErrors: List<FieldError>) {
        val errorMap: Map<String, List<String>> = fieldErrors.stream().collect(
                Collectors.groupingBy(
                        FieldError::getField,
                        Collectors.mapping(
                                DefaultMessageSourceResolvable::getDefaultMessage,
                                Collectors.toList<String>()
                        )
                )
        )

        errorMap.forEach { fieldName, errors ->
            val error = ValidationFieldError()
            error.fieldName = fieldName
            error.addErrors(errors)
            messages.add(error)
        }
    }
}
