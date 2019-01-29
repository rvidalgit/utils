package model;

/**
 * Classe de validação da Inscrição Municipal da Prefeitura do Rio de Janeiro.
 *
 * @link view-source:http://dief.rio.rj.gov.br/smf/certecweb/pesquisa.asp
 */
public class InscricaoMunicipalValidator {

    /**
    * Método de teste.
    */
    public static void main(String[] args) {
        InscricaoMunicipalValidator validator = new InscricaoMunicipalValidator();

        Boolean valido = validator.validaInscricaoMunicipal("00471356");

        System.out.println(valido);
    }

    /**
     * Método que realiza a validação do documento.
     *
     * @param inscricao String
     * @return Boolean
     * */
    public Boolean validaInscricaoMunicipal(String inscricao) {
        StringBuilder insc = new StringBuilder(removeCaracteresEspeciais(inscricao));

        if (insc.toString().equals("") || insc.length() > 8) {
            return false;
        }

        if (insc.toString().equals("00000000")
                || insc.toString().equals("11111111")
                || insc.toString().equals("22222222")
                || insc.toString().equals("33333333")
                || insc.toString().equals("44444444")
                || insc.toString().equals("55555555")
                || insc.toString().equals("66666666")
                || insc.toString().equals("77777777")
                || insc.toString().equals("88888888")
                || insc.toString().equals("99999999")) {
            return false;
        }

        if (insc.length() < 8) {
            for (int i = insc.length(); i < 8; i++) {
                insc.insert(0, "0");
            }
        }

        int j = 1;
        int iAuxNum = 0;
        String inscSemDigitoVerifcador = insc.substring(0, 7);
        int iMax = inscSemDigitoVerifcador.length() - 1;
        for (int i = iMax; i >= 0; i--) {
            j += 1;
            if (j > 7) {
                j = 2;
            }
            iAuxNum += (inscSemDigitoVerifcador.charAt(i) - 48) * j;
        }

        int iResto = iAuxNum % 11;
        int digito = 11 - iResto;

        if (digito > 9) {
            digito = 0;
        }

        return digito == (insc.charAt(7) - 48);
    }

    /**
     * Remove os caracteres especiais do documento.
     *
     * @param doc String
     * @return String
     * */
    private String removeCaracteresEspeciais(String doc) {
        if (doc.contains(".")) {
            doc = doc.replace(".", "");
        }
        if (doc.contains("-")) {
            doc = doc.replace("-", "");
        }
        if (doc.contains("/")) {
            doc = doc.replace("/", "");
        }
        return doc;
    }

}
