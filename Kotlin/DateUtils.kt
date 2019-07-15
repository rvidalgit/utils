package br.com.tauto.profissionalparceiro.api.utils

import org.joda.time.DateTime
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import javax.xml.datatype.DatatypeFactory
import javax.xml.datatype.XMLGregorianCalendar


/**
 * Classe DateUtils
 *
 * Responsável por manipular datas.
 * */
class DateUtils {

    /**
     * Retorna o primeiro dia do mês anterior.
     *
     * @return Date
     * */
    fun getPrimeiroDiaDoMesAnterior(): Date {
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.MONTH, -1)
        calendar.set(Calendar.DAY_OF_MONTH, 1)
        return calendar.time
    }

    /**
     * Retorna o primeiro dia do mês anterior com a hora zerada. Ex: 2018/10/01 00:00:00
     *
     * @param data Date - Data de referência
     * @return Date
     */
    fun getPrimeiroDiaDoMesAnteriorComMinimaHora(data: Date): Date {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.MONTH, DateTime(data).monthOfYear - 1)
        calendar.set(Calendar.YEAR, DateTime(data).year)
        calendar.add(Calendar.MONTH, -1)
        calendar.set(Calendar.DAY_OF_MONTH, 1)
        return arredondaDataZerandoHora(calendar.time)
    }

    /**
     * Retorna o último dia do mês anterior.
     *
     * @return Date
     * */
    fun getUltimoDiaDoMesAnterior(): Date {
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.MONTH, -2)
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH))
        return calendar.time
    }

    /**
     * Retorna o último dia do próximo mês com a hora máxima. Ex: 2018/12/31 23:59:59
     *
     * @param data Date - Data de referência
     * @return Date
     * */
    fun getUltimoDiaDoProximoMesComMaximaHora(data: Date): Date {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.MONTH, DateTime(data).monthOfYear - 1)
        calendar.set(Calendar.YEAR, DateTime(data).year)
        calendar.add(Calendar.MONTH, +1)
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH))
        return arredondaDataComMaximaHora(calendar.time)
    }

    /**
     * Arredonta a data desconsiderando a hora, minutos, segundos e milisegundos. Exemplo: 14/10/2012 10:11:12 -> 14/10/2012 00:00:00.
     *
     * @param data Date
     * @return Date
     */
    fun arredondaDataZerandoHora(data: Date): Date {
        return org.apache.commons.lang3.time.DateUtils.truncate(data, Calendar.DAY_OF_MONTH)
    }

    /**
     * Arredonta a data desconsiderando a hora, minutos, segundos e milisegundos. Exemplo: 14/10/2012 10:11:12 -> 14/10/2012 23:59:59.
     *
     * @param data Date
     * @return Date
     * @exception ParseException
     */
    fun arredondaDataComMaximaHora(data: Date): Date {
        val dateFormat = SimpleDateFormat("dd/MM/yyyy 23:59:59")
        val dateFormatTransf = SimpleDateFormat("dd/MM/yyyy HH:mm:ss")

        return try {
            dateFormatTransf.parse(dateFormat.format(data))
        } catch (ex: ParseException) {
            ex.stackTrace
            dateFormatTransf.parse(dateFormat.format(Date()))
        }
    }

    /**
     * Método responsável por formatar a data no padrão pt-BR.
     *
     * @param data Date
     * @return String
     */
    fun formataDataHora(data: Date): String {
        val dataFormatada = SimpleDateFormat("dd/MM/yyyy HH:mm:ss")
        return dataFormatada.format(data)
    }

    /**
     * Método responsável por formatar a data no padrão pt-BR sem horário.
     *
     * @param data Date
     * @return String
     */
    fun formataData(data: Date): String {
        val dataFormatada = SimpleDateFormat("dd/MM/yyyy")
        return dataFormatada.format(data)
    }

    /**
     * Método responsável por formatar a data no padrão pt-BR.
     *
     * @param data Date
     * @return String
     */
    fun formataDiaMes(data: Date): String {
        val dataFormatada = SimpleDateFormat("dd/MM")
        return dataFormatada.format(data)
    }

    /**
     * Retorna o ultimo dia do mês da data informada.
     *
     * @param data Date
     * @return Date
     * */
    fun ultimoDiaDoMesAtualComHoraMaxima(data: Date): Date {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.MONTH, DateTime(data).monthOfYear - 1)
        calendar.set(Calendar.YEAR, DateTime(data).year)
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH))
        return arredondaDataComMaximaHora(calendar.time)
    }

    /**
     *  Converte um Date em um XMLGregorianCalendar.
     *
     *  @param data Date
     *  @return XMLGregorianCalendar
     * */
    fun converteDataEmXMLGregoriaCalendar(data: Date): XMLGregorianCalendar {
        val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
        return DatatypeFactory.newInstance().newXMLGregorianCalendar(sdf.format(data))
    }
}
