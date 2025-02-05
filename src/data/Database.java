package data;
import java.util.List;

import models.Casal;
import models.Loja;
import models.PessoaFisica;
import models.PessoaJuridica;
import models.Casamento;
import models.Lar;
import models.PrestadorServico;

import java.util.ArrayList;

public class Database {
    
    private List<PessoaFisica> pessoasFisicas = new ArrayList<PessoaFisica>();
    private List<PessoaJuridica> pessoasJuridicas = new ArrayList<PessoaJuridica>();
    private List<Loja> lojas = new ArrayList<Loja>();
    private List<Casal> casais = new ArrayList<Casal>();
    private List<Casamento> casamentos = new ArrayList<Casamento>();
    private List<Lar> lares = new ArrayList<Lar>();
    private List<PrestadorServico> prestadoresServico = new ArrayList<PrestadorServico>();


    // FUNCOES PESSOA FISICA
    public void adicionaPessoaFisica(PessoaFisica pf){

        this.pessoasFisicas.add(pf);
    }

    public PessoaFisica getPessoaFisicaById(String id){

        for(int i = 0; i < this.pessoasFisicas.size(); i++){

            PessoaFisica pf = this.pessoasFisicas.get(i);

            if(id.compareTo(pf.getId()) == 0){
                return pf;
            }
        }

        return null;
    }

    //FUNCOES PESSOA JURIDICA
    public PessoaJuridica getPessoaJuridicaById(String id){

        for(int i = 0; i < this.pessoasJuridicas.size(); i++){

            PessoaJuridica pj = this.pessoasJuridicas.get(i);

            if(id.compareTo(pj.getId()) == 0){
                return pj;
            }
        }
        return null;
    }
    
    public void adicionaPessoaJuridicas(PessoaJuridica pj){

        this.pessoasJuridicas.add(pj);
    }


    //FUNCOES LOJAS
    public void adicionaLojas(Loja loja){

        this.lojas.add(loja);
    }
    

    //FUNCOES CASAIS
    public void adicionaCasal(Casal casal){

        this.casais.add(casal);
    }

    public Casal getCasalById(String id1, String id2){
        
         for(int i = 0; i < this.casais.size(); i++){

            Casal casal = this.casais.get(i);
            
            PessoaFisica p1 = casal.getPessoa1();
            PessoaFisica p2 = casal.getPessoa2();

            if((id1.compareTo(p1.getId()) == 0 && id2.compareTo(p2.getId()) == 0) || (id1.compareTo(p2.getId()) == 0 && id2.compareTo(p1.getId()) == 0)){
                return casal;
            }
        }
        return null;
        
    }

    public List<Casal> getCasais(){

        return this.casais;
    }
    
    //FUNCOES CASAMENTO
    public void adicionaCasamento(Casamento casamento){

        this.casamentos.add(casamento);
    }
    

    //FUNCOES LAR
    public void adicionaLar(Lar lar){

        this.lares.add(lar);
    }

    public Lar getLarById(String id){
        
         for(int i = 0; i < this.lares.size(); i++){

              Lar lar = this.lares.get(i);

            if((id.compareTo(lar.getId()) == 0)){
                return lar;
            }
        }
        return null;
        
    }

    //FUNCOES PRESTADORES
    public void adicionaPrestador(PrestadorServico prestador){

        this.prestadoresServico.add(prestador);
    }

    public PrestadorServico getPrestadorById(String id){
        
         for(int i = 0; i < this.lares.size(); i++){

              PrestadorServico prestador = this.prestadoresServico.get(i);

            if((id.compareTo(prestador.getId()) == 0)){
                return prestador;
            }
        }
        return null;
        
    }
}
