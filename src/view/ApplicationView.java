package view;
import controller.Controller;
/*
 * @author alfonso
 */
public abstract class ApplicationView {
    protected Controller c;
    
    public abstract void showApplicationStart(String msgBienvenida);
    public abstract void showMainMenu();
    public abstract void showApplicationEnd(String msgDespedida);
    
    public void setController(Controller c) {
        this.c = c;
    }
}
