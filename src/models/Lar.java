package models;
import java.util.List;
import java.util.ArrayList;

public class Lar {
    
    private String rua;
    private int numero;
    private String complemento;
    private List<Tarefa> tarefas = new ArrayList<Tarefa>();

    public Lar (String rua,int numero,String complemento){
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
}
