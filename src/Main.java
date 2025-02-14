import data.Database;
import services.CasalServices;
import services.LeituraArquivos;
import services.LojaServices;
import services.PrestadorServices;

public class Main {
    public static void main(String[] args) throws Exception {

        String path = args[0];
        Database db = new Database();
        
        LeituraArquivos.lePessoas(db, path + "pessoas.csv");
        LeituraArquivos.leLares(db, path + "lares.csv");
        LeituraArquivos.leCasamentos(db, path + "casamentos.csv");
        LeituraArquivos.leTarefas(db, path + "tarefas.csv");
        LeituraArquivos.leFestas(db, path + "festas.csv");
        LeituraArquivos.leCompras(db, path + "compras.csv");

        CasalServices.atualizaDadosCasais(db);

        CasalServices.geraRelatorioCasal(db);

        PrestadorServices.geraRelatorioPrestador(db);
        LojaServices.geraRelatorioLoja(db);
    }
}
