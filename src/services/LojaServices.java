package services;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Comparator;
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

    public static void geraRelatorioLoja(Database db,String path){

        Comparator<Loja> comparator = new Comparator<Loja>() {

            @Override
            public int compare(Loja l1, Loja l2) {
                      
                int comparaValor = (int)(l2.getValorRecebido() - l1.getValorRecebido());
                return comparaValor;
            }
        };

        List<Loja> lojas = db.getLojas();

        lojas.sort(comparator);

        try {
            FileWriter fileWriter = new FileWriter(path + "/saida/2-estatisticas-prestadores.csv", true);
            PrintWriter printWriter = new PrintWriter(fileWriter);

            for(int i = 0; i < lojas.size(); i++){

                Loja loja = lojas.get(i);

                printWriter.print("Loja;");
                printWriter.print(loja.getNome() + ";");
                printWriter.printf("R$ %.2f\n", loja.getValorRecebido());
            }
            
            printWriter.close();
            fileWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    
    }
}