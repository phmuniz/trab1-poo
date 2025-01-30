package models;

public class PrestadorServico {
    private String tipo;
    private PessoaFisica pessoaFisica;
    private PessoaJuridica pessoaJuridica;
    private double valorRecebido;

    public PrestadorServico(String tipo,PessoaFisica pessoaFisica, PessoaJuridica pessoaJuridica)
    {
        if (pessoaFisica != null) {
            this.pessoaFisica = pessoaFisica;
        }else if(pessoaJuridica != null)
        this.pessoaJuridica = pessoaJuridica;
        this.tipo = tipo;
        this.valorRecebido = 0;
    }
     public String getTipo() {
        return tipo;
    }

    public PessoaFisica getPessoaFisica() {
        return pessoaFisica;
    }

    public PessoaJuridica getPessoaJuridica() {
        return pessoaJuridica;
    }

    public double getValorRecebido() {
        return valorRecebido;
    }
}