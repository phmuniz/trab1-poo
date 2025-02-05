package models;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;

public class Tarefa {
    
    private String id;
    private Date dataInicio;
    private int prazoEntrega;
    private double valorPrestador;
    private int numParcelas;
    private List<Compra> compras = new ArrayList<Compra>();

    public Tarefa(String id, Date dataInicio, int prazoEntrega, double valorPrestador, int numParcelas){

        this.id = id;
        this.dataInicio = dataInicio;
        this.prazoEntrega = prazoEntrega;
        this.valorPrestador = valorPrestador;
        this.numParcelas = numParcelas;
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
    public void adicionaCompra(Compra compra){

        this.compras.add(compra);
    }
    public void printTarefa(){

        System.out.println("Tarefa " + this.id);
    }
}
