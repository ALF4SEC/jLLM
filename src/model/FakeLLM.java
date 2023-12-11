package model;
/*
 * @author alfonso
 */
public class FakeLLM implements ILLM{
    String identificador="Fake";
    
    @Override
    public String speak(String input){
        String output=null;
        int numero=(int)(Math.random()*10+1);
        if (input.contains("Hola")||input.contains("Buenas")||input.contains("Buenos")){
            output=getSaludo(numero);
        }
        
        if (input.contains("Gracias")||input.contains("Muchas gracias")){
            output=getGracias(numero);
        }
        
        if (input.contains("¿")||input.contains("?")){
            output=getRespuesta(numero);
        }
        
        if (input.contains("Adios")||input.contains("Hasta luego")||input.contains("Hasta la proxima")){
            output=getDespedida(numero);
        }
        
        if (output==null){
            output="Disculpe no entendi, reescriba la frase";
        }
        return output;
    }
    
    @Override
    public String getIdentifier(){
        return String.format("%s", identificador);
    }
    
    public String getSaludo(int numero){
        switch(numero){
            case 1:
                return String.format("Hola");
            case 2:
                return String.format("Hola, ¿como estas?");
            case 3:
                return String.format("Buenas, bienvenido");
            case 4:
                return String.format("Buenos días, ¡espero que tengas un gran día!");
            case 5: 
                return String.format("¡Qué alegría verte!");
            case 6:
                return String.format("Hola, mucho gusto en conocerte");
            case 7:
                return String.format("Saludos cordiales a todos aquí");
            case 8:
                return String.format("Saludos cordiales");
            case 9:
                return String.format("Buenas");
            case 10:
                return String.format("Buenas, ¿como estas?");
            default:
                return String.format("Hola");
        }
    }
    
    public String getDespedida(int numero){
        switch(numero){
            case 1:
                return String.format("Adios");
            case 2:
                return String.format("Adios, hasta la proxima");
            case 3:
                return String.format("Hasta luego");
            case 4:
                return String.format("Hasta luego, cuídate mucho");
            case 5: 
                return String.format("Adiós, nos vemos pronto");
            case 6:
                return String.format("Que tengas un buen día, nos vemos");
            case 7:
                return String.format("Hasta la próxima, ha sido un placer");
            case 8:
                return String.format("Me voy, pero nos vemos pronto");
            case 9:
                return String.format("Hasta la próxima");
            case 10:
                return String.format("Nos vemos pronto");
            default:
                return String.format("Hasta la próxima");
        }
    }

    public String getRespuesta(int numero){
        switch(numero){
            case 1:
                return String.format("Me llamo FakeLLM");
            case 2:
                return String.format("Son las tres de la tarde");
            case 3:
                return String.format("El baño está a la derecha");
            case 4:
                return String.format("Claro, dime en qué puedo ayudarte");
            case 5: 
                return String.format("Mi color favorito es el azul");
            case 6:
                return String.format("Estoy completamente de acuerdo");
            case 7:
                return String.format("Si, la hipotenusa");
            case 8:
                return String.format("No, se si lo que me preguntas esta entre mis conocimientos");
            case 9:
                return String.format("Estoy completamente de acuerdo");
            case 10:
                return String.format("¡No puedo creerlo!");
            default:
                return String.format("¡Eso es asombroso!");
        }
    }
    
    public String getGracias(int numero){
        switch(numero){
            case 1:
                return String.format("De nada");
            case 2:
                return String.format("De nada, encantado de ayudarte");
            case 3:
                return String.format("Gracias a ti");
            case 4:
                return String.format("Muchas veces");
            case 5: 
                return String.format("Para eso fui creada");
            case 6:
                return String.format("Si necesitas algo mas puedes decirme");
            case 7:
                return String.format("De nada, gracias a ti");
            case 8:
                return String.format("Gracias a ti, por confiar en mi");
            case 9:
                return String.format("Nada, es un placer de poder ayudar");
            case 10:
                return String.format("Gracias, es un placer de poder ayudar");
            default:
                return String.format("Gracias, si necesitas algo mas puedes decirme");
        }
    }
}
