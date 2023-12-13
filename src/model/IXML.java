package model;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
/*
 * @author alfonso
 */
public class IXML implements IRepository{
    XmlMapper xmlMapper=new XmlMapper();
    
    @Override
    public ArrayList<Conversacion> importConversations(){
        Path rutaImport=Paths.get(System.getProperty("user.home"), "Desktop", "CregoCalvoAlfonso", "input.xml");
        File file=rutaImport.toFile();
        
        if (!file.exists()&&!file.isFile()){
            return null;
        }
        
        try{
           String xml=new String(Files.readAllBytes(rutaImport), StandardCharsets.UTF_8);
           ArrayList<Conversacion> conversaciones = xmlMapper.readValue(xml, xmlMapper.getTypeFactory().constructCollectionType(ArrayList.class, Conversacion.class));
           return conversaciones;
        }catch (IOException e) {
            return null;
        }
    }
    
    @Override
    public boolean exportConversations(ArrayList<Conversacion> conversaciones){
        Path rutaImport=Paths.get(System.getProperty("user.home"), "Desktop", "CregoCalvoAlfonso", "output.xml");
        
        try {
            String xml=xmlMapper.writeValueAsString(conversaciones);
            Files.write(rutaImport, xml.getBytes(StandardCharsets.UTF_8));
            return true;    
        } catch (IOException e) {
            return false;
        }
    }
}
