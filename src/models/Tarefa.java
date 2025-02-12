package models;

import java.util.Date;
import java.util.List;

import date.DateFunctions;

import java.util.ArrayList;

public class Tarefa {

    private String id;
    private Date dataInicio;
    private int prazoEntrega;
    private double valorPrestador;
    private int numParcelas;
    private int numParcelasAtual;
    private Date dataProximaParcela;
    private List<Compra> compras = new ArrayList<Compra>();

    public Tarefa(String id, Date dataInicio, int prazoEntrega, double valorPrestador, int numParcelas) {

        this.id = id;
        this.dataInicio = dataInicio;
        this.dataProximaParcela = dataInicio;
        this.prazoEntrega = prazoEntrega;
        this.valorPrestador = valorPrestador;
        this.numParcelas = numParcelas;
        this.numParcelasAtual = numParcelas;
    }

    public String getId() {
        return id;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public int getPrazoEntrega() {
        return prazoEntrega;
    }

    public double getValorPrestador() {
        return valorPrestador;
    }

    public int getNumParcelas() {
        return numParcelas;
    }

    public void adicionaCompra(Compra compra) {

        this.compras.add(compra);
    }

    public double pagaTarefa(Date dataAtual) {

        double valorPagar = 0;

        if (DateFunctions.ehMesmoMes(dataAtual, this.dataProximaParcela) && this.numParcelasAtual > 0) {
            this.dataProximaParcela = DateFunctions.vaiProProximoMes(this.dataProximaParcela);
            this.numParcelasAtual--;

            valorPagar += this.valorPrestador / this.numParcelas;
        }

        for (int i = 0; i < this.compras.size(); i++) {
            Compra c = this.compras.get(i);
            valorPagar += c.pagaCompra(dataAtual);
        }

        return valorPagar;
    }

    public Date obtemDataFim() {

        Date dataFim = new Date();

        for (int i = 0; i < this.compras.size(); i++) {
            Compra c = this.compras.get(i);
            Date dataAtual = c.obtemDataFim(this.dataInicio);
            if (i == 0)
                dataFim = dataAtual;

            if (dataAtual.after(dataFim)) {

                dataFim = dataAtual;
            }
        }
        return dataFim;
    }

    public void printTarefa() {

        System.out.println("Tarefa " + this.id);
        System.out.println("Prazo: " + this.prazoEntrega);
        System.out.println("Valor do Prestador: " + this.valorPrestador);
        System.out.println("Parcelas: " + this.numParcelas);
        System.out.println("Compras: ");

        for (int i = 0; i < this.compras.size(); i++) {
            Compra c = this.compras.get(i);
            c.printCompra();
        }
    }
}
