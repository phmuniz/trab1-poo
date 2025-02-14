package models;

public class Loja {

    private PessoaJuridica pessoaJuridica;
    private double valorRecebido;

    public Loja(PessoaJuridica pessoaJuridica) {
        this.pessoaJuridica = pessoaJuridica;
        this.valorRecebido = 0;
    }

    public String getId() {
        return this.pessoaJuridica.getId();
    }
     public String getNome() {
        return this.pessoaJuridica.getNome();
    }
    
    public double getValorRecebido() {
        return valorRecebido;
    }

    public void recebeValor(double valor){
        this.valorRecebido += valor;
    }

    public void printLoja(){

        System.out.println("Loja " + this.getId());
        System.out.println("Valor recebido: " + this.valorRecebido);
    }
}