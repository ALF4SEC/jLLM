package model;

import java.io.Serializable;

/*
 * @author alfonso
 */
public class Message implements Serializable{
    private static final long serialVersionUID = 1L;
    private String indentificador, contenido;
    private String fechaEnvio;
    
    //Constructor
    public Message(String identificador, String contenido, String fechaEnvio){
        this.indentificador=identificador;
        this.contenido=contenido;
        this.fechaEnvio=fechaEnvio;
    }
    
    public Message(){
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

    public void setFechaEnvio(String fechaEnvio) {
        this.fechaEnvio = fechaEnvio;
    }

    public String getFechaEnvio() {
        return fechaEnvio;
    }
}
