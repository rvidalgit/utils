package model;

public class ValidaCartao {

    String txtStatus;

    public String valida(String txtNumCartao) {

        String numCartao = Numeros(txtNumCartao);

        String numString;

        int soma = 0;

//Cartão com seqüências de caracteres menor ou igual a 15 dígitos

        if (numCartao.length() <= 15) {

            for (int i = 0; i <= numCartao.length(); i++) {

                numString = (numCartao.substring(i, i + 1));

                if (i % 2 == 0) {

                    soma += (Integer.parseInt(numString));

                } else {

                    if ((Integer.parseInt(numString) * 2) > 9) {

                        soma += ((Integer.parseInt(numString) * 2) - 9);

                    } else {

                        soma += ((Integer.parseInt(numString) * 2));

                    }

                }

            }

        }

//Cartão com seqüências de caracteres maior ou igual a 16 dígitos

        if (numCartao.length() >= 16) {
            for (int i = 0; i <= numCartao.length(); i++) {
                numString = (numCartao.substring(i, i + 1));
                if (i % 2 == 0) {
                    if ((Integer.parseInt(numString) * 2) > 9) {
                        soma += ((Integer.parseInt(numString) * 2) - 9);
                    } else {
                        soma += ((Integer.parseInt(numString) * 2));
                    }
                } else {
                    soma += (Integer.parseInt(numString) * 1);
                }
            }
        }

        if (soma % 10 == 0) {
            txtStatus = "Cartão valido!";

        } else {
            txtStatus = "Cartão invalido!";
        }

        return txtStatus;
    }


    private static String Numeros(String n) {

        String lnNova = "";
        String soNumero = "0123456789";
        String item = "";


        for (int i = 0; i <= n.length() - 1; i++) {

            item = n.substring(i, i + 1);

            for (int j = 0; j <= soNumero.length() - 1; j++) {

                if (soNumero.substring(j, j + 1) == item) {

                    //Se for um numero item recebe o caracter

                    item = n.substring(j, j + 1) + lnNova;

                }

            }

        }

//Retorna a nova linha

        return lnNova;

    }

    public static void main(String[] args) {
        ValidaCartao vc = new ValidaCartao();
        System.out.println(vc.valida("123546879"));
    }

}
