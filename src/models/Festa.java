package models;

import java.util.Date;
import java.util.List;

import date.DateFunctions;

import java.util.ArrayList;

public class Festa extends Evento {

    private double preco;
    private int numConvidados;
    private int numParcelas;
    private int numParcelasAtual;
    private Date dataProximaParcela;
    private List<String> convidados = new ArrayList<String>();

    public Festa(String local, Date data, String hora, String id, double preco, int numConvidados, int numParcelas) {
        super(local, data, hora, id);
        this.preco = preco;
        this.numConvidados = numConvidados;
        this.numParcelas = numParcelas;
        this.numParcelasAtual = numParcelas;
        this.dataProximaParcela = data;
    }

    public Date getDataProximaParcela() {
        return this.dataProximaParcela;
    }

    public double getPreco() {
        return preco;
    }

    public double getNumParcelasAtual() {
        return this.numParcelasAtual;
    }

    public int getNumConvidados() {
        return numConvidados;
    }

    public int getNumParcelas() {
        return numParcelas;
    }

    public double pagaFesta() {
        this.dataProximaParcela = DateFunctions.vaiProProximoMes(this.dataProximaParcela);
        this.numParcelasAtual--;
        return (this.preco) / this.numParcelas;
    }

    public void adicionaConvidado(String convidado) {

        this.convidados.add(convidado);
    }
    public boolean casalEstaNaFesta(String Nome1,String Nome2)
    {
        List <String> convidados = this.convidados;
        int cont = 0;
        for(int i = 0; i < convidados.size(); i++)
        {
            String nomeAtual = convidados.get(i);
            if (nomeAtual.compareTo(Nome1) == 0 || nomeAtual .compareTo(Nome2) == 0) {
                cont++;
            }
        }
        if(cont == 2) return true;
        else 
        return false;
    }
    public Date obtemDataFim() {

        return DateFunctions.somaMeses(getData(), numParcelas);
    }

    public void printFesta() {
        System.out.println("Festa:");
        System.out.println("ID:" + this.getId());
        System.out.printf("Data:");
        DateFunctions.printData(this.getData());
        System.out.println("Hora: " + this.getHora());
        System.out.println("Local: " + this.getLocal());
        System.out.println("Preco: " + this.preco);
        System.out.println("Parcelas: " + this.numParcelas);
        System.out.println("Convidados: ");

        for (int i = 0; i < convidados.size(); i++) {

            String c = convidados.get(i);
            System.out.println(c);
        }
    }
}