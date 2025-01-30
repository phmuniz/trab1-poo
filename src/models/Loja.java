package models;

public class Loja {

    private PessoaJuridica pessoaJuridica;
    private double valorRecebido;

    public Loja(PessoaJuridica pessoaJuridica, double valorRecebido) {
        this.pessoaJuridica = pessoaJuridica;
        this.valorRecebido = valorRecebido;
    }

    public PessoaJuridica getPessoaJuridica() {
        return pessoaJuridica;
    }

    public double getValorRecebido() {
        return valorRecebido;
    }
}