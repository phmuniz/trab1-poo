package data;

import java.util.List;

import models.Casal;
import models.Loja;
import models.PessoaFisica;
import models.PessoaJuridica;
import models.Casamento;
import models.Lar;
import models.PrestadorServico;
import models.Tarefa;
import models.Festa;
import models.Compra;

import java.util.ArrayList;

public class Database {

    private List<PessoaFisica> pessoasFisicas = new ArrayList<PessoaFisica>();
    private List<PessoaJuridica> pessoasJuridicas = new ArrayList<PessoaJuridica>();
    private List<Loja> lojas = new ArrayList<Loja>();
    private List<Casal> casais = new ArrayList<Casal>();
    private List<Casamento> casamentos = new ArrayList<Casamento>();
    private List<Lar> lares = new ArrayList<Lar>();
    private List<PrestadorServico> prestadoresServico = new ArrayList<PrestadorServico>();
    private List<Tarefa> tarefas = new ArrayList<Tarefa>();
    private List<Festa> festas = new ArrayList<Festa>();
    private List<Compra> compras = new ArrayList<Compra>();

    // FUNCOES PESSOAS
    public boolean verificaPessoaExiste(String id) {
        List<PessoaFisica> pessoasF = this.pessoasFisicas;
        List<PessoaJuridica> pessoasJ = this.pessoasJuridicas;
        for (int i = 0; i < pessoasF.size(); i++) {
            PessoaFisica pF = pessoasF.get(i);
            if (id.compareTo(pF.getId()) == 0) {
                return true;
            }

        }
        for (int i = 0; i < pessoasJ.size(); i++) {
            PessoaJuridica pJ = pessoasJ.get(i);
            if (id.compareTo(pJ.getId()) == 0) {
                return true;
            }

        }
        return false;

    }

    // FUNCOES PESSOA FISICA
    public void adicionaPessoaFisica(PessoaFisica pf) {

        this.pessoasFisicas.add(pf);
    }

    public PessoaFisica getPessoaFisicaById(String id) {

        for (int i = 0; i < this.pessoasFisicas.size(); i++) {

            PessoaFisica pf = this.pessoasFisicas.get(i);

            if (id.compareTo(pf.getId()) == 0) {
                return pf;
            }
        }

        return null;
    }

    public boolean ehMesmoCpf(String cpf){

        for (int i = 0; i < this.pessoasFisicas.size(); i++) {
            PessoaFisica pF = this.pessoasFisicas.get(i);
            if (cpf.compareTo(pF.getCpf()) == 0) {
                return true;
            }
        }

        return false;
    }

    // FUNCOES PESSOA JURIDICA
    public PessoaJuridica getPessoaJuridicaById(String id) {

        for (int i = 0; i < this.pessoasJuridicas.size(); i++) {

            PessoaJuridica pj = this.pessoasJuridicas.get(i);

            if (id.compareTo(pj.getId()) == 0) {
                return pj;
            }
        }
        return null;
    }

    public void adicionaPessoaJuridicas(PessoaJuridica pj) {

        this.pessoasJuridicas.add(pj);
    }

    public boolean ehMesmoCnpj(String cnpj){

        for (int i = 0; i < this.pessoasJuridicas.size(); i++) {
            PessoaJuridica pj = this.pessoasJuridicas.get(i);
            if (cnpj.compareTo(pj.getCnpj()) == 0) {
                return true;
            }
        }

        return false;
    }

    // FUNCOES LOJAS
    public void adicionaLojas(Loja loja) {

        this.lojas.add(loja);
    }

    public Loja getLojaById(String id) {

        for (int i = 0; i < this.lojas.size(); i++) {

            Loja loja = this.lojas.get(i);

            if (id.compareTo(loja.getId()) == 0) {
                return loja;
            }
        }
        return null;
    }

    public List<Loja> getLojas() {

        return this.lojas;
    }

    // FUNCOES CASAIS
    public void adicionaCasal(Casal casal) {

        this.casais.add(casal);
    }

    public Casal getCasalById(String id1, String id2) {

        for (int i = 0; i < this.casais.size(); i++) {

            Casal casal = this.casais.get(i);

            PessoaFisica p1 = casal.getPessoa1();
            PessoaFisica p2 = casal.getPessoa2();

            if ((id1.compareTo(p1.getId()) == 0 && id2.compareTo(p2.getId()) == 0)
                    || (id1.compareTo(p2.getId()) == 0 && id2.compareTo(p1.getId()) == 0)) {
                return casal;
            }
        }
        return null;

    }

    public Casal getCasalByCpf(String cpf1, String cpf2) {

        for (int i = 0; i < this.casais.size(); i++) {

            Casal casal = this.casais.get(i);

            PessoaFisica p1 = casal.getPessoa1();
            PessoaFisica p2 = casal.getPessoa2();

            if ((cpf1.compareTo(p1.getCpf()) == 0 && cpf2.compareTo(p2.getCpf()) == 0)
                    || (cpf1.compareTo(p2.getCpf()) == 0 && cpf2.compareTo(p1.getCpf()) == 0)) {
                return casal;
            }
        }
        return null;

    }

    public List<Casal> getCasais() {

        return this.casais;
    }

    // FUNCOES CASAMENTO
    public void adicionaCasamento(Casamento casamento) {

        this.casamentos.add(casamento);
    }

    public Casamento getCasamentoById(String id) {

        for (int i = 0; i < this.casamentos.size(); i++) {

            Casamento casamento = this.casamentos.get(i);

            if ((id.compareTo(casamento.getId()) == 0)) {
                return casamento;
            }
        }
        return null;

    }

    public boolean verificaCasamentoExiste(String id) {
        
        for (int i = 0; i < this.casamentos.size(); i++) {
            
            Casamento casamento = this.casamentos.get(i);
            if (id.compareTo(casamento.getId()) == 0) {
                return true;
            }

        }

        return false;
    }

    // FUNCOES LAR
    public void adicionaLar(Lar lar) {

        this.lares.add(lar);
    }

    public Lar getLarById(String id) {

        for (int i = 0; i < this.lares.size(); i++) {

            Lar lar = this.lares.get(i);

            if ((id.compareTo(lar.getId()) == 0)) {
                return lar;
            }
        }
        return null;

    }

    public boolean verificaLarExiste(String id) {
        
        for (int i = 0; i < this.lares.size(); i++) {
            
            Lar lar = this.lares.get(i);
            if (id.compareTo(lar.getId()) == 0) {
                return true;
            }

        }

        return false;
    }

    // FUNCOES PRESTADORES
    public void adicionaPrestador(PrestadorServico prestador) {

        this.prestadoresServico.add(prestador);
    }

    public PrestadorServico getPrestadorById(String id) {

        for (int i = 0; i < this.prestadoresServico.size(); i++) {

            PrestadorServico prestador = this.prestadoresServico.get(i);

            if ((id.compareTo(prestador.getId()) == 0)) {
                return prestador;
            }
        }

        return null;
    }

    public List<PrestadorServico> getPrestadoresServico() {

        return this.prestadoresServico;
    }

    // FUNCOES TAREFAS
    public void adicionaTarefa(Tarefa tarefa) {

        this.tarefas.add(tarefa);
    }

    public Tarefa getTarefaById(String id) {

        for (int i = 0; i < this.tarefas.size(); i++) {

            Tarefa tarefa = this.tarefas.get(i);

            if ((id.compareTo(tarefa.getId()) == 0)) {
                return tarefa;
            }
        }

        return null;
    }

    public boolean verificaTarefaExiste(String id) {
        
        for (int i = 0; i < this.tarefas.size(); i++) {
            
            Tarefa tarefa = this.tarefas.get(i);
            if (id.compareTo(tarefa.getId()) == 0) {
                return true;
            }

        }

        return false;
    }

    // FUNCOES FESTAS
    public void adicionaFestas(Festa festa) {

        this.festas.add(festa);
    }

    public List<Festa> getFestas() {

        return this.festas;
    }

    public boolean verificaFestaExiste(String id) {
        
        for (int i = 0; i < this.festas.size(); i++) {
            
            Festa festa = this.festas.get(i);
            if (id.compareTo(festa.getId()) == 0) {
                return true;
            }

        }

        return false;
    }

    // FUNCOES COMPRAS
    public void adicionaCompra(Compra compra) {

        this.compras.add(compra);
    }

    public boolean verificaCompraExiste(String id) {
        
        for (int i = 0; i < this.compras.size(); i++) {
            
            Compra compra = this.compras.get(i);
            if (id.compareTo(compra.getId()) == 0) {
                return true;
            }

        }

        return false;
    }
}
