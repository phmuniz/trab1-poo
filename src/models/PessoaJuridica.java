package models;

public class PessoaJuridica extends Pessoa {

    private String cnpj;

    public PessoaJuridica(String id, String nome,String endereco,String telefone,String cnpj){
        super(id, nome, endereco, telefone);
        this.cnpj = cnpj;
    }
    public String getCnpj(){
        return cnpj;
    }
}
