package models;

public class Loja {

    private PessoaJuridica pessoaJuridica;
    private double valorRecebido;

    public Loja(PessoaJuridica pessoaJuridica) {
        this.pessoaJuridica = pessoaJuridica;
        this.valorRecebido = 0;
    }

    public PessoaJuridica getPessoaJuridica() {
        return pessoaJuridica;
    }

    public double getValorRecebido() {
        return valorRecebido;
    }
}