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
    private double saldoCasal;

    public Casal(PessoaFisica pessoa1, PessoaFisica pessoa2, Lar lar, Casamento casamento) {

        this.pessoa1 = pessoa1;
        this.pessoa2 = pessoa2;
        this.lar = lar;
        this.casamento = casamento;
        this.saldoCasal = this.pessoa1.getPoupanca() + this.pessoa2.getPoupanca();
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

    public void setCasamento(Casamento casamento) {
        this.casamento = casamento;
    }

    private void atualizaSaldoCasal(Date dataAtual){

        double rendimento = this.saldoCasal*0.005;
        double salarioCasal = this.pessoa1.getSalario() + this.pessoa2.getSalario();
        double gastoMensalCasal = this.pessoa1.getGastoMensal() + this.pessoa2.getGastoMensal();

        if(DateFunctions.ehDezembro(dataAtual)){
            this.saldoCasal = this.saldoCasal + 2*salarioCasal + rendimento - gastoMensalCasal;
        }
        else
        this.saldoCasal = this.saldoCasal + salarioCasal + rendimento - gastoMensalCasal;
    }

    public double obtemSaldoCasal() {
        Date dataAtual = this.dataInicioPlanejamento;
        
        while (true) {
            
            this.atualizaSaldoCasal(dataAtual);

            if (this.casamento != null && this.casamento.temFesta()) {

                Festa f = this.casamento.getFesta();

                Date dataProximaParcelaFesta = f.getDataProximaParcela();

                if (DateFunctions.ehMesmoMes(dataAtual, dataProximaParcelaFesta) && f.getNumParcelasAtual() > 0) {

                    this.saldoCasal -= f.pagaFesta();
                }
            }

            if (this.lar != null) {

                this.saldoCasal -= this.lar.pagaTarefasLar(dataAtual);
            }

            if (DateFunctions.ehMesmoMes(dataAtual, this.dataFimPlanejamento)) break;

            dataAtual = DateFunctions.vaiProProximoMes(dataAtual);
            
            System.out.println(this.saldoCasal);
        }

        return this.saldoCasal;
    }

    public void obtemDataInicioFimPlanejamento() {

        if (this.casamento != null && this.casamento.temFesta()) {
            Date dataInicioCasamento = this.casamento.obtemDataInicioPlanejamento();
            Date dataFimCasamento = this.casamento.obtemDataFimPlanejamento();

            this.setDataInicioPlanejamento(dataInicioCasamento);
            this.setDataFimPlanejamento(dataFimCasamento);
        }

        if (this.lar != null) {
            Date dataInicioLar = this.lar.obtemDataInicioPlanejamento();
            Date dataFimLar = this.lar.obtemDataFimPlanejamento();

            if (dataInicioLar.before(this.dataInicioPlanejamento)) {
                this.setDataInicioPlanejamento(dataInicioLar);
            }
            if (dataFimLar.after(this.dataFimPlanejamento)) {
                this.setDataFimPlanejamento(dataFimLar);
            }
        }
    }

    public void setDataInicioPlanejamento(Date data) {
        this.dataInicioPlanejamento = data;
    }

    public void setDataFimPlanejamento(Date data) {
        this.dataFimPlanejamento = data;
    }

    public void printCasal() {
        this.pessoa1.printPessoaFisica();
        this.pessoa2.printPessoaFisica();
        if (this.lar != null)
            this.lar.printLar();
        if (this.casamento != null) {
            this.casamento.printCasamento();

            if (this.casamento.temFesta()) {
                DateFunctions.printData(this.dataInicioPlanejamento);
                DateFunctions.printData(this.dataFimPlanejamento);
            }
        }
    }
}
