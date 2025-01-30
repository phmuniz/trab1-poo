package models;
import java.util.Date;
public class Evento {

    private String id;
    private String local;
    private Date data;
    private String hora;

    public Evento(String local,Date data,String hora,String id)
    {
        this.local = local;
        this.data = data;
        this.hora = hora;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getLocal() {
        return local;
    }

    public Date getData() {
        return data;
    }

    public String getHora() {
        return hora;
    }
}