package model;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MesInicioEfim {

    public static void main(String[] args) {

        System.out.println("Primeiro dia do mês || " + getPrimeiroDiaDoMesAnterior());
        System.out.println("Último dia do mês || " + getUltimoDiaDoMesAnterior());
        System.out.println("Primeiro dia do mês com horário zerado || " + arredondaDataZerandoHora(getPrimeiroDiaDoMesAnterior()));
        System.out.println("Último dia do mês com horário máximo || " + arredondaDataComMaximaHora(getUltimoDiaDoMesAnterior()));
    }

    private static Date getPrimeiroDiaDoMesAnterior() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -1);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        return calendar.getTime();
    }

    private static Date getUltimoDiaDoMesAnterior() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -1);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        return calendar.getTime();
    }


    /**
     * Arredonta a data desconsiderando a hora, minutos, segundos e milisegundos. Exemplo: 14/10/2012 10:11:12 -> 14/10/2012 00:00:00.
     *
     * @param data {@link Date}
     * @return {@link Date}
     */
    private static Date arredondaDataZerandoHora(Date data) {
        if (data == null) {
            return null;
        }
        return org.apache.commons.lang3.time.DateUtils.truncate(data, Calendar.DAY_OF_MONTH);
    }

    /**
     * Arredonta a data desconsiderando a hora, minutos, segundos e milisegundos. Exemplo: 14/10/2012 10:11:12 -> 14/10/2012 23:59:59.
     *
     * @param data {@link Date}
     * @return {@link Date}
     */
    private static Date arredondaDataComMaximaHora(Date data) {
        if (data == null) {
            return null;
        }
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy 23:59:59");
        DateFormat dateFormatTransf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        try {
            return dateFormatTransf.parse(dateFormat.format(data));
        } catch (ParseException ex) {
            return null;
        }
    }
}
