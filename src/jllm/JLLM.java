package jllm;
import controller.Controller;
import model.IJson;
import model.IXML;
import model.ILLM;
import model.IRepository;
import model.RandomCSVLLM;
import model.FakeLLM;
import model.Model;
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
            repository=new IJson();
            llm=new RandomCSVLLM();
            view=new simpleConsole();
        }
        
        Model model=new Model(repository, llm);
        Controller c=new Controller(model, view);
        
        c.initApplication();
    }
    
    private static ApplicationView getViewForOption(String argumento){
        switch (argumento){
            case "voz":
                return new simpleConsole();
            default:
                return new simpleConsole();
        }
    }
        
    private static IRepository getRepositoryForOption(String argumento){
        switch (argumento){
            case "xml":
                return new IXML();
            default:
                return new IJson();
            }
        }
        
    private static ILLM getLLMForOption(String argumento){
        switch (argumento){
            case "fake":
                return new FakeLLM();
            default:
                return new RandomCSVLLM();
        }
    }
    
}
