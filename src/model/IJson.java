package model;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
/*
 * @author alfonso
 */
public class IJson implements IRepository{
    Gson gson = new Gson();
    
    @Override
    public ArrayList<Conversacion> importConversations(){
        Path rutaImport=Paths.get(System.getProperty("user.home"), "Desktop", "CregoCalvoAlfonso", "input.json");
        File fileImport=rutaImport.toFile();
        
        if(!fileImport.exists()&&!fileImport.isFile()){
            return null;
        }
        
        try{
            String json=new String(Files.readAllBytes(rutaImport), StandardCharsets.UTF_8);
            Type type=new TypeToken<ArrayList<Conversacion>>(){}.getType();
            ArrayList<Conversacion> conversaciones=gson.fromJson(json, type);
            return conversaciones;
        }catch (IOException e){
            return null;
        }
    }
    
    @Override
    public boolean exportConversations(ArrayList<Conversacion> conversaciones){
        Path rutaImport=Paths.get(System.getProperty("user.home"), "Desktop", "CregoCalvoAlfonso", "output.json");
        File fileImport=rutaImport.toFile();
        String json = gson.toJson(conversaciones);
        
        try {
            Files.write(fileImport.toPath(), json.getBytes(StandardCharsets.UTF_8));
            return true;
        } catch (IOException e) {
            return false;
        }
    }
}
