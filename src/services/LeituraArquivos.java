package services;
import data.Database;
import date.DateFunctions;
import models.PessoaFisica;
import models.PessoaJuridica;
import models.Loja;
import models.Lar;
import models.Casal;
import models.Casamento;
import models.PrestadorServico;
import models.Tarefa;
import models.Festa;
import models.Compra;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

public class LeituraArquivos {

    
    public static void lePessoas(Database db, String caminho) throws Exception{

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

                    if(db.verificaPessoaExiste(valores[0])){
                        throw new Exception("ID repetido " + valores[0] + " na classe Pessoa.");
                    }

                    valores[7] = valores[7].replace(',', '.');
                    valores[8] = valores[8].replace(',', '.');
                    valores[9] = valores[9].replace(',', '.');

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
                    PrestadorServico ps = new PrestadorServico(null, pessoa);
                    db.adicionaPrestador(ps);
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


    public static void leTarefas(Database db, String caminho){

        try {
            List<String> linhas = Files.readAllLines(Paths.get(caminho));
            for (String linha : linhas) {
                String[] valores = linha.split(";");

                Date dataInicial;
                try{
                    dataInicial = DateFunctions.criaData(valores[3]);
                }
                catch(ParseException e){
                    dataInicial = null;
                }

                valores[5] = valores[5].replace(',', '.');

                Tarefa tarefa = new Tarefa(valores[0], dataInicial, Integer.parseInt(valores[4]), Double.parseDouble(valores[5]), Integer.parseInt(valores[6]));

                Lar lar = db.getLarById(valores[1]);
                lar.adicionaTarefa(tarefa);

                PrestadorServico ps = db.getPrestadorById(valores[2]);

                if(ps == null){

                    PessoaFisica pf = db.getPessoaFisicaById(valores[2]);

                    PrestadorServico ps2 = new PrestadorServico(pf,null);
                    ps2.recebeValor(Double.parseDouble(valores[5]));
                    db.adicionaPrestador(ps2);
                }
                else{
                    ps.recebeValor(Double.parseDouble(valores[5]));
                }       
                
                db.adicionaTarefa(tarefa);
            }
        } catch (IOException e) {
            System.out.println("Erro de IO");
        }
    }
    
    public static void leFestas(Database db, String caminho){

        try {
            List<String> linhas = Files.readAllLines(Paths.get(caminho));
            for (String linha : linhas) {
                String[] valores = linha.split(";");

                 Date dataFesta;
                try{
                    dataFesta = DateFunctions.criaData(valores[3]);
                }
                catch(ParseException e){
                    dataFesta = null;
                }
                valores[5] = valores[5].replace(',', '.');
                int numConvidados = Integer.parseInt(valores[7]);
                Casamento casamento = db.getCasamentoById(valores[1]);
                Festa festa = new Festa(valores[2],dataFesta,valores[4],valores[0],Double.parseDouble(valores[5]),
                numConvidados,Integer.parseInt(valores[6]));
                for(int i = 0; i < numConvidados;i++){
                    festa.adicionaConvidado(valores[8 + i]);
                }
                casamento.setFesta(festa);
                db.adicionaFestas(festa);
                
            }
        } catch (IOException e) {
            System.out.println("Erro de IO");
        }
    }

    public static void leCompras(Database db, String caminho){

        try {
            List<String> linhas = Files.readAllLines(Paths.get(caminho));
            for (String linha : linhas) {
                String[] valores = linha.split(";");

                valores[5] = valores[5].replace(',', '.');

                Tarefa tarefa = db.getTarefaById(valores[1]);
                Date dataCompra = tarefa.getDataInicio();
                Compra compra = new Compra(valores[0], valores[3], Integer.parseInt(valores[4]), Double.parseDouble(valores[5]), Integer.parseInt(valores[6]), dataCompra);
                tarefa.adicionaCompra(compra);

                Loja loja = db.getLojaById(valores[2]);
                double valorRecebidoLoja = Integer.parseInt(valores[4]) * Double.parseDouble(valores[5]);
                loja.recebeValor(valorRecebidoLoja);
            }
        } catch (IOException e) {
            System.out.println("Erro de IO");
        }
    }
}