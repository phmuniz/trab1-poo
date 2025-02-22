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

                if (db.verificaPessoaExiste(valores[0])) {
                    throw new Exception("ID repetido " + valores[0] + " na classe Pessoa.");
                }

                String tipo = valores[1];

                if (tipo.compareTo("F") == 0) {

                    if (db.ehMesmoCpf(valores[5])) {
                        throw new Exception("O CPF " + valores[5] + " da Pessoa " + valores[0] + " é repetido.");
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
                            valores[0], nome, valores[4], valores[3],
                            dataNascimento, valores[5],
                            Double.parseDouble(valores[7]), Double.parseDouble(valores[8]),
                            Double.parseDouble(valores[9]));
                    db.adicionaPessoaFisica(pessoa);
                }

                else if (tipo.compareTo("J") == 0) {

                    if (db.ehMesmoCnpj(valores[5])) {
                        throw new Exception("O CNPJ " + valores[5] + " da Pessoa " + valores[0] + " é repetido.");
                    }
                    String nome = valores[2].trim();
                    PessoaJuridica pessoa = new PessoaJuridica(valores[0], nome, valores[4], valores[3],
                            valores[5]);
                    db.adicionaPessoaJuridicas(pessoa);
                    PrestadorServico ps = new PrestadorServico(null, pessoa);
                    db.adicionaPrestador(ps);
                }

                else if (tipo.compareTo("L") == 0) {

                    if (db.ehMesmoCnpj(valores[5])) {
                        throw new Exception("O CNPJ " + valores[5] + " da Pessoa " + valores[0] + " é repetido.");
                    }
                    String nome = valores[2].trim();
                    PessoaJuridica pessoa = new PessoaJuridica(valores[0], nome, valores[4], valores[3],
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

                if (db.verificaLarExiste(valores[0])) {
                    throw new Exception("ID repetido " + valores[0] + " na classe Lar.");
                }

                Lar lar = new Lar(valores[0], valores[3], Integer.parseInt(valores[4]), valores[5]);

                PessoaFisica p1 = db.getPessoaFisicaById(valores[1]);
                PessoaFisica p2 = db.getPessoaFisicaById(valores[2]);

                if (p1 == null && p2 == null) {
                    throw new Exception("ID(s) de Pessoa " + valores[1] + " " + valores[2]
                            + " " + "não cadastrado no Lar de ID " + valores[0] + ".");
                } else if (p1 == null) {
                    throw new Exception(
                            "ID(s) de Pessoa " + valores[1] + " " + "não cadastrado no Lar de ID " + valores[0] + ".");
                } else if (p2 == null) {
                    throw new Exception(
                            "ID(s) de Pessoa " + valores[2] + " " + "não cadastrado no Lar de ID " + valores[0] + ".");
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

                if (db.verificaCasamentoExiste(valores[0])) {
                    throw new Exception("ID repetido " + valores[0] + " na classe Casamento.");
                }

                Date dataCasamento;
                try {
                    dataCasamento = DateFunctions.criaData(valores[3]);
                } catch (ParseException e) {
                    dataCasamento = null;
                }

                Casamento casamento = new Casamento(valores[5], dataCasamento, valores[4], valores[0], null);
                PessoaFisica p1 = db.getPessoaFisicaById(valores[1]);
                PessoaFisica p2 = db.getPessoaFisicaById(valores[2]);
                if (p1 == null && p2 == null) {
                    throw new Exception("ID(s) de Pessoa " + valores[1] + " " + valores[2]
                            + " " + "não cadastrado no Casamento de ID " + valores[0] + ".");
                } else if (p1 == null) {
                    throw new Exception(
                            "ID(s) de Pessoa " + valores[1] + " " + "não cadastrado no Casamento de ID " + valores[0]
                                    + ".");
                } else if (p2 == null) {
                    throw new Exception(
                            "ID(s) de Pessoa " + valores[2] + " " + "não cadastrado no Casamento de ID " + valores[0]
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

                if (db.verificaTarefaExiste(valores[0])) {
                    throw new Exception("ID repetido " + valores[0] + " na classe Tarefa.");
                }

                Date dataInicial;
                try {
                    dataInicial = DateFunctions.criaData(valores[3]);
                } catch (ParseException e) {
                    dataInicial = null;
                }

                valores[5] = valores[5].replace(',', '.');

                Tarefa tarefa = new Tarefa(valores[0], dataInicial, Integer.parseInt(valores[4]),
                        Double.parseDouble(valores[5]), Integer.parseInt(valores[6]));

                Lar lar = db.getLarById(valores[1]);
                if (lar == null) {
                    throw new Exception(
                            "ID(s) de Lar " + valores[1] + " " + "não cadastrado na Tarefa de ID " + valores[0] + ".");
                }
                lar.adicionaTarefa(tarefa);
                PrestadorServico ps = db.getPrestadorById(valores[2]);

                if (ps == null) {

                    PessoaFisica pf = db.getPessoaFisicaById(valores[2]);
                    if (pf == null) {
                        throw new Exception(
                                "ID(s) de Prestador de Serviço " + valores[2] + " " + "não cadastrado na Tarefa de ID "
                                        + valores[0] + ".");
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

                if (db.verificaFestaExiste(valores[0])) {
                    throw new Exception("ID repetido " + valores[0] + " na classe Festa.");
                }

                Date dataFesta;
                try {
                    dataFesta = DateFunctions.criaData(valores[3]);
                } catch (ParseException e) {
                    dataFesta = null;
                }
                valores[5] = valores[5].replace(',', '.');
                int numConvidados = Integer.parseInt(valores[7]);
                Casamento casamento = db.getCasamentoById(valores[1]);
                if (casamento == null) {
                    throw new Exception(
                            "ID(s) de Casamento " + valores[1] + " " + "não cadastrado na Festa de ID "
                                    + valores[0] + ".");
                }
                Festa festa = new Festa(valores[2], dataFesta, valores[4], valores[0], Double.parseDouble(valores[5]),
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

                if (db.verificaCompraExiste(valores[0])) {
                    throw new Exception("ID repetido " + valores[0] + " na classe Compra.");
                }

                valores[5] = valores[5].replace(',', '.');

                Tarefa tarefa = db.getTarefaById(valores[1]);
                if (tarefa == null) {
                    throw new Exception(
                            "ID(s) de Tarefa " + valores[1] + " " + "não cadastrado na Compra de ID "
                                    + valores[0] + ".");
                }
                Date dataCompra = tarefa.getDataInicio();
                Compra compra = new Compra(valores[0], valores[3], Integer.parseInt(valores[4]),
                        Double.parseDouble(valores[5]), Integer.parseInt(valores[6]), dataCompra);
                tarefa.adicionaCompra(compra);
                db.adicionaCompra(compra);

                if (db.verificaLojaExiste(valores[2]) == false) {
                    if (db.verificaPjExiste(valores[2]) == true) {
                        throw new Exception(
                                "ID " + valores[2] + " da Compra de ID " + valores[0]
                                        + " não se refere a uma loja, mas a uma PJ.");
                    } else
                        throw new Exception(
                                "ID(s) de Loja " + valores[2] + " " + "não cadastrado na Compra de ID "
                                        + valores[0] + ".");
                }
                Loja loja = db.getLojaById(valores[2]);
                double valorRecebidoLoja = (double)(Integer.parseInt(valores[4]) * Double.parseDouble(valores[5]));
                System.out.println(valorRecebidoLoja);
                loja.recebeValor(valorRecebidoLoja);
            }
        } catch (IOException e) {
            System.out.println("Erro de IO");
        }
    }
}