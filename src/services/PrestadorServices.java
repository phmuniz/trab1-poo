package services;

import java.util.List;

import data.Database;
import models.PrestadorServico;

public class PrestadorServices {
    
    public static void printPrestadores(Database db){

        List<PrestadorServico> prestadores = db.getPrestadoresServico();

        for(int i = 0; i < prestadores.size(); i++){

            prestadores.get(i).printPrestador();;
            System.out.printf("\n");
        }
    }
}
