package models;

public class Pessoa {

    private String id;
    private String nome;
    private String telefone;
    private String endereco;

    public Pessoa(String id, String nome, String endereco, String telefone){

        this.id = id;
        this.nome = nome;
        this.endereco = endereco;
        this.telefone = telefone;
    }
     public String getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getEndereco() {
        return endereco;
    }

}
