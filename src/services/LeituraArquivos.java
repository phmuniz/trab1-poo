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

    public static void lePessoas(Database db, String caminho) throws Exception {

        try {
            List<String> linhas = Files.readAllLines(Paths.get(caminho));
            for (String linha : linhas) {
                String[] valores = linha.split(";");
                String id = valores[0].replace(" ", "");
                if (db.verificaPessoaExiste(id)) {
                    throw new Exception("ID repetido " + id + " na classe Pessoa.");
                }
                String tipo = valores[1];

                if (tipo.compareTo("F") == 0) {

                    if (db.ehMesmoCpf(valores[5])) {
                        throw new Exception("O CPF " + valores[5] + " da Pessoa " + id + " é repetido.");
                    }

                    Date dataNascimento;
                    try {
                        dataNascimento = DateFunctions.criaData(valores[6]);
                    } catch (ParseException e) {
                        dataNascimento = null;
                    }

                    valores[7] = valores[7].replace(',', '.');
                    valores[8] = valores[8].replace(',', '.');
                    valores[9] = valores[9].replace(',', '.');
                    String nome = valores[2].trim();
                    PessoaFisica pessoa = new PessoaFisica(
                            id, nome, valores[4], valores[3],
                            dataNascimento, valores[5],
                            Double.parseDouble(valores[7]), Double.parseDouble(valores[8]),
                            Double.parseDouble(valores[9]));
                    db.adicionaPessoaFisica(pessoa);
                }

                else if (tipo.compareTo("J") == 0) {

                    if (db.ehMesmoCnpj(valores[5])) {
                        throw new Exception("O CNPJ " + valores[5] + " da Pessoa " + id + " é repetido.");
                    }
                    String nome = valores[2].trim();
                    PessoaJuridica pessoa = new PessoaJuridica(id, nome, valores[4], valores[3],
                            valores[5]);
                    db.adicionaPessoaJuridicas(pessoa);
                    PrestadorServico ps = new PrestadorServico(null, pessoa);
                    db.adicionaPrestador(ps);
                }

                else if (tipo.compareTo("L") == 0) {

                    if (db.ehMesmoCnpj(valores[5])) {
                        throw new Exception("O CNPJ " + valores[5] + " da Pessoa " + id+ " é repetido.");
                    }
                    String nome = valores[2].trim();
                    PessoaJuridica pessoa = new PessoaJuridica(id, nome, valores[4], valores[3],
                            valores[5]);
                    Loja loja = new Loja(pessoa);
                    db.adicionaLojas(loja);
                }

            }
        } catch (IOException e) {
            System.out.println("Erro de IO");
        }
    }

    public static void leLares(Database db, String caminho) throws Exception {

        try {
            List<String> linhas = Files.readAllLines(Paths.get(caminho));
            for (String linha : linhas) {
                String[] valores = linha.split(";");
                String id = valores[0].replace(" ", "");
                String idp1 = valores[1].replace(" ", "");
                String idp2 = valores[2].replace(" ", "");
                
            
                if (db.verificaLarExiste(id)) {
                    throw new Exception("ID repetido " + id + " na classe Lar.");
                }
                Lar lar = new Lar(id, valores[3], Integer.parseInt(valores[4]), valores[5]);

                PessoaFisica p1 = db.getPessoaFisicaById(idp1);
                PessoaFisica p2 = db.getPessoaFisicaById(idp2);

                if (p1 == null && p2 == null) {
                    throw new Exception("ID(s) de Pessoa " + idp1 + " " + idp2
                            + " " + "não cadastrado no Lar de ID " + id + ".");
                } else if (p1 == null) {
                    throw new Exception(
                            "ID(s) de Pessoa " + idp1 + " " + "não cadastrado no Lar de ID " + id + ".");
                } else if (p2 == null) {
                    throw new Exception(
                            "ID(s) de Pessoa " + idp2 + " " + "não cadastrado no Lar de ID " + id + ".");
                }

                Casal casal = new Casal(p1, p2, lar, null);
                db.adicionaLar(lar);
                db.adicionaCasal(casal);
            }
        } catch (IOException e) {
            System.out.println("Erro de IO");
        }
    }

    public static void leCasamentos(Database db, String caminho) throws Exception {

        try {
            List<String> linhas = Files.readAllLines(Paths.get(caminho));
            for (String linha : linhas) {
                String[] valores = linha.split(";");
                String id = valores[0].replace(" ", "");
                String idp1 = valores[1].replace(" ", "");
                String idp2 = valores[2].replace(" ", "");
                if (db.verificaCasamentoExiste(id)) {
                    throw new Exception("ID repetido " + id + " na classe Casamento.");
                }
                Date dataCasamento;
                try {
                    dataCasamento = DateFunctions.criaData(valores[3]);
                } catch (ParseException e) {
                    dataCasamento = null;
                }

                Casamento casamento = new Casamento(valores[5], dataCasamento, valores[4], id, null);
                PessoaFisica p1 = db.getPessoaFisicaById(idp1);
                PessoaFisica p2 = db.getPessoaFisicaById(idp2);
                if (p1 == null && p2 == null) {
                    throw new Exception("ID(s) de Pessoa " + idp1 + " " + idp2
                            + " " + "não cadastrado no Casamento de ID " + id + ".");
                } else if (p1 == null) {
                    throw new Exception(
                            "ID(s) de Pessoa " + idp1 + " " + "não cadastrado no Casamento de ID " + id
                                    + ".");
                } else if (p2 == null) {
                    throw new Exception(
                            "ID(s) de Pessoa " + idp2 + " " + "não cadastrado no Casamento de ID " + id
                                    + ".");
                }

                Casal casal = db.getCasalById(p1.getId(), p2.getId());
                if (casal != null) {
                    casal.setCasamento(casamento);
                } else {
                    casal = new Casal(p1, p2, null, casamento);
                    db.adicionaCasal(casal);
                }

                db.adicionaCasamento(casamento);
            }
        } catch (

        IOException e) {
            System.out.println("Erro de IO");
        }
    }

    public static void leTarefas(Database db, String caminho) throws Exception {

        try {
            List<String> linhas = Files.readAllLines(Paths.get(caminho));
            for (String linha : linhas) {
                String[] valores = linha.split(";");
                String id = valores[0].replace(" ", "");
                String idLar = valores[1].replace(" ", "");
                String idPrestador = valores[2].replace(" ", "");
                if (db.verificaTarefaExiste(id)) {
                    throw new Exception("ID repetido " + id + " na classe Tarefa.");
                }
                Date dataInicial;
                try {
                    dataInicial = DateFunctions.criaData(valores[3]);
                } catch (ParseException e) {
                    dataInicial = null;
                }

                valores[5] = valores[5].replace(',', '.');

                Tarefa tarefa = new Tarefa(id, dataInicial, Integer.parseInt(valores[4]),
                        Double.parseDouble(valores[5]), Integer.parseInt(valores[6]));

                Lar lar = db.getLarById(idLar);
                if (lar == null) {
                    throw new Exception(
                            "ID(s) de Lar " + idLar + " " + "não cadastrado na Tarefa de ID " + id + ".");
                }
                lar.adicionaTarefa(tarefa);
                PrestadorServico ps = db.getPrestadorById(idPrestador);

                if (ps == null) {

                    PessoaFisica pf = db.getPessoaFisicaById(idPrestador);
                    if (pf == null) {
                        throw new Exception(
                                "ID(s) de Prestador de Serviço " + idPrestador + " " + "não cadastrado na Tarefa de ID "
                                        + id + ".");
                    }
                    PrestadorServico ps2 = new PrestadorServico(pf, null);
                    ps2.recebeValor(Double.parseDouble(valores[5]));
                    db.adicionaPrestador(ps2);
                } else {
                    ps.recebeValor(Double.parseDouble(valores[5]));
                }

                db.adicionaTarefa(tarefa);
            }
        } catch (IOException e) {
            System.out.println("Erro de IO");
        }
    }

    public static void leFestas(Database db, String caminho) throws Exception {

        try {
            List<String> linhas = Files.readAllLines(Paths.get(caminho));
            for (String linha : linhas) {
                String[] valores = linha.split(";");
                String id = valores[0].replace(" ", "");
                String idCasamento = valores[1].replace(" ", "");
                if (db.verificaFestaExiste(id)) {
                    throw new Exception("ID repetido " + id + " na classe Festa.");
                }

                Date dataFesta;
                try {
                    dataFesta = DateFunctions.criaData(valores[3]);
                } catch (ParseException e) {
                    dataFesta = null;
                }
                valores[5] = valores[5].replace(',', '.');
                int numConvidados = Integer.parseInt(valores[7]);
                Casamento casamento = db.getCasamentoById(idCasamento);
                if (casamento == null) {
                    throw new Exception(
                            "ID(s) de Casamento " + idCasamento + " " + "não cadastrado na Festa de ID "
                                    + id + ".");
                }
                Festa festa = new Festa(valores[2], dataFesta, valores[4], id, Double.parseDouble(valores[5]),
                        numConvidados, Integer.parseInt(valores[6]));
                for (int i = 0; i < numConvidados; i++) {
                    String convidado = valores[8 + i].trim();
                    festa.adicionaConvidado(convidado);
                }
                casamento.setFesta(festa);
                db.adicionaFestas(festa);

            }
        } catch (IOException e) {
            System.out.println("Erro de IO");
        }
    }

    public static void leCompras(Database db, String caminho) throws Exception {

        try {
            List<String> linhas = Files.readAllLines(Paths.get(caminho));
            for (String linha : linhas) {
                
                String[] valores = linha.split(";");
                String id = valores[0].replace(" ", "");
                String idTarefa = valores[1].replace(" ", "");
                String idLoja = valores[2].replace(" ", "");



                if (db.verificaCompraExiste(id)) {
                    throw new Exception("ID repetido " + id + " na classe Compra.");
                }

                valores[5] = valores[5].replace(',', '.');

                Tarefa tarefa = db.getTarefaById(idTarefa);
                if (tarefa == null) {
                    throw new Exception(
                            "ID(s) de Tarefa " + idTarefa + " " + "não cadastrado na Compra de ID "
                                    + id + ".");
                }
                Date dataCompra = tarefa.getDataInicio();
                Compra compra = new Compra(id, valores[3], Integer.parseInt(valores[4]),
                        Double.parseDouble(valores[5]), Integer.parseInt(valores[6]), dataCompra);
                tarefa.adicionaCompra(compra);
                db.adicionaCompra(compra);

                if (db.verificaLojaExiste(idLoja) == false) {
                    if (db.verificaPjExiste(idLoja) == true) {
                        throw new Exception(
                                "ID " + idLoja + " da Compra de ID " + id
                                        + " não se refere a uma loja, mas a uma PJ.");
                    } else
                        throw new Exception(
                                "ID(s) de Loja " + idLoja + " " + "não cadastrado na Compra de ID "
                                        + id + ".");
                }
                Loja loja = db.getLojaById(idLoja);
                double valorRecebidoLoja = (double)(Integer.parseInt(valores[4]) * Double.parseDouble(valores[5]));
                loja.recebeValor(valorRecebidoLoja);
            }
        } catch (IOException e) {
            System.out.println("Erro de IO");
        }
    }
}