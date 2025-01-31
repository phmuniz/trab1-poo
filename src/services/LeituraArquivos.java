package services;
import data.Database;
import date.DateFunctions;
import models.PessoaFisica;
import models.PessoaJuridica;
import models.Loja;
import models.Lar;
import models.Casal;
import models.Casamento;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

public class LeituraArquivos {

    
    public static void lePessoas(Database db, String caminho){

        try {
            List<String> linhas = Files.readAllLines(Paths.get(caminho));
            for (String linha : linhas) {
                String[] valores = linha.split(";");
                String tipo = valores[1];

                if(tipo.compareTo("F") == 0){

                    Date dataNascimento;
                    try{
                        dataNascimento = DateFunctions.criaData(valores[6]);
                    }
                    catch(ParseException e){
                        dataNascimento = null;
                    }

                    PessoaFisica pessoa = new PessoaFisica(
                        valores[0], valores[2], valores[4], valores[3],
                        dataNascimento, valores[5],
                        Double.parseDouble(valores[7]), Double.parseDouble(valores[8]), Double.parseDouble(valores[9])
                    );
                    db.adicionaPessoaFisica(pessoa);
                }

                else if(tipo.compareTo("J") == 0){

                    PessoaJuridica pessoa = new PessoaJuridica(valores[0],valores[2],valores[4],valores[3],valores[5]);
                    db.adicionaPessoaJuridicas(pessoa);
                }

                else if(tipo.compareTo("L") == 0){

                    PessoaJuridica pessoa = new PessoaJuridica(valores[0],valores[2],valores[4],valores[3],valores[5]);
                    Loja loja = new Loja(pessoa);
                    db.adicionaLojas(loja);
                }
                
            }
        } catch (IOException e) {
            System.out.println("Erro de IO");
        }
    }


    public static void leLares(Database db, String caminho){

        try {
            List<String> linhas = Files.readAllLines(Paths.get(caminho));
            for (String linha : linhas) {
                String[] valores = linha.split(";");
                Lar lar = new Lar(valores[0], valores[3],Integer.parseInt(valores[4]),valores[5]);
                
                PessoaFisica p1 = db.getPessoaFisicaById(valores[1]);
                PessoaFisica p2 = db.getPessoaFisicaById(valores[2]);

                Casal casal = new Casal(p1, p2, lar, null);
                db.adicionaLar(lar);
                db.adicionaCasal(casal);
            }
        } catch (IOException e) {
            System.out.println("Erro de IO");
        }
    }
    
    public static void leCasamentos(Database db, String caminho){

        try {
            List<String> linhas = Files.readAllLines(Paths.get(caminho));
            for (String linha : linhas) {
                String[] valores = linha.split(";");

                 Date dataCasamento;
                    try{
                        dataCasamento = DateFunctions.criaData(valores[3]);
                    }
                    catch(ParseException e){
                        dataCasamento = null;
                    }
                
                Casamento casamento = new Casamento(valores[5],dataCasamento,valores[4],valores[0],null);
                
                PessoaFisica p1 = db.getPessoaFisicaById(valores[1]);
                PessoaFisica p2 = db.getPessoaFisicaById(valores[2]);

                Casal casal = db.getCasalById(p1.getId(), p2.getId());
                if(casal != null){
                    casal.setCasamento(casamento);
                }
                else{
                    casal = new Casal(p1, p2, null, casamento);
                    db.adicionaCasal(casal);
                }

                db.adicionaCasamento(casamento);
            }
        } catch (IOException e) {
            System.out.println("Erro de IO");
        }
    }
}