package view;
import com.coti.tools.Esdia;
import java.time.Instant;
import java.util.ArrayList;
import model.Conversacion;
import model.Message;
/*
 * @author alfonso
 */
public class simpleConsole extends ApplicationView{
    
    @Override
    public void showApplicationStart(String msgBienvenida){
        tituloApp();
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
                case 3:
                    menuExportacion();
                    break;
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
            System.out.println("1.- Listar conversaciones"); 
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
    
    private void nuevaConversacion(){
        boolean salir=false;
        String cadenaSalida="/salir";
        String usuario="Yo";
        
        Instant instant=Instant.now();
        long fechaInicio=instant.getEpochSecond();
        
        do{
            String mensajeUSR=Esdia.readString("Message jLLM... (Escribe /salir para terminar la conversacion)");
            String cadena=mensajeUSR;
            
            if (!cadena.equals(cadenaSalida)){
                String mostrarMensaje=c.mostrarMensajes(usuario, mensajeUSR);
                System.out.println(mostrarMensaje);
                
                String mensajeLLM=c.speakLLM(mensajeUSR);
                System.out.println(mensajeLLM);
            }else{
                salir=true;
            }
        }while(salir==false);
        
        if (c.guardarConversacion(fechaInicio)){
            System.out.println("Conversacion guardada correctamente");
        }else{
            System.out.println("Conversacion no ha podido ser guardada");
        }
        
    }
    
    private void eliminarConversaciones(){
        int numConversacion=0;
        int numMensaje;
        ArrayList<Conversacion> conversaciones=new ArrayList<>();
        conversaciones=c.obtenerConversaciones();
        System.out.printf("%s |   %5s    | %s | %s \n", "Nº","Fecha Inicio", "Numero de mensajes", "Primeros 20 caracteres");
        for (Conversacion conversacion : conversaciones){
            numMensaje=0;
            for (Message message: conversacion.getMensajes()){
                numMensaje++;
            }
            numConversacion++;
            String primerMensaje=conversacion.getMensajes().get(0).getContenido();
            System.out.printf("%-3d|%s|%20d|%-20s\n", numConversacion,conversacion.getFechaInicio(), numMensaje, primerMensaje);            
        }
        
        if(numConversacion!=0){
            int numeroConversacion=Esdia.readInt("Dame el numero de la conversacion a eliminar: ");
            if(c.eliminarConversaciones(numeroConversacion)){
                System.out.println("Conversacion eliminada con exito");
            }else{
                System.out.println("No se pudo eliminar el conversacion con ese numero");
            }
        }else{
            System.out.println("Si quiere eliminar una conversacion debe crearla primero");
        }
    }
    
    private void listarConversaciones(){
        int numConversacion=0;
        
        ArrayList<Conversacion> conversaciones=c.obtenerConversaciones();
        System.out.printf("%s |   %5s    | %s | %s \n", "Nº","Fecha Inicio", "Numero de mensajes", "Primeros 20 caracteres");
        for (Conversacion conversacion : conversaciones){
            String fechaInicio=conversacion.getFechaInicio();
            int numMensaje=0;
            for (Message message: conversacion.getMensajes()){
                numMensaje++;
            }
            numConversacion++;
            String primerMensaje=conversacion.getMensajes().get(0).getContenido();
            System.out.printf("%-3d|%s|%20d|%-20s\n", numConversacion, fechaInicio, numMensaje, primerMensaje);
        }
        
        if (numConversacion!=0){
            int numero=Esdia.readInt("Dame el numero de la conversacion que quieres ver: ");
            ArrayList<Message> message=new ArrayList<>();

            for (Message mensajer: conversaciones.get(numero-1).getMensajes()){
                message.add(new Message(mensajer.getIndentificador(), mensajer.getContenido(), mensajer.getFechaEnvio()));
            }
            boolean salir=false;
            do{
                for (Message mensaje: message){
                    System.out.printf("%s[%s]%s\n", mensaje.getIndentificador(), mensaje.getFechaEnvio(), mensaje.getContenido());
                }
                boolean opcion=Esdia.yesOrNo("Quieres dejar de ver los mensajes de la conversacion: ");
                if(opcion){
                    salir=true;
                }
            } while(salir==false);
        }else{
            System.out.println("No se puede mostrar ninguna conversacion ya que no hay");
        }
        
    }
    
    private void menuExportacion(){
        boolean salir=false;
        do{
            System.out.println("1.- Importar conversaciones"); 
            System.out.println("2.- Exportar conversaciones");
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
    }
    
    private void importarConversaciones(){
        if (c.importarConversaciones()){
            System.out.println("Conversacion importada con exito");
        } else{
            System.out.println("Conversacion no ha sido importada, se produjo un error");
        }
    }
    
    private void exportarConversaciones(){
        if (c.exportarConversaciones()){
            System.out.println("Conversacion exportada con exito");
        } else{
            System.out.println("Conversacion no ha sido exportada, se produjo un error");
        }
    }
    
    private static void tituloApp(){
        System.out.println("   ,,                                         ");
        System.out.println("   db `7MMF'      `7MMF'      `7MMM.     ,MMF'");
        System.out.println("        MM          MM          MMMb    dPMM  ");
        System.out.println(" `7MM   MM          MM          M YM   ,M MM  ");
        System.out.println("   MM   MM          MM          M  Mb  M' MM  ");
        System.out.println("   MM   MM      ,   MM      ,   M  YM.P'  MM  ");
        System.out.println("   MM   MM     ,M   MM     ,M   M  `YM'   MM  ");
        System.out.println("   MM .JMMmmmmMMM .JMMmmmmMMM .JML. `'  .JMML.");
        System.out.println("QO MP ");
        System.out.println("`bmP ");
    }
    
}
