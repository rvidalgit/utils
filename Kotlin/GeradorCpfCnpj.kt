package br.com.tauto.profissionalparceiro.api.util

class GeradorCpfCnpj {

    /**
     * Gera um inteiro randômico
     * */
    private fun randomiza(n: Int): Int = Math.random().times(n).toInt()

    /**
     * Calcula o numero do digito verificador
     * */
    private fun mod(dividendo: Int, divisor: Int): Int = Math.round(dividendo - Math.floor((dividendo / divisor).toDouble()) * divisor).toInt()

    /**
     * Método que gera um CPF válido.
     * @param comPontuacao Boolean
     * @return String
     * */
    fun geraCpf(comPontuacao: Boolean): String {
        val n = 9
        val n1 = randomiza(n)
        val n2 = randomiza(n)
        val n3 = randomiza(n)
        val n4 = randomiza(n)
        val n5 = randomiza(n)
        val n6 = randomiza(n)
        val n7 = randomiza(n)
        val n8 = randomiza(n)
        val n9 = randomiza(n)
        var d1: Int = n9 * 2 + n8 * 3 + n7 * 4 + n6 * 5 + n5 * 6 + n4 * 7 + n3 * 8 + n2 * 9 + n1 * 10

        d1 = 11 - mod(d1, 11)

        if (d1 >= 10) {
            d1 = 0
        }

        var d2: Int = d1 * 2 + n9 * 3 + n8 * 4 + n7 * 5 + n6 * 6 + n5 * 7 + n4 * 8 + n3 * 9 + n2 * 10 + n1 * 11

        d2 = 11 - (mod(d2, 11))

        if (d2 >= 10) {
            d2 = 0
        }

        return if (comPontuacao) { //com pontuação
            "$n1$n2$n3.$n4$n5$n6.$n7$n8$n9-$d1$d2"
        } else { //sem pontuação
            "$n1$n2$n3$n4$n5$n6$n7$n8$n9$d1$d2"
        }

    }

    /**
     * Método que gera um CNPJ válido.
     * @param comPontuacao Boolean
     * @return String
     * */
    fun geraCNPJ(comPontuacao: Boolean): String {
        val n = 9
        val n1 = randomiza(n)
        val n2 = randomiza(n)
        val n3 = randomiza(n)
        val n4 = randomiza(n)
        val n5 = randomiza(n)
        val n6 = randomiza(n)
        val n7 = randomiza(n)
        val n8 = randomiza(n)
        val n9 = 0
        val n10 = 0
        val n11 = 0
        val n12 = 1
        var d1 = n12 * 2 + n11 * 3 + n10 * 4 + n9 * 5 + n8 * 6 + n7 * 7 + n6 * 8 + n5 * 9 + n4 * 2 + n3 * 3 + n2 * 4 + n1 * 5

        d1 = 11 - (mod(d1, 11))

        if (d1 >= 10)
            d1 = 0

        var d2 = d1 * 2 + n12 * 3 + n11 * 4 + n10 * 5 + n9 * 6 + n8 * 7 + n7 * 8 + n6 * 9 + n5 * 2 + n4 * 3 + n3 * 4 + n2 * 5 + n1 * 6

        d2 = 11 - (mod(d2, 11))

        if (d2 >= 10)
            d2 = 0


        return if (comPontuacao)
            "$n1$n2.$n3$n4$n5.$n6$n7$n8/$n9$n10$n11$n12-$d1$d2"
        else
            "$n1$n2$n3$n4$n5$n6$n7$n8$n9$n10$n11$n12$d1$d2"

    }

    fun removeCaracteresEspeciaisDocumento(documento: String): String {
        var doc = documento

        if (doc.contains(".")) {
            doc = documento.replace(".", "")
        }
        if (doc.contains("-")) {
            doc = doc.replace("-", "")
        }
        if (doc.contains("/")) {
            doc = doc.replace("/", "")
        }

        return doc
    }
}