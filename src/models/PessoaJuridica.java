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
    public void printPessoaJuridica(){

        System.out.println("Nome: " + this.getNome());
        System.out.println("Id: " + this.getId());
        System.out.println("Endereco: " + this.getEndereco());
        System.out.println("Telefone: " + this.getTelefone());
        System.out.println("CNPJ: " + this.cnpj);
        
    }
}
