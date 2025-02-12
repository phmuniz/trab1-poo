package models;

import java.util.Date;
import date.DateFunctions;

public class Compra {

    private String id;
    private String nomeProduto;
    private int qtdProduto;
    private double precoUnitario;
    private int numParcelas;
    private int numParcelasAtual;
    private Date dataProximaParcela;

    public Compra(String id, String nomeProduto, int qtdProduto, double precoUnitario, int numParcelas,
            Date dataCompra) {

        this.id = id;
        this.nomeProduto = nomeProduto;
        this.qtdProduto = qtdProduto;
        this.precoUnitario = precoUnitario;
        this.numParcelas = numParcelas;
        this.numParcelasAtual = numParcelas;
        this.dataProximaParcela = dataCompra;
    }

    public String getId() {
        return id;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public int getQtdProduto() {
        return qtdProduto;
    }

    public double getPrecoUnitario() {
        return precoUnitario;
    }

    public int getNumParcelas() {
        return numParcelas;
    }

    public int getNumParcelasAtual() {
        return numParcelasAtual;
    }

    public double pagaCompra(Date dataAtual) {
        if (DateFunctions.ehMesmoMes(dataAtual, this.dataProximaParcela) && this.numParcelasAtual > 0) {
            this.dataProximaParcela = DateFunctions.vaiProProximoMes(this.dataProximaParcela);
            this.numParcelasAtual--;

            return (this.qtdProduto * this.precoUnitario) / this.numParcelas;
        }

        return 0;
    }

    public Date obtemDataFim(Date dataInicio) {

        return DateFunctions.somaMeses(dataInicio, numParcelas);
    }

    public void printCompra() {
        System.out.println("ID:" + this.id);
        System.out.println("Nome do Produto:" + this.nomeProduto);
        System.out.println("Quantidade do produto:" + this.qtdProduto);
        System.out.println("Preco:" + this.precoUnitario);
        System.out.println("Quantidade de Parcelas:" + this.numParcelas);
    }
}
