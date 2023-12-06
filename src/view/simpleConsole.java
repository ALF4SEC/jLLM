package view;

import com.coti.tools.Esdia;

/*
 * @author alfonso
 */
public class simpleConsole extends ApplicationView{
    
    @Override
    public void showApplicationStart(String msgBienvenida){
        System.out.println(msgBienvenida);
    }
    
    @Override
    public void showApplicationEnd(String msgDespedida){
        System.out.println(msgDespedida);
    }
    
    @Override
    public void showMainMenu(){
        boolean salir=false;
        do{
           System.out.println("1.- Nueva conversacion"); 
           System.out.println("2.- Menu CRUD");
           System.out.println("3.- Menu exportacion");
           System.out.println("4.- Salir");
           int opcion=Esdia.readInt("Dame una opcion: ");
           switch(opcion){
                case 1:
                   nuevaConversacion();
                   break;
                case 2:
                    menuCRUD();
                    break;
                /*case 3:
                    menuExportacion();
                    break;*/
                case 4:
                    salir=true;
                    break;
                default:
                    System.out.println("Disculpe, pero la opcion elegida no es correcta. Elija otra opcion.");
                    break;
           }
        } while(salir==false);
    }
    
    private void menuCRUD(){
        boolean salir=false;
        do{
            System.out.println("1.- Conservar conversaciones"); 
            System.out.println("2.- Eliminar conversaciones");
            System.out.println("3.- Salir");
            int opcion=Esdia.readInt("Dame una opcion: ");
            switch(opcion){
                case 1:
                    listarConversaciones();
                    break;
                case 2:
                    eliminarConversaciones();
                    break;
                case 3:
                    salir=true;
                    break;
                default:
                  System.out.println("Disculpe, pero la opcion elegida no es correcta. Elija otra opcion.");
                  break;  
            }
        } while(salir==false);
    }
    
    private static void nuevaConversacion(){
        
    }
    
    private static void eliminarConversaciones(){
        
    }
    
    private static void listarConversaciones(){
        
    }
    
    /*private void menuExportacion(){
        boolean salir=false;
        do{
            System.out.println("1.- Conservar conversaciones"); 
            System.out.println("2.- Eliminar conversaciones");
            System.out.println("3.- Salir");
            int opcion=Esdia.readInt("Dame una opcion: ");
            switch(opcion){
                case 1:
                    importarConversaciones();
                    break;
                case 2:
                    exportarConversaciones();
                    break;
                case 3:
                    salir=true;
                    break;
                default:
                    System.out.println("Disculpe, pero la opcion elegida no es correcta. Elija otra opcion.");
                    break;         
            }
        } while(salir==false);
    }*/
    
    /*private void importarConversaciones(){
        if (c.importarConversaciones()){
            System.out.println("Conversacion importada con exito");
        } else{
            System.out.println("Conversacion no hasido importada, se produjo un error");
        }
    }*/
    
    /*private void exportarConversaciones(){
        if (c.exportarConversaciones()){
            System.out.println("Conversacion exportada con exito");
        } else{
            System.out.println("Conversacion no hasido exportada, se produjo un error");
        }
    }*/
            
}
