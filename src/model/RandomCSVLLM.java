package model;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

/*
 * @author alfonso
 */
public class RandomCSVLLM implements ILLM{
    String identificador="RandomCSVLLM";
    Random random = new Random();
    
    @Override
    public String speak(String input){
        int numeroMensajesImportados=0;
        ArrayList<Frase> frases=importarFrase();
        ArrayList<String> mensajesImportados=new ArrayList<>();
        
        for(Frase frase:frases){
            mensajesImportados.add(frase.getFrase());
            numeroMensajesImportados++;
        }
        
        int numeroAleatorio = random.nextInt(numeroMensajesImportados);
        
        String mensajeAEnviar=mensajesImportados.get(numeroAleatorio);
        return mensajeAEnviar;    
    }
    
    @Override
    public String getIdentifier(){
        return String.format("%s", identificador);
    }
    
    public ArrayList<Frase> importarFrase(){
        Path ruta = Paths.get(System.getProperty("user.home"), "Desktop", "CregoCalvoAlfonso","input.csv");
        String delimitador = ",";
        ArrayList<Frase> frases = new ArrayList<>();
        try {
            List<String> lineas = Files.readAllLines(ruta, StandardCharsets.UTF_8);
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
