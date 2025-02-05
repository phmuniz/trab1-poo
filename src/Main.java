import data.Database;
//import services.CasalServices;
import services.LeituraArquivos;
//import services.LojaServices;
import services.PrestadorServices;

public class Main {
    public static void main(String[] args) throws Exception {
        
        Database db = new Database();

        LeituraArquivos.lePessoas(db, "src/inputs/pessoas.csv");
        LeituraArquivos.leLares(db, "src/inputs/lares.csv");
        LeituraArquivos.leCasamentos(db, "src/inputs/casamentos.csv");
        LeituraArquivos.leTarefas(db, "src/inputs/tarefas.csv");
        LeituraArquivos.leFestas(db, "src/inputs/festas.csv");
        LeituraArquivos.leCompras(db, "src/inputs/compras.csv");

        PrestadorServices.printPrestadores(db);
    }
}
