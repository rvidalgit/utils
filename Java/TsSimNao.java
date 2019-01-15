package modelosGerados;

/**
 * Enum - Tipo Simples Sim, Não ou Talvez
 * */
public enum TsSimNao {

    SIM(1, "Sim"),
    NAO(2, "Não"),
    TALVEZ(3,"Talvez");

    /**
     * Contrutor
     * @param codigo
     * @param descricao
     * */
    TsSimNao(Integer codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    private Integer codigo;
    private String descricao;

    /**
     * Retorno o código do enum.
     *
     * @return Integer
     * */
    public Integer getCodigo() {
        return Integer.parseInt(String.valueOf(this.codigo));
    }

    /**
     * Retorna a descrição do enum.
     *
     * @return String
     * */
    public String getDescricao() {
        return descricao;
    }

    /**
     * Método de execução.
     * */
    public static void main(String[] args) {
        Integer tc = TsSimNao.NAO.codigo;
        String td = TsSimNao.NAO.descricao;

        System.out.println("Código: " + tc);
        System.out.println("Descrição: " + td);
    }
}
