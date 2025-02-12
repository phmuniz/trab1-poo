import data.Database;
import services.CasalServices;
import services.LeituraArquivos;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner ler = new Scanner(System.in);
        Database db = new Database();
        
        LeituraArquivos.lePessoas(db, "inputs/01/pessoas.csv");
        LeituraArquivos.leLares(db, "inputs/01/lares.csv");
        LeituraArquivos.leCasamentos(db, "inputs/01/casamentos.csv");
        LeituraArquivos.leTarefas(db, "inputs/01/tarefas.csv");
        LeituraArquivos.leFestas(db, "inputs/01/festas.csv");
        LeituraArquivos.leCompras(db, "inputs/01/compras.csv");
        
        while(true){

            String entrada = ler.nextLine();

            if(entrada.compareTo("") == 0) break;

            String[] cpfs = entrada.split(", ");
            String cpf1 = cpfs[0];
            String cpf2 = cpfs[1];

            CasalServices.geraRelatorioCasal(db, cpf1, cpf2);
        }

        ler.close();
    }
}
