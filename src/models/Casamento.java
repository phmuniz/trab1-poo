package models;
import date.DateFunctions;
import java.util.Date;

public class Casamento extends Evento {
    private Festa festa;

    public Casamento(String local, Date data, String hora,String id, Festa festa) {
        super(local, data, hora,id);

        if(festa != null){
            this.festa = festa;
        }
    }
    public void setFesta(Festa festa){

        this.festa = festa;
    }
    public boolean temFesta(){

        if(festa == null) return false;

        return true;
    }
    public void printCasamento(){
        System.out.println("Id:" + this.getId());
        System.out.println("Local:" + this.getLocal());
        System.out.printf("Data: ");
        DateFunctions.printData(this.getData());
        System.out.println("Hora:"+ this.getHora());

        if(this.festa != null) this.festa.printFesta();
    }
}