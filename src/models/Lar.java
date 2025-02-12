package models;
import java.util.List;
import java.util.ArrayList;

import java.util.Date;

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

    public String getId() {
        return id;
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

    public Date obtemDataInicioPlanejamento(){

        Date dataInicio = new Date();

        for(int i = 0; i < this.tarefas.size(); i++){
            Tarefa t = this.tarefas.get(i);
            
            if(i == 0){
                dataInicio = t.getDataInicio();
            }

            if(t.getDataInicio().before(dataInicio)){
                dataInicio = t.getDataInicio();
            }
        }

        return dataInicio;
    }

    public void printLar(){

        System.out.println("Id: " + this.id);
        System.out.println("Rua: " + this.rua);
        System.out.println("Numero: " + this.numero);
        System.out.println("Complemento: " + this.complemento);
        for(int i = 0; i < this.tarefas.size(); i++){
            Tarefa t = this.tarefas.get(i);
            t.printTarefa();
        }
    }
}
