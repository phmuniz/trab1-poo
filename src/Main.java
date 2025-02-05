import data.Database;
import services.CasalServices;
import services.LeituraArquivos;

public class Main {
    public static void main(String[] args) throws Exception {
        
        Database db = new Database();

        LeituraArquivos.lePessoas(db, "src/inputs/pessoas.csv");
        LeituraArquivos.leLares(db, "src/inputs/lares.csv");
        LeituraArquivos.leCasamentos(db, "src/inputs/casamentos.csv");
        LeituraArquivos.leTarefas(db, "src/inputs/tarefas.csv");

        CasalServices.printCasais(db);
    }
}
