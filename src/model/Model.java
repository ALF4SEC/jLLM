package model;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Calendar;
/*
 * @author alfonso
 */
public class Model {
    private ArrayList<Conversacion> conversaciones=new ArrayList<>();
    private ArrayList<Message> mensajes=new ArrayList<>();
    private ILLM illm;
    private IRepository irep;
    File ficheroEstadoSerializado;
    
    public Model(IRepository irep, ILLM illm) {
        this.irep = irep;
        this.illm=illm;
        ficheroEstadoSerializado = Paths.get(System.getProperty("user.home"), "Desktop", "CregoCalvoAlfonso", "jLLM", "model.bin").toFile();
        conversaciones=new ArrayList<>();
        mensajes=new ArrayList<>();
    }
    
    public boolean cargarEstadoAplicación(){
        if (ficheroEstadoSerializado.exists() && ficheroEstadoSerializado.isFile()){
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
    
    public boolean guardarEstadoAplicación(){
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
            if (oos!=null) {
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
    
    public String speakLLM(String mensajeUSR){
        Instant instant=Instant.now();
        long fechaInicio=instant.getEpochSecond();
        DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(fechaInicio);
        String fechaEnvio = formatter.format(calendar.getTime());      
        String respuesta=illm.speak(mensajeUSR);
        mensajes.add(new Message (illm.getIdentifier(), respuesta, fechaEnvio));
        return String.format("%s[%s]%s", illm.getIdentifier(), fechaEnvio, respuesta);
    }
    
    public String mostrarMensajes(String usuario, String mensajeUSR){
        Instant instant=Instant.now();
        long fechaInicio=instant.getEpochSecond();
        DateFormat formatter=new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        Calendar calendar=Calendar.getInstance();
        calendar.setTimeInMillis(fechaInicio);
        String fechaEnvio=formatter.format(calendar.getTime());
        mensajes.add(new Message (usuario, mensajeUSR, fechaEnvio));
        return String.format("%s[%s]%s", usuario, fechaEnvio, mensajeUSR);
    }
    
    public boolean guardarConversacion(long fechaIni){
        String identificador=illm.getIdentifier();
        DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(fechaIni);
        String fechaInicio = formatter.format(calendar.getTime());
        Conversacion conversacion=new Conversacion(identificador, mensajes, fechaInicio);
        if (conversaciones.contains(conversacion)){
            return false;
        }else{
            conversaciones.add(conversacion);
            mensajes=new ArrayList<>();
            return true;
        }
    }
    
    public boolean eliminarConversaciones(int numero){
        int numeroConversacionActual=1;
        for (Conversacion conversacion:conversaciones){
            Conversacion conversacionAborrar=conversacion;
            if (numero==numeroConversacionActual){
                conversaciones.remove(conversacionAborrar);
                return true;
            }else{
                numeroConversacionActual++;
            }
        }
        return false;
    }
    
    public ArrayList<Conversacion> obtenerConversaciones(){
        return conversaciones;
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
    
    
}
