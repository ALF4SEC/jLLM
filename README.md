# jLLM

Aplicación de consola en Java que simula un chat con un modelo de lenguaje. Es una práctica de Programación III centrada en el patrón modelo-vista-controlador y en programar contra interfaces: el "modelo de lenguaje", el formato de guardado y la vista se eligen al arrancar sin tocar el resto del código.

## Funcionamiento

El menú principal tiene cuatro opciones:

1. Nueva conversación: se chatea con el modelo hasta escribir `/salir`. Al terminar, la conversación se guarda con su fecha de inicio.
2. Menú CRUD: listar las conversaciones guardadas o borrar una.
3. Menú de exportación: importar o exportar todas las conversaciones.
4. Salir.

Las conversaciones se guardan al cerrar el programa y se recuperan al abrirlo, serializadas en `model.bin`.

## Piezas intercambiables

| Interfaz | Implementaciones | Argumento |
|---|---|---|
| `ILLM` (modelo de lenguaje) | `RandomCSVLLM`: responde con una frase al azar de un CSV. `FakeLLM`: contesta según palabras clave (saludos, preguntas, agradecimientos, despedidas) y, si no encuentra ninguna, con una frase de broma. | `fake` elige `FakeLLM`; cualquier otro valor, `RandomCSVLLM`. |
| `IRepository` (importar y exportar) | `IJson` (con Gson) e `IXML` (con Jackson XML). | `xml` elige `IXML`; cualquier otro valor, `IJson`. |
| `ApplicationView` (vista) | `simpleConsole`. | `consola`. |

```bash
java -jar jLLM.jar <repositorio> <llm> <vista>
java -jar jLLM.jar xml fake consola
```

Si no se pasan exactamente tres argumentos se usan JSON, `RandomCSVLLM` y la consola.

## Archivos de datos

Las rutas están escritas en el código y apuntan a `~/Desktop/CregoCalvoAlfonso/`:

| Archivo | Uso |
|---|---|
| `input.csv` | Frases de `RandomCSVLLM`. Cada línea tiene el formato `tipo,longitud,frase`. |
| `input.json` / `input.xml` | Conversaciones que se importan. |
| `output.json` / `output.xml` | Conversaciones que se exportan. |
| `jLLM/model.bin` | Estado de la aplicación guardado entre ejecuciones. |

El repositorio incluye un `model.bin` de ejemplo.

## Estructura

```
src/
  jllm/JLLM.java             punto de entrada; elige las implementaciones según los argumentos
  controller/Controller.java
  model/                     Model, Conversacion, Message, Frase, las interfaces y sus implementaciones
  view/                      ApplicationView y simpleConsole
```

## Compilación

Es un proyecto de NetBeans con Ant (`build.xml`, `nbproject/`). Usa estas librerías externas: Gson 2.10.1, Jackson 2.15.3 (core, databind, annotations y dataformat-xml), stax2-api, SLF4J, Log4j, ollama4j, jadapter-for-native-tts y `biblioteca.jar`, la librería de la asignatura que tiene la clase `Esdia` para leer datos por teclado.

En `nbproject/project.properties` los JAR tienen rutas absolutas del ordenador donde se desarrolló. Para compilar en otra máquina hay que cambiarlas y después ejecutar:

```bash
ant jar
```
