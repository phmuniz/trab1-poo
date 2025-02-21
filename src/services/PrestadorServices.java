package services;

import java.util.List;
import java.util.Comparator;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

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

    public static void geraRelatorioPrestador(Database db,String path){

        Comparator<PrestadorServico> comparator = new Comparator<PrestadorServico>() {

            @Override
            public int compare(PrestadorServico p1, PrestadorServico p2) {
    
                int aux = p1.getTipo().compareTo(p2.getTipo());
                if(aux == 0) {
                    
                    int comparaValor = (int)(p2.getValorRecebido() - p1.getValorRecebido());
                    return comparaValor;
                }
                return aux;
            }
        };

        List<PrestadorServico> prestadores = db.getPrestadoresServico();

        prestadores.sort(comparator);

        try {
            FileWriter fileWriter = new FileWriter(path + "/saida/2-estatisticas-prestadores.csv", false);
            PrintWriter printWriter = new PrintWriter(fileWriter);

            for(int i = 0; i < prestadores.size(); i++){

                PrestadorServico prestador = prestadores.get(i);

                printWriter.print(prestador.getTipo() + ";");
                printWriter.print(prestador.getNomePrestador() + ";");
                printWriter.printf("R$ %.2f\n", prestador.getValorRecebido());
            }
            
            printWriter.close();
            fileWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    
    }
}
