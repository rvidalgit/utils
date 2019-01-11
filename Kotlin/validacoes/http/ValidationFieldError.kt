package br.com.tauto.profissionalparceiro.api.http

import java.util.*

/**
 * Contém o resultado de falhas de validação
 */
class ValidationFieldError {

    /**
     * Contém o nome do campo que não passou por uma validação
     */
    var fieldName: String? = null

    /**
     * Contém a mensagem de erro da validação
     */
    val errors: MutableList<String> = ArrayList()

    /**
     * Metodo responsável por adicionar uma lista de erros a uma lista de erros.
     *
     * @param errors List<String>
     * @return Boolean
     */
    fun addErrors(errors: List<String>) {
        this.errors.addAll(errors)
    }
}
