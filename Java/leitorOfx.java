public class OfxReader {

    public static void main(String[] args) {
        String path = "/home/rodrigovidal/Documentos/materiais_de_estudo/extrato.ofx";
        try {
            InputStream entrada = new FileInputStream(path);
            new OfxReader().lerOfx(entrada);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void lerOfx(InputStream entrada) {
        AggregateUnmarshaller<ResponseEnvelope> a = new AggregateUnmarshaller<>(ResponseEnvelope.class);

        try {
            ResponseEnvelope re = a.unmarshal(entrada);
            //SignonResponse sr = re.getSignonResponse();
            MessageSetType type = MessageSetType.banking;
            ResponseMessageSet message = re.getMessageSet(type);

            if (message != null) {
                List<BankStatementResponseTransaction> bank = ((BankingResponseMessageSet) message).getStatementResponses();

                for (BankStatementResponseTransaction b : bank) {
                    System.out.println("cc: " + b.getMessage().getAccount().getAccountNumber());
                    System.out.println("ag: " + b.getMessage().getAccount().getBranchId());
                    System.out.println("balanço final: " + b.getMessage().getLedgerBalance().getAmount());
                    System.out.println("dataDoArquivo: " + b.getMessage().getLedgerBalance().getAsOfDate());
                    List<Transaction> list = b.getMessage().getTransactionList().getTransactions();
                    System.out.println("TRANSAÇÕES\n");
                    for (Transaction transaction : list) {
                        System.out.println("tipo: " + transaction.getTransactionType().name());
                        System.out.println("id: " + transaction.getId());
                        System.out.println("data: " + transaction.getDatePosted());
                        System.out.println("valor: " + transaction.getAmount());
                        System.out.println("descricao: " + transaction.getMemo() + "\n");
                    }
                }
            }
        } catch (IOException | OFXParseException e) {
            e.printStackTrace();
        }
    }

}

//DEPENDENCIAS
// https://mvnrepository.com/artifact/org.javassist/javassist            3.26.0-GA
// https://mvnrepository.com/artifact/org.reflections/reflections        0.9.11
// https://mvnrepository.com/artifact/commons-logging/commons-logging    1.2
// https://mvnrepository.com/artifact/be.cyberelf.nanoxml/nanoxml        2.2.3
// https://mvnrepository.com/artifact/com.webcohesion.ofx4j/ofx4j        1.17
// https://mvnrepository.com/artifact/com.google.guava/guava             28.1-jre
