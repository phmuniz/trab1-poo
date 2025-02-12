package models;
import java.util.Date;
import java.util.List;

import date.DateFunctions;

import java.util.ArrayList;

public class Festa extends Evento{
    
    private double preco;
    private int numConvidados;
    private int numParcelas;
    private List<String> convidados = new ArrayList<String>();

    public Festa(String local,Date data,String hora, String id,double preco, int numConvidados, int numParcelas){
        super(local,data,hora,id);
        this.preco = preco;
        this.numConvidados = numConvidados;
        this.numParcelas = numParcelas;
    }

    public double getPreco() {
        return preco;
    }

    public int getNumConvidados() {
        return numConvidados;
    }

    public int getNumParcelas() {
        return numParcelas;
    }

    public void adicionaConvidado(String convidado){

        this.convidados.add(convidado);
    }

    public Date obtemDataFim(){
        
        return DateFunctions.somaMeses(getData(), numParcelas);
    }

    public void printFesta(){
        System.out.println("Festa:");
        System.out.println("ID:" + this.getId());
        System.out.printf("Data:");
        DateFunctions.printData(this.getData());
        System.out.println("Hora: " + this.getHora());
        System.out.println("Local: " + this.getLocal());
        System.out.println("Preco: " + this.preco);
        System.out.println("Parcelas: " + this.numParcelas);
        System.out.println("Convidados: ");

        for(int i = 0; i < convidados.size(); i++){

            String c = convidados.get(i);
            System.out.println(c);
        }
    }
}