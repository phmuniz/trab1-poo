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

            LeituraArquivos.lePessoas(db, path + "/pessoas.csv");
            LeituraArquivos.leLares(db, path + "/lares.csv");
            LeituraArquivos.leCasamentos(db, path + "/casamentos.csv");
            LeituraArquivos.leTarefas(db, path + "/tarefas.csv");
            LeituraArquivos.leFestas(db, path + "/festas.csv");
            LeituraArquivos.leCompras(db, path + "/compras.csv");
    
            CasalServices.atualizaDadosCasais(db);
    
            CasalServices.geraRelatorioPlanejamento(db,path);
    
            PrestadorServices.geraRelatorioPrestador(db,path);
            LojaServices.geraRelatorioLoja(db,path);
    
            CasalServices.geraRelatorioCasal(db,path);
        }

        catch(Exception e){
            System.out.println(e.getMessage());
            FileWriter fileWriter1 = new FileWriter( path + "/saida/1-planejamento.csv", false);
            FileWriter fileWriter2 = new FileWriter( path + "/saida/3-estatisticas-casais.csv", false);
            FileWriter fileWriter3 = new FileWriter( path + "/saida/2-estatisticas-prestadores.csv", false);

            fileWriter1.close();
            fileWriter2.close();
            fileWriter3.close();
        }
    }
}
