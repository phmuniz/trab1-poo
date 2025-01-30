package models;
import java.util.Date;
import java.util.List;
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
}