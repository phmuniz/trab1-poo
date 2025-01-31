package models;
import java.util.List;
import java.util.ArrayList;

public class Lar {
    
    private String id;
    private String rua;
    private int numero;
    private String complemento;
    private List<Tarefa> tarefas = new ArrayList<Tarefa>();

    public Lar (String id, String rua,int numero,String complemento){
        this.id = id;
        this.rua = rua;
        this.numero = numero;
        this.complemento = complemento;
    }

    public String getRua() {
        return rua;
    }

    public int getNumero() {
        return numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void adicionaTarefa(Tarefa tarefa){

        this.tarefas.add(tarefa);
    }

    public void printLar(){

        System.out.println("Id: " + this.id);
        System.out.println("Rua: " + this.rua);
        System.out.println("Numero: " + this.numero);
        System.out.println("Complemento: " + this.complemento);
    }
}
