package models;
import java.util.Date;

import date.DateFunctions;

public class PessoaFisica extends Pessoa {

    private Date dataNascimento;
    private String cpf;
    private double poupanca;
    private double salario;
    private double gastoMensal;

    public PessoaFisica(String id, String nome, String endereco, String telefone, Date dataNascimento, String cpf,double poupanca,
    double salario,double gastoMensal){

        super(id, nome, endereco, telefone);
        this.dataNascimento = dataNascimento;
        this.cpf = cpf;
        this.poupanca = poupanca;
        this.salario = salario;
        this.gastoMensal = gastoMensal;
    }
     public Date getDataNascimento() {
        return dataNascimento;
    }

    public String getCpf() {
        return cpf;
    }

    public double getPoupanca() {
        return poupanca;
    }

    public double getSalario() {
        return salario;
    }

    public double getGastoMensal() {
        return gastoMensal;
    }
    public void atualizaPoupanca(Date dataAtual){
        double rendimento = this.poupanca*0.005;
        if(DateFunctions.ehDezembro(dataAtual)){
            this.poupanca = this.poupanca + 2*salario + rendimento - this.gastoMensal;
        }else
        this.poupanca = this.poupanca + salario + rendimento - this.gastoMensal;
    }
    public void printPessoaFisica(){

        System.out.println("Nome: " + this.getNome());
        System.out.println("Id: " + this.getId());
        System.out.println("Endereco: " + this.getEndereco());
        System.out.println("Telefone: " + this.getTelefone());
        System.out.println("CPF: " + this.getCpf());
        System.out.printf("Data Nascimento: ");
        DateFunctions.printData(this.dataNascimento);
        System.out.println("Poupanca: " + this.poupanca);
        System.out.println("Salario: " + this.salario);
        System.out.println("Gasto Mensal: " + this.gastoMensal);
    }
}
