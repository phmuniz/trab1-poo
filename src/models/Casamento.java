package models;

import java.util.Date;

public class Casamento extends Evento {
    private Festa festa;

    public Casamento(String local, Date data, String hora,String id, Festa festa) {
        super(local, data, hora,id);

        if(festa != null){
            this.festa = festa;
        }
    }

    public boolean temFesta(){

        if(festa == null) return false;

        return true;
    }
}