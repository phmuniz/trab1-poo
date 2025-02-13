package services;

import java.util.List;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import data.Database;
import models.Casal;
import models.PessoaFisica;
import java.util.Date;
import date.DateFunctions;

public class CasalServices {

    public static void atualizaDadosCasais(Database db){

        List<Casal> casais = db.getCasais();

        for(int i = 0; i < casais.size(); i++){

            Casal casal = casais.get(i);

            casal.obtemDataInicioFimPlanejamento();

            casal.geraSaldoMensalCasal();
        }
    }

    public static void geraRelatorioCasal(List<Casal> casaisRelatorioPlanejamento){

        try {
            FileWriter fileWriter = new FileWriter("planejamento.csv", false);
            PrintWriter printWriter = new PrintWriter(fileWriter);

            for(int i = 0; i < casaisRelatorioPlanejamento.size(); i++){

                Casal casal = casaisRelatorioPlanejamento.get(i);
            
                List<Double> saldoMensal = casal.getSaldoMensal();

                PessoaFisica p1 = casal.getPessoa1();
                PessoaFisica p2 = casal.getPessoa2();
                printWriter.print("Nome 1;Nome 2;"); 
                Date data = casal.getDataInicioPlanejamento();
                for(int j = 0;j< saldoMensal.size();j++){
                    printWriter.print(DateFunctions.dataFormataMesAno(data) + ";");
                    data = DateFunctions.vaiProProximoMes(data);
                }
                printWriter.println();
                printWriter.print(p1.getNome() +";"+p2.getNome()+";"); 
                for(int j = 0;j< saldoMensal.size();j++){
                    double saldo = saldoMensal.get(j);
                    printWriter.printf("R$ %.2f;", saldo);
                    data = DateFunctions.vaiProProximoMes(data);
                }
                printWriter.println();
            }
            printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    
    }
    public static void printCasais(Database db){

        List<Casal> casais = db.getCasais();

        for(int i = 0; i < casais.size(); i++){

            casais.get(i).printCasal();;
            System.out.printf("\n");
        }
    }
}
