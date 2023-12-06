package model;
/*
 * @author alfonso
 */
public interface IRepository {
    public List<Conversacion> importConversations();
    public void exportConversations(List<Conversacion> conversaciones);
}
