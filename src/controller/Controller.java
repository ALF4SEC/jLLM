package controller;
import java.util.ArrayList;
import model.Model;
import view.ApplicationView;
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
    
    
    
    public boolean eliminarConversaciones(int numero){
        return m.eliminarConversaciones(numero);
    }
    
    public ArrayList<Conversacion> obtenerConversaciones(){
        
    }
}
