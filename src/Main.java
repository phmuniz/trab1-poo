import data.Database;
import services.CasalServices;
import services.LeituraArquivos;
import services.LojaServices;
import services.PrestadorServices;
import java.io.FileWriter;

public class Main {
    public static void main(String[] args) throws Exception {

        String path = args[0];
        Database db = new Database();
        
        try{

            LeituraArquivos.lePessoas(db, path + "pessoas.csv");
            LeituraArquivos.leLares(db, path + "lares.csv");
            LeituraArquivos.leCasamentos(db, path + "casamentos.csv");
            LeituraArquivos.leTarefas(db, path + "tarefas.csv");
            LeituraArquivos.leFestas(db, path + "festas.csv");
            LeituraArquivos.leCompras(db, path + "compras.csv");
    
            CasalServices.atualizaDadosCasais(db);
    
            CasalServices.geraRelatorioPlanejamento(db);
    
            PrestadorServices.geraRelatorioPrestador(db);
            LojaServices.geraRelatorioLoja(db);
    
            CasalServices.geraRelatorioCasal(db);
        }

        catch(Exception e){
            System.out.println(e.getMessage());
            FileWriter fileWriter1 = new FileWriter("planejamento.csv", false);
            FileWriter fileWriter2 = new FileWriter("estatisticas-casais.csv", false);
            FileWriter fileWriter3 = new FileWriter("estatisticas-prestadores.csv", false);

            fileWriter1.close();
            fileWriter2.close();
            fileWriter3.close();
        }
    }
}
