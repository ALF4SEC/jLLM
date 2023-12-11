package model;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;

/*
 * @author alfonso
 */
public class IXML implements IRepository{
    
    @Override
    public ArrayList<Conversacion> importConversations(){
        String rutaImport=System.getProperty("user.home")+File.separator+"Desktop"+File.separator+"CregoCalvoAlfonso"+"input.xml";
        File fileImport=new File(rutaImport);
        
        ObjectMapper xmlMapper = new XmlMapper();
        try{
            String xml=new String(Files.readAllBytes(fileImport.toPath()), StandardCharsets.UTF_8);
            Type type=new TypeToken<ArrayList<Conversacion>>(){}.getType();
            ArrayList<Conversacion> conversaciones=xmlMapper.readValue(xml, type);
            return conversaciones;
        }catch (IOException e){
            return null;
        }
    }
    
    @Override
    public boolean exportConversations(ArrayList<Conversacion> conversaciones){
        String rutaExport=System.getProperty("user.home")+File.separator+"Desktop"+File.separator+"CregoCalvoAlfonso"+"output.json";
        File fileExport=new File(rutaExport);
        
        ObjectMapper xmlMapper = new XmlMapper();
        try {
            String xml = xmlMapper.writeValueAsString(conversaciones);
            Files.write(fileExport.toPath(), xml.getBytes(StandardCharsets.UTF_8));
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
