package services;

import java.util.List;
import java.util.Scanner;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import data.Database;
import models.Casal;
import models.Casamento;
import models.PessoaFisica;
import models.Festa;

import java.util.Comparator;
import java.util.Date;
import date.DateFunctions;

public class CasalServices {

    public static void atualizaDadosCasais(Database db) {

        List<Casal> casais = db.getCasais();

        for (int i = 0; i < casais.size(); i++) {

            Casal casal = casais.get(i);

            casal.obtemDataInicioFimPlanejamento();

            casal.geraSaldoMensalCasal();
        }
    }

    public static void geraRelatorioPlanejamento(Database db, String path) {

        Scanner ler = new Scanner(System.in);
        String entrada = null;
        try {
            FileWriter fileWriter = new FileWriter(path + "/saida/1-planejamento.csv", false);
            PrintWriter printWriter = new PrintWriter(fileWriter);

            while (true) {
                if(ler.hasNextLine()){
                    entrada = ler.nextLine();
                    if (entrada.compareTo("") == 0)
                    break;
                }else
                break;
               
        
                String[] cpfs = entrada.split(", ");
                String cpf1 = cpfs[0];
                String cpf2 = cpfs[1];
                Casal casal = db.getCasalByCpf(cpf1, cpf2);

                if(casal == null){
                    printWriter.println("Casal com CPFs " + cpf1 + " e " + cpf2 + " não está cadastrado.");
                    continue;
                }

                if (casal.getTotalGasto() == 0) {
                    printWriter.println("Casal com CPFs " + cpf1 + " e " + cpf2 + " não possui gastos cadastrados.");
                    continue;
                }

                List<Double> saldoMensal = casal.getSaldoMensal();

                PessoaFisica p1 = casal.getPessoa1();
                PessoaFisica p2 = casal.getPessoa2();
                printWriter.print("Nome 1;Nome 2;");
                Date data = casal.getDataInicioPlanejamento();
                for (int j = 0; j < saldoMensal.size(); j++) {

                    if (j + 1 == saldoMensal.size()) {
                        printWriter.print(DateFunctions.dataFormataMesAno(data));
                    } else
                        printWriter.print(DateFunctions.dataFormataMesAno(data) + ";");
                    data = DateFunctions.vaiProProximoMes(data);
                }
                printWriter.println();
                printWriter.print(p1.getNome() + ";" + p2.getNome() + ";");
                for (int j = 0; j < saldoMensal.size(); j++) {
                    double saldo = saldoMensal.get(j);
                    printWriter.printf("R$ %.2f;", saldo);
                    data = DateFunctions.vaiProProximoMes(data);
                }
                printWriter.println();
            }

            printWriter.close();
            ler.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void geraRelatorioCasal(Database db,String path) {

        Comparator<Casal> comparator = new Comparator<Casal>() {

            @Override
            public int compare(Casal c1, Casal c2) {

                if (c1.getTotalGasto() > c2.getTotalGasto()) {
                    return -1;
                } else if (c1.getTotalGasto() < c2.getTotalGasto()) {
                    return 1;
                } else {

                    PessoaFisica pessoa1Casal1 = c1.getPessoa1();
                    PessoaFisica pessoa1Casal2 = c2.getPessoa1();

                    return pessoa1Casal1.getNome().compareTo(pessoa1Casal2.getNome());
                }
            }
        };

        List<Casal> casais = db.getCasais();
        List<Festa> festas = db.getFestas();

        casais.sort(comparator);

        try {
            FileWriter fileWriter = new FileWriter(path + "/saida/3-estatisticas-casais.csv", false);
            PrintWriter printWriter = new PrintWriter(fileWriter);

            for (int i = 0; i < casais.size(); i++) {

                Casal casalAtual = casais.get(i);
                PessoaFisica p1 = casalAtual.getPessoa1();
                PessoaFisica p2 = casalAtual.getPessoa2();

                String nome1 = p1.getNome();
                String nome2 = p2.getNome();

                printWriter.print(nome1 + ";");
                printWriter.print(nome2 + ";");
                printWriter.printf("R$ %.2f;", casalAtual.getTotalGasto());

                int numFestas = 0;

                for (int j = 0; j < festas.size(); j++) {

                    Festa festaAtual = festas.get(j);

                    if (festaAtual.casalEstaNaFesta(nome1, nome2)) {
                        numFestas++;
                    }
                }
                
                printWriter.println(numFestas);
            }

            printWriter.close();
            fileWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void printCasais(Database db) {

        List<Casal> casais = db.getCasais();

        for (int i = 0; i < casais.size(); i++) {

            casais.get(i).printCasal();
            System.out.printf("\n");
        }
    }
}
