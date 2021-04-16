package utils

import java.text.ParseException
import javax.swing.text.MaskFormatter

/**
 * Classe FormataCpfCnpj
 * */
class FormataCpfCnpj {

    /**
     * Formata o documento para exibição no relatório.
     *
     * @param doc String
     * @return String
     * @throws ParseException
     * */
    @Throws(ParseException::class)
    fun formatar(doc: String): String {
        val mask = if (doc.length == 11) {
            MaskFormatter("###.###.###-##")
        } else {
            MaskFormatter("##.###.###/####-##")
        }
        mask.valueContainsLiteralCharacters = false
        return mask.valueToString(doc)
    }
}
