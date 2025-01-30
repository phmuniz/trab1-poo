package models;

public class Compra {
    
    private String id;
    private String nomeProduto;
    private int qtdProduto;
    private double precoUnitario;
    private int numParcelas;

    public Compra(String id, String nomeProduto, int qtdProduto, double precoUnitario, int numParcelas){

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
}
