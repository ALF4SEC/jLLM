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
    
    public boolean guardarConversacion(long fechaInicio, long fechaFin){
        return model.guardarConversacion(fechaInicio, fechaFin);
    }
    
    public boolean eliminarConversaciones(int numero){
        return model.eliminarConversaciones(numero);
    }
    
    public List<Conversacion> obtenerConversaciones(){
        return model.obtenerConversaciones();
    }
    
    public ArrayList<Message> obtenerMessages(int numero){
        return model.obtenerMessages(numero);
    }
}
