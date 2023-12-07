package model;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/*
 * @author alfonso
 */
public class RandomCSVLLM implements ILLM{
    String identificador="RandomCSVLLM";
    Path ruta = Paths.get(System.getProperty("user.home"), "Desktop", "CregoCalvoAlfonso","input.csv");
    String delimitador = ",";
    
    @Override
    public String speak(String input){
        boolean salir=false;
        String cadena=null;
        ArrayList<Frase> frases=importarFrase();
        do{
            int numero=(int)(Math.random()*40+1);
            for (Frase frase: frases){
                if(frase.getLongitud()==numero){
                    cadena=frase.getFrase();
                    salir=true;
                }
            }
        }while(salir==false);
        return cadena;
    }
    
    @Override
    public String getIdentifier(){
        return String.format("%s", identificador);
    }
    
    public ArrayList<Frase> importarFrase(){
       ArrayList<Frase> frases = new ArrayList<>();
        try {
            List<String> lineas = Files.readAllLines(ruta);
            for (String linea : lineas) {
                Frase p = Frase.getFraseFromDelimitedString(linea, delimitador);
                if (p != null) {
                    frases.add(p);
                }
            }
            return frases;
        } catch (IOException e) {
            // En otros ejemplos propagaremos una exception
            return null;
        }
    }
   
    //Setters y getters
    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }
   
}
