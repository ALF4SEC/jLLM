package model;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Paths;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
/*
 * @author alfonso
 */
public class Model {
    private ArrayList<Conversacion> conversaciones;
    private ILLM illm;
    private IRepository irep;
    private File ficheroEstadoSerializado;
    
    public Model(IRepository irep, ILLM illm) {
        this.irep = irep;
        this.illm=illm;
        ficheroEstadoSerializado = Paths.get(System.getProperty("user.home"), "Desktop", "CregoCalvoAlfonso", "model.bin").toFile();
        conversaciones = new ArrayList<>();
    }
    
    public boolean guardarConversacion(long fechaInicio, long fechaFin){ 
        return conversaciones.add(new Conversacion(illm.getIdentifier(), , fechaInicio, fechaFin));
    }
    
    public String getMessageAsString(String identificador, String contenido){
        
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
    
    public boolean importarConversaciones(){
        ArrayList<Conversacion> conversacionImportados = irep.importConversations();
        if (conversacionImportados!=null) {
            for (Conversacion conversacionImportado : conversacionImportados) {
                if (!conversaciones.contains(conversacionImportado)) {
                    conversaciones.add(conversacionImportado);
                }
            }
            return true;
        } else {
            return false;
        }
    }
    
    public boolean exportarConversaciones(){
        return irep.exportConversations(conversaciones);
    }
    
    public boolean cargarEstadoAplicación(){
        if (ficheroEstadoSerializado.exists() && ficheroEstadoSerializado.isFile()) {
            ObjectInputStream ois = null;
            try {
                ois = new ObjectInputStream(new FileInputStream(ficheroEstadoSerializado));
                this.conversaciones = (ArrayList<Conversacion>) ois.readObject();
            } catch (IOException | ClassNotFoundException ex) {
                // Dejamos el error para la depuración, por el canal err.
                System.err.println("Error durante la deserialización: " + ex.getMessage());
                return false;
            } finally {
                if (ois != null) {
                    try {
                        ois.close();
                    } catch (IOException ex) {
                        // Dejamos el error para la depuración, por el canal err.
                        System.err.println("Error durante la deserialización: " + ex.getMessage());
                        return false;
                    }
                }
            }
            return true;
        } else {
            return false;
        }

    }
    
    public boolean guardarEstadoAplicación() {
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(new FileOutputStream(ficheroEstadoSerializado));
            oos.writeObject(conversaciones);
            return true;
        } catch (IOException ex) {
            // Dejamos el error para la depuración, por el canal err.
            System.err.println("Error durante la serialización: " + ex.getMessage());
            return false;
        } finally {
            if (oos != null) {
                try {
                    oos.close();
                } catch (IOException ex) {
                    // Dejamos el error para la depuración, por el canal err.
                    System.err.println("Error al cerrar el flujo: " + ex.getMessage());
                    return false;
                }
            }
        }

    }
}
