package services;

import java.util.List;

import data.Database;
import models.Casal;

public class CasalServices {

    public static void geraRelatorioCasal(Database db, String cpf1, String cpf2){

        Casal casal = db.getCasalByCpf(cpf1, cpf2); 
        
        casal.obtemDataInicioFimPlanejamento();
        
        double saldoTotal = casal.obtemSaldoCasal();
    
    }
    public static void printCasais(Database db){

        List<Casal> casais = db.getCasais();

        for(int i = 0; i < casais.size(); i++){

            casais.get(i).printCasal();;
            System.out.printf("\n");
        }
    }
}
