package services;

import java.util.List;

import data.Database;
import models.Casal;

public class CasalServices {

    public static void geraRelatorioCasal(Database db, String id1, String id2){

        Casal casal = db.getCasalById(id1, id2);

        casal.obtemDataInicioFimPlanejamento();
    }
    
    public static void printCasais(Database db){

        List<Casal> casais = db.getCasais();

        for(int i = 0; i < casais.size(); i++){

            casais.get(i).printCasal();;
            System.out.printf("\n");
        }
    }
}
