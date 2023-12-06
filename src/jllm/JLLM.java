package jllm;
import model.ILLM;
import model.IRepository;
import view.ApplicationView;
import view.simpleConsole;
/*
 * @author alfonso
 */
public class JLLM {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ILLM llm;
        IRepository repository;
        ApplicationView view;
        
        if(args.length==3){
            repository=getRepositoryForOption(args[0]);
            llm=getLLMForOption(args[1]);
            view=getViewForOption(args[2]);
        }else{
            repository=new ;
            llm=new ;
            view=new simpleConsole();
        }
        
        private static ApplicationView getViewForOption(String argumento){
            switch (argumento){
                case "voz":
                    System.out.println("voz");
                    break;
                default:
                    return new simpleConsole();
            }
        }
        
        private static IRepository getRepositoryForOption(String argumento){
            switch (argumento){
                case "xml":
                    return new IXML();
                    break;
                default:
                    
                    break;
                
            }
        }
        
        private static ILLM getLLMForOption(String argumento){
            switch (argumento){
                case "fake":
                    return 
                    break;
                default:
                    
                    break;
            }
        }
    }
    
}
