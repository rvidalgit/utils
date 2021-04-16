package utils

import java.util.regex.Pattern

class ValidaCadastroUtils {

    companion object {

        /**
         * Valida um e-mail de acordo com o pattern.
         *
         * @param email String
         * @return Boolean
         * */
        fun validaEmail(email: String): Boolean =
                Pattern.compile("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}\$").matcher(email).matches()

        /**
         * Método responsável por verificar o telefone com o pattern.
         *
         * @param telefone String.
         * @return Boolean
         */
        fun validaTelefone(telefone: String): Boolean {

            return Pattern.compile(
                    "^\\(?([0-9]{2})\\)?[-.\\s]?([0-9]{4,5})[-.\\s]?([0-9]{4})\$"
            ).matcher(telefone).matches() && validaDigitosRepetidos(telefone, true)
        }

        /**
         * Método responsável por verificar se é uma sequência de números repetidos.
         *
         * @param numeros String.
         * @param isTelefone Boolean. Caso seja telefone ignora o DDD.
         * @return Boolean
         */
        fun validaDigitosRepetidos(numeros: String, isTelefone: Boolean = false): Boolean {

            val num = if (isTelefone) {
                numeros.subSequence(2, numeros.length)
            } else {
                numeros.subSequence(0, numeros.length)
            }

            var tam = num.length - 1

            while (tam > 0) {
                if (num[tam] != num[tam - 1]) {
                    return true
                }
                tam -= 1
            }
            return false
        }

    }
}
