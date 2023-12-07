package model;
import java.util.ArrayList;
/*
 * @author alfonso
 */
public class Conversacion {
    String identificador;
    ArrayList<Message> mensajes;
    long fechaInicio, fechaFin;
    
    //Constructor
    public Conversacion(String identificador, ArrayList<Message> mensajes, long fechaInicio, long fechaFin){
        this.identificador=identificador;
        this.mensajes=mensajes;
        this.fechaInicio=fechaInicio;
        this.fechaFin=fechaFin;
    }
    
    public Conversacion(Conversacion otraConversacion){
        this.identificador=otraConversacion.identificador;
        this.mensajes=otraConversacion.mensajes;
        this.fechaInicio=otraConversacion.fechaInicio;
        this.fechaFin=otraConversacion.fechaFin;
    }
    
    //Setters y getters
    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }
    
    public String getIdentificador() {
        return identificador;
    }

    public void setMensajes(ArrayList<Message> mensajes) {
        this.mensajes = mensajes;
    }

    public ArrayList<Message> getMensajes() {
        return mensajes;
    }

    public void setFechaInicio(long fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public long getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaFin(long fechaFin) {
        this.fechaFin = fechaFin;
    }

    public long getFechaFin() {
        return fechaFin;
    }

    //Metodos de presentacion
    
}
