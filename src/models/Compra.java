package models;

public class Compra {
    
    private String id;
    private String nomeProduto;
    private int qtdProduto;
    private double precoUnitario;
    private int numParcelas;

    public Compra(String id, String nomeProduto, int qtdProduto, double precoUnitario, int numParcelas){

        this.id = id;
        this.nomeProduto = nomeProduto;
        this.qtdProduto = qtdProduto;
        this.precoUnitario = precoUnitario;
        this.numParcelas = numParcelas;
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
    public void printCompra()
    {
        System.out.println("ID:" + this.id);
        System.out.println("Nome do Produto:" + this.nomeProduto);
        System.out.println("Quantidade do produto:" + this.qtdProduto);
        System.out.println("Preco:" + this.precoUnitario);
        System.out.println("Quantidade de Parcelas:" + this.numParcelas);
    }
}
