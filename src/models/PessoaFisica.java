package models;
import java.util.Date;

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
}
