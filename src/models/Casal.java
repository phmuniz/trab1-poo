package models;

import java.util.Date;

import date.DateFunctions;

public class Casal {
    
    private PessoaFisica pessoa1;
    private PessoaFisica pessoa2;
    private Lar lar;
    private Casamento casamento;
    private Date dataInicioPlanejamento;
    private Date dataFimPlanejamento;

    public Casal(PessoaFisica pessoa1, PessoaFisica pessoa2, Lar lar, Casamento casamento){

        this.pessoa1 = pessoa1;
        this.pessoa2 = pessoa2;
        this.lar = lar;
        this.casamento = casamento;
    }

    public PessoaFisica getPessoa1() {
        return pessoa1;
    }

    public PessoaFisica getPessoa2() {
        return pessoa2;
    }

    public Lar getLar() {
        return lar;
    }

    public Casamento getCasamento() {
        return casamento;
    }

    public void setCasamento(Casamento casamento){
        this.casamento = casamento;
    }

    public void obtemDataInicioFimPlanejamento(){
        
        if(this.casamento != null && this.casamento.temFesta()){
            Date dataInicioCasamento = this.casamento.obtemDataInicioPlanejamento();
            Date dataFimCasamento = this.casamento.obtemDataFimPlanejamento();

            this.setDataInicioPlanejamento(dataInicioCasamento);
            this.setDataFimPlanejamento(dataFimCasamento);
        }

        if(this.lar != null){
            Date dataInicioLar = this.lar.obtemDataInicioPlanejamento();

            if(dataInicioLar.before(this.dataInicioPlanejamento)){
                this.setDataInicioPlanejamento(dataInicioLar);
            }
        }
    }

    public void setDataInicioPlanejamento(Date data){
        this.dataInicioPlanejamento = data;
    }

    public void setDataFimPlanejamento(Date data){
        this.dataFimPlanejamento = data;
    }
    
    public void printCasal(){
        this.pessoa1.printPessoaFisica();
        this.pessoa2.printPessoaFisica();
        if(this.lar != null) this.lar.printLar();
        if(this.casamento != null){
            this.casamento.printCasamento();
            
            if(this.casamento.temFesta()){
                DateFunctions.printData(this.dataInicioPlanejamento);
                DateFunctions.printData(this.dataFimPlanejamento);
            }
        } 
    }
}
