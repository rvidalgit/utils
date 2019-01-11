package br.com.tauto.profissionalparceiro.api.exception

import org.springframework.validation.FieldError

/**
 * Classe indicativa de erros de validação de requisições
 */
class ValidationException(val errors: List<FieldError>) : Throwable()