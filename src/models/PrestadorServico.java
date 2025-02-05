package models;

public class PrestadorServico {

    private PessoaFisica pessoaFisica;
    private PessoaJuridica pessoaJuridica;
    private double valorRecebido;

    public PrestadorServico(PessoaFisica pessoaFisica, PessoaJuridica pessoaJuridica)
    {
        if (pessoaFisica != null) {
            this.pessoaFisica = pessoaFisica;
        }
        
        else if(pessoaJuridica != null){
            this.pessoaJuridica = pessoaJuridica;
        }
        this.valorRecebido = 0;
    }

    public String getId() {
        
        if(this.pessoaFisica != null){
            return this.pessoaFisica.getId();
        }
        else if(this.pessoaJuridica != null){
            return this.pessoaJuridica.getId();
        }
        else return null;
    }

    public String getTipo() {
        return this.pessoaFisica != null ? "PF" : "PJ";
    }

    public double getValorRecebido() {
        return valorRecebido;
    }

    public void recebeValor(double valor){
        this.valorRecebido += valor;
    }
}