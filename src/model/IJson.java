package model;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

/*
 * @author alfonso
 */
public class IJson implements IRepository{
    
    @Override
    public ArrayList<Conversacion> importConversations(){
        String rutaImport=System.getProperty("user.home")+File.separator+"Desktop"+File.separator+"CregoCalvoAlfonso"+"input.json";
        File fileImport=new File(rutaImport);
        Gson gson = new Gson();
        try{
            String json=new String(Files.readAllBytes(fileImport.toPath()), StandardCharsets.UTF_8);
            Type type=new TypeToken<ArrayList<Conversacion>>(){}.getType();
            ArrayList<Conversacion> conversaciones=gson.fromJson(json, type);
            return conversaciones;
        }catch (IOException e){
            return null;
        }
    }
    
    @Override
    public boolean exportConversations(ArrayList<Conversacion> conversaciones){
        String rutaExport=System.getProperty("user.home")+File.separator+"Desktop"+File.separator+"CregoCalvoAlfonso"+"output.json";
        File fileExport=new File(rutaExport);
        Gson gson = new Gson();
        String json = gson.toJson(conversaciones);
        try{
            Files.write(fileExport.toPath(), json.getBytes(StandardCharsets.UTF_8));
            return true;
        }catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
