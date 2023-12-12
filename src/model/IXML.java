package model;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
 * @author alfonso
 */
public class IXML implements IRepository{
    
    @JacksonXmlRootElement(localName = "Conversacion")
    public class ConversacionXML {
        private String identificador;
        private long fechaInicio, fechaFin;
    }
    
    @JacksonXmlElementWrapper(localName = "Mensajes")
    private List<Message> mensajes;
    
    
    @Override
    public ArrayList<Conversacion> importConversations(){
        String rutaImport=System.getProperty("user.home")+File.separator+"Desktop"+File.separator+"CregoCalvoAlfonso"+"input.xml";
        File fileImport=new File(rutaImport);
        
        ObjectMapper xmlMapper = new XmlMapper();
        try (Scanner scannerRef = new Scanner(fileImport)) {
            StringBuilder xml = new StringBuilder();
            while (scannerRef.hasNext()) {
                xml.append(scannerRef.nextLine());
            }
            return xmlMapper.readValue(xml.toString(), Conversacion.class);
        } catch (IOException e) {
            e.printStackTrace();
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
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
