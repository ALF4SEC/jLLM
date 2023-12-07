package model;
/*
 * @author alfonso
 */
public class FakeLLM implements ILLM{
    String identificador="Fake";
    
    @Override
    public String speak(String input){
        
    }
    
    @Override
    public String getIdentifier(){
        return String.format("%s", identificador);
    }
}
