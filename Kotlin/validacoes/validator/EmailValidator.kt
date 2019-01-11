package br.com.tauto.profissionalparceiro.api.model.validator

import br.com.tauto.profissionalparceiro.api.exception.ValidationException
import br.com.tauto.profissionalparceiro.api.model.Email
import org.springframework.stereotype.Component
import org.springframework.validation.FieldError
import java.util.*
import java.util.regex.Pattern

/**
 * Classe EmailValidator responsável pela validação dos campos do E-mail.
 */
@Component
class EmailValidator {

    val fieldErrors = ArrayList<FieldError>()

    @Throws(ValidationException::class)
    fun validate(email: Email) {

        if (email.to.isEmpty() || email.to[0] == "") {
            fieldErrors.add(FieldError(Email::class.qualifiedName!!, "to", email.to, false, null, null, "O campo destinatário precisa ser preenchido."))
        }

        if (email.subject.isEmpty()) {
            fieldErrors.add(FieldError(Email::class.qualifiedName!!, "subject", email.subject, false, null, null, "O campo assunto precisa ser preenchido."))
        }

        if (email.messageContent.isEmpty()) {
            fieldErrors.add(FieldError(Email::class.qualifiedName!!, "messageContent", email.messageContent, false, null, null, "O campo mensagem precisa ser preenchido."))
        }

        if (fieldErrors.isNotEmpty()) {
            throw ValidationException(fieldErrors)
        }
    }

    @Throws(ValidationException::class)
    fun validatorListEmail(destinatarios: List<String>) {
        for (email in destinatarios) {
            val valido = pattern(email)
            if (!valido){
                fieldErrors.add(FieldError(Email::class.qualifiedName!!, "email", email, false, null, null, "Existe um ou mais e-mails inválidos."))
                throw ValidationException(fieldErrors)
            }
        }
    }

    fun pattern(email: String): Boolean {

        return Pattern.compile(
                "^(.+)@(.+)\\.(.+)\$"
        ).matcher(email).matches()
    }
}