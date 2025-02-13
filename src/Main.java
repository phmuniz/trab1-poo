import data.Database;
import services.CasalServices;
import services.LeituraArquivos;
import models.Casal;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {

        String path = args[0];
        Scanner ler = new Scanner(System.in);
        Database db = new Database();
        
        LeituraArquivos.lePessoas(db, path + "pessoas.csv");
        LeituraArquivos.leLares(db, path + "lares.csv");
        LeituraArquivos.leCasamentos(db, path + "casamentos.csv");
        LeituraArquivos.leTarefas(db, path + "tarefas.csv");
        LeituraArquivos.leFestas(db, path + "festas.csv");
        LeituraArquivos.leCompras(db, path + "compras.csv");

        CasalServices.atualizaDadosCasais(db);

        List<Casal> casaisRelatorioPlanejamento = new ArrayList<Casal>();
        
        while(true){

            String entrada = ler.nextLine();

            if(entrada.compareTo("") == 0) break;

            String[] cpfs = entrada.split(", ");
            String cpf1 = cpfs[0];
            String cpf2 = cpfs[1];

            Casal casal = db.getCasalByCpf(cpf1, cpf2);

            casaisRelatorioPlanejamento.add(casal);
        }

        CasalServices.geraRelatorioCasal(casaisRelatorioPlanejamento);

        ler.close();
    }
}
