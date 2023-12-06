package controller;
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
    
    
}
