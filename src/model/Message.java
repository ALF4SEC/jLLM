package model;
/*
 * @author alfonso
 */
public class Message {
    private String indentificador, contenido;
    private long fechaEnvio;
    
    //Constructor
    public Message(String identificador, String contenido, long fechaEnvio){
        this.indentificador=identificador;
        this.contenido=contenido;
        this.fechaEnvio=fechaEnvio;
    }
    
    //Setters y getters
    public void setIndentificador(String indentificador) {
        this.indentificador = indentificador;
    }
    
    public String getIndentificador() {
        return indentificador;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public String getContenido() {
        return contenido;
    }

    public void setFechaEnvio(long fechaEnvio) {
        this.fechaEnvio = fechaEnvio;
    }

    public long getFechaEnvio() {
        return fechaEnvio;
    }

    //Metodos de presentacion
    public String getMessageAsString(){
        return String.format("%s[%d]%s", this.indentificador, this.fechaEnvio, this.contenido);
    }
    
    
}
