package model;
import java.util.ArrayList;
/*
 * @author alfonso
 */
public class Conversacion {
    String identificador;
    int numConversacion;
    ArrayList<Message> mensajes;
    long fechaInicio, fechaFin;
    
    public Conversacion(int numConversacion, String identificador, ArrayList<Message> mensajes, long fechaInicio, long fechaFin){
        this.numConversacion=numConversacion;
        this.identificador=identificador;
    }
}
