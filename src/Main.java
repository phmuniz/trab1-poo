import data.Database;
import services.CasalServices;
import services.LeituraArquivos;
//import services.LojaServices;
import services.PrestadorServices;

public class Main {
    public static void main(String[] args) throws Exception {
        
        Database db = new Database();

        LeituraArquivos.lePessoas(db, "src/inputs/01/pessoas.csv");
        LeituraArquivos.leLares(db, "src/inputs/01/lares.csv");
        LeituraArquivos.leCasamentos(db, "src/inputs/01/casamentos.csv");
        LeituraArquivos.leTarefas(db, "src/inputs/01/tarefas.csv");
        LeituraArquivos.leFestas(db, "src/inputs/01/festas.csv");
        LeituraArquivos.leCompras(db, "src/inputs/01/compras.csv");

        CasalServices.geraRelatorioCasal(db, "5928374657284960439718273910763458932", "2749812613847265982301756972408391587");
        PrestadorServices.printPrestadores(db);
        CasalServices.printCasais(db);
    }
}
