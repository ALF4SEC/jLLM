package model;
import java.util.ArrayList;

/*
 * @author alfonso
 */
public interface IRepository {
    public ArrayList<Conversacion> importConversations();
    public boolean exportConversations(ArrayList<Conversacion> conversaciones);
}
