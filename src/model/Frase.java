package model;
/*
 * @author alfonso
 */
public class Frase {
    String tipoFrase, frase;
    int longitud;
    
    //Constructor

    public Frase(String tipoFrase, int longitud, String frase) {
        this.tipoFrase = tipoFrase;
        this.frase = frase;
        this.longitud = longitud;
    }
    
    //Getters y setters
    public void setTipoFrase(String tipoFrase) {
        this.tipoFrase = tipoFrase;
    }
    
    public String getTipoFrase() {
        return tipoFrase;
    }

    public void setFrase(String frase) {
        this.frase = frase;
    }

    public String getFrase() {
        return frase;
    }

    public void setLongitud(int longitud) {
        this.longitud = longitud;
    }

    public int getLongitud() {
        return longitud;
    }
    
     public static Frase getFraseFromDelimitedString(String cadena, String delimitador) {
        String[] chunks=cadena.split(delimitador);

        if (chunks.length!=3) {
            return null;
        }

        try {
            String tipo=chunks[0];
            int longitud=Integer.parseInt(chunks[1]);
            String frase=chunks[2];
            Frase f=new Frase(tipo, longitud, frase);
            return f;
        } catch (Exception e) {
            return null;
        }
    }
}
