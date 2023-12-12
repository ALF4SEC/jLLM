package model;
import java.io.Serializable;
import java.util.ArrayList;
/*
 * @author alfonso
 */
public class Conversacion implements Serializable{
    String identificador;
    ArrayList<Message> mensajes;
    String fechaInicio;
    
    //Constructor
    public Conversacion(String identificador, ArrayList<Message> mensajes, String fechaInicio){
        this.identificador=identificador;
        this.mensajes=mensajes;
        this.fechaInicio=fechaInicio;
    }
    
    public Conversacion(Conversacion otraConversacion){
        this.identificador=otraConversacion.identificador;
        this.mensajes=otraConversacion.mensajes;
        this.fechaInicio=otraConversacion.fechaInicio;
    }
    
    public Conversacion(){    
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

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }  
}
