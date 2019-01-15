package model;

/**
 * Classe responsável por exiber todas as informações do local de implantação da JVM.
 * */
public class InfrastructureInformation {

    /**
     * Método principal
     *
     * @see System
     * */
    public static void main(String[] args) {
        System.out.println("01 - Versão do Java: " + System.getProperty("java.version"));
        System.out.println("02 - Distribuidor Java: " + System.getProperty("java.vendor"));
        System.out.println("03 - Site do Distribuidor: " + System.getProperty("java.vendor.url"));
        System.out.println("04 - Diretório de instalação: " + System.getProperty("java.home"));
        System.out.println("05 - Versão da VM: " + System.getProperty("java.vm.specification.version"));
        System.out.println("06 - Distribuidor da VM: " + System.getProperty("java.vm.specification.vendor"));
        System.out.println("07 - Caminho para pesquisar ao carregar bibliotecas: " + System.getProperty("java.io.tmpdir"));
        System.out.println("08 - Sistema Operacional: " + System.getProperty("os.name"));
        System.out.println("09 - Versão do Sistema Operacional: " + System.getProperty("os.version"));
        System.out.println("10 - Arquitetura: " + System.getProperty("os.arch"));
        System.out.println("11 - Separador de arquivo: " + System.getProperty("file.separator"));
        System.out.println("12 - Separador de pasta: " + System.getProperty("path.separator"));
        System.out.println("13 - Separador de linha (unix) (\\n): " + System.getProperty("line.separator").equals("\n"));
        System.out.println("14 - Separador de linha (windows) (\\r\\n): " + System.getProperty("line.separator").equals("\\r\n"));
        System.out.println("15 - Usuário logado: " + System.getProperty("user.name"));
        System.out.println("16 - Diretório do usuário: " + System.getProperty("user.home"));
        System.out.println("17 - Diretório da aplicação: " + System.getProperty("user.dir"));
    }
}
