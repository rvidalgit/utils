package br.com.tauto.profissionalparceiro.api.model.validator

import org.springframework.stereotype.Component
import java.util.*
/**
 * classe CpfECnpjValidator responsável pela validação do documento.
 */
@Component
class CpfECnpjValidator {

    /**
     * Valida um CPF
     * @param documento String
     * @return Boolean
     * */
    fun isCPF(documento: String): Boolean {

        // considera-se erro CPF's formados por uma sequencia de numeros iguais e com número de digitos diferente de 11.
        if (documento == "00000000000" || documento == "11111111111" || documento == "22222222222" || documento == "33333333333" || documento == "44444444444"
                || documento == "55555555555" || documento == "66666666666" || documento == "77777777777" || documento == "88888888888" || documento == "99999999999" || documento.length != 11)
            return false

        try {
            val dig10: Char
            val dig11: Char
            var sm = 0
            var peso = 10
            var num: Int

            // Calculo do 1o. Digito Verificador
            for (i in 0..8) {
                Integer.parseInt(documento[i].toString()) //gera exception caso não seja um número
                num = (documento[i] - 48).toInt()
                sm += (num * peso)
                peso -= 1
            }

            var r = 11 - (sm % 11)

            dig10 = if ((r == 10) || (r == 11))
                '0'
            else
                (r + 48).toChar()

            // Calculo do 2o. Digito Verificador
            sm = 0
            peso = 11

            for (i in 0..9) {
                num = (documento[i] - 48).toInt()
                sm += (num * peso)
                peso -= 1
            }

            r = 11 - (sm % 11)

            dig11 = if ((r == 10) || (r == 11))
                '0'
            else
                (r + 48).toChar()

            return dig10 == documento[9] && dig11 == documento[10]

        } catch (ex: NumberFormatException) {
            return false
        }

    }

    /**
     * Valida um CNPJ
     * @param documento String
     * @return Boolean
     * */
    fun isCNPJ(documento: String): Boolean {

        if (documento == "00000000000000" || documento == "11111111111111" || documento == "22222222222222" || documento == "33333333333333" || documento == "44444444444444" || documento == "55555555555555" || documento == "66666666666666" || documento == "77777777777777" || documento == "88888888888888" || documento == "99999999999999" || documento.length != 14)
            return false

        try {
            val dig13: Char
            val dig14: Char
            var sm = 0
            var peso = 2
            var num: Int

            // Calculo do 1o. Digito Verificador
            for (i in 11 downTo 0) {
                Integer.parseInt(documento[i].toString()) //gera exception caso não seja um número
                num = (documento[i] - 48).toInt()
                sm += (num * peso)
                peso += 1

                if (peso == 10) peso = 2
            }

            var r = sm % 11

            dig13 = if ((r == 0) || (r == 1))
                '0'
            else
                ((11 - r) + 48).toChar()

            // Calculo do 2o. Digito Verificador
            sm = 0
            peso = 2

            for (i in 12 downTo 0) {
                num = (documento[i] - 48).toInt()
                sm += (num * peso)
                peso += 1

                if (peso == 10) peso = 2
            }

            r = sm % 11

            dig14 = if ((r == 0) || (r == 1))
                '0'
            else
                ((11 - r) + 48).toChar()

            return dig13 == documento[12] && dig14 == documento[13]

        } catch (ex: NumberFormatException) {
            return false
        }

    }
}