package data;
import java.util.List;

import models.Casal;
import models.Loja;
import models.PessoaFisica;
import models.PessoaJuridica;

import java.util.ArrayList;

public class Database {
    
    private List<PessoaFisica> pessoasFisicas = new ArrayList<PessoaFisica>();
    private List<PessoaJuridica> pessoasJuridicas = new ArrayList<PessoaJuridica>();
    private List<Loja> lojas = new ArrayList<Loja>();
    private List<Casal> casais = new ArrayList<Casal>();

    public void adicionaPessoaFisica(PessoaFisica pf){

        this.pessoasFisicas.add(pf);
    }
    
    public void adicionaPessoaJuridicas(PessoaJuridica pj){

        this.pessoasJuridicas.add(pj);
    }

    public void adicionaLojas(Loja loja){

        this.lojas.add(loja);
    }
    
    public void adicionaCasal(Casal casal){

        this.casais.add(casal);
    }
}
