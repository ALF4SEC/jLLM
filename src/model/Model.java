package model;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
/*
 * @author alfonso
 */
public class Model {
    private ArrayList<Conversacion> conversaciones;
    private ILLM illm;
    
    
    public boolean guardarConversacion(long fechaInicio, long fechaFin){ 
        return conversaciones.add(new Conversacion(illm.getIdentifier(), , fechaInicio, fechaFin));
    }
    
    public String getMessageAsString(String identificador, String contenido){
        Message message;
        Instant instant=Instant.now();
        long fechaEnvio=instant.getEpochSecond();
        String cadena=message.getMessageAsString();
        return cadena;
    }
    
    public boolean eliminarConversaciones(int numero){
        int numeroConversacionActual=1;
        for (Conversacion conversacion:conversaciones){
            Conversacion conversacionAborrar=conversacion;
            if (numero==numeroConversacionActual){
                conversacion.mensajes.clear();
                conversaciones.remove(conversacionAborrar);
                return true;
            }else{
                numeroConversacionActual++;
            }
        }
        return false;
    }
    
    public List<Conversacion> obtenerConversaciones(){
        List<Conversacion> listaCopia = new ArrayList<>(conversaciones.size());
        for (Conversacion conversacion : conversaciones){
            listaCopia.add(new Conversacion(conversacion));
        }
        return listaCopia;
    }
    
    public ArrayList<Message> obtenerMessages(int numero){
        ArrayList<Message> mensajes;
        int contador=1;    
        for (Conversacion conversacion : conversaciones){
            if (contador==numero){
                mensajes=conversacion.getMensajes();
                for (Message mensaje:conversacion.getMensajes()){
                    mensajes.add(mensaje);
                }
                return mensajes;
            }
            contador++;           
        }
        return null;
    }
}
