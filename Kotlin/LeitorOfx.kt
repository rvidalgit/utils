package utils

import com.webcohesion.ofx4j.domain.data.MessageSetType
import com.webcohesion.ofx4j.domain.data.ResponseEnvelope
import com.webcohesion.ofx4j.domain.data.ResponseMessageSet
import com.webcohesion.ofx4j.domain.data.banking.BankStatementResponseTransaction
import com.webcohesion.ofx4j.domain.data.banking.BankingResponseMessageSet
import com.webcohesion.ofx4j.io.AggregateUnmarshaller
import com.webcohesion.ofx4j.io.OFXParseException
import java.io.Reader
import java.io.StringReader
import java.util.*

/**
 * Classe LeitorOfx - Responsável por interpretar o arquivo OFX.
 * bank.message.transactionList.transactions
 * implementation group: 'com.webcohesion.ofx4j', name: 'ofx4j', version: '1.24'
 * */
class LeitorOfx {

    /**
     * Recebe o conteudo da requisição.
     *
     * @param content String - Conteúdo em base64
     * @return MutableList<BankStatementResponseTransaction>?
     * @throws OFXParseException
     * */
    @Throws(OFXParseException::class)
    fun recebeConteudo(content: String): MutableList<BankStatementResponseTransaction> {
        val conteudo = String(Base64.getDecoder().decode(content))
        val reader: Reader = StringReader(conteudo)
        return leDados(reader)
    }

    /**
     * Converte o OFX em um {@see BankStatementResponseTransaction}.
     *
     * @param entrada Reader
     * @return MutableList<BankStatementResponseTransaction>?
     * @throws OFXParseException
     * */
    @Throws(OFXParseException::class)
    private fun leDados(entrada: Reader): MutableList<BankStatementResponseTransaction> {

        val aggregate: AggregateUnmarshaller<ResponseEnvelope> = AggregateUnmarshaller(ResponseEnvelope::class.java)

        val re: ResponseEnvelope = aggregate.unmarshal(entrada)
        val type: MessageSetType = MessageSetType.banking
        val message: ResponseMessageSet? = re.getMessageSet(type)

        return if (message != null) {
            (message as BankingResponseMessageSet).statementResponses
        } else {
            throw OFXParseException("Erro ao ler arquivo.")
        }

    }
}
