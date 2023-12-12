package controller;
import java.util.ArrayList;
import java.util.List;
import model.Model;
import view.ApplicationView;
import model.Conversacion;
import model.Message;
/*
 * @author alfonso
 */
public class Controller {
    ApplicationView view;
    Model model;
    
    public Controller(Model model, ApplicationView view){
        this.model=model;
        this.view=view;
        view.setController(this);
    }
    
    
    public String speakLLM(String mensajeUSR){
        return model.speakLLM(mensajeUSR);
    }
    
    public String mostrarMensajes(String usuario, String mensajeUSR){
        return model.mostrarMensajes(usuario, mensajeUSR);
    } 
    
    public boolean guardarConversacion(long fechaInicio){
        return model.guardarConversacion(fechaInicio);
    }
    
    public boolean eliminarConversaciones(int numero){
        return model.eliminarConversaciones(numero);
    }
    
    public ArrayList<Conversacion> obtenerConversaciones(){
        return model.obtenerConversaciones();
    }
    
    public ArrayList<Message> obtenerMessages(int numero){
        return model.obtenerMessages(numero);
    }
    
    public boolean importarConversaciones(){
        return model.importarConversaciones();
    }
    
    public boolean exportarConversaciones(){
        return model.exportarConversaciones();
    }
    
     public void initApplication(){
        
        // Carga inicial programa
        if(model.cargarEstadoAplicación()){
            view.showApplicationStart("Cargado estado anterior con exito");
        }else{
            view.showApplicationStart("No se encontró fichero para carga del programa");
        }
        
        // Menú principal
        view.showMainMenu();
        
        
        // Guardado final del programa
        if(model.guardarEstadoAplicación()){
            view.showApplicationEnd("Guardado el estado de la aplicación.\nSaliendo...");
        }else{
            view.showApplicationEnd("No se pudo guardar el estado de la aplicación.\nSaliendo...");
        }
        
    }

    
}
