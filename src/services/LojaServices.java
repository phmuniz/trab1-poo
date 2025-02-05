package services;

import java.util.List;

import data.Database;
import models.Loja;

public class LojaServices {
    
    public static void printLojas(Database db){

        List<Loja> lojas = db.getLojas();

        for(int i = 0; i < lojas.size(); i++){

            lojas.get(i).printLoja();;
            System.out.printf("\n");
        }
    }
}