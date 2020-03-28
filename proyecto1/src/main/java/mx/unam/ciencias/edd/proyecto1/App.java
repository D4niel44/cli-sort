package mx.unam.ciencias.edd.proyecto1;

/**
 * Contiene el método main y se encarga de imprimir en pantalla el resultado y
 * los errores.
 */
public class App {

    /**
     * Método principal. Recibe los argumentos introducidos por el usuario crea. 
     * Ejecuta el programa para ordenar los archivos e imprime el resultado en pantalla.
     */
    public static void main(String[] args) {
        try {
            Sort app = new Sort(args);
            app.ejecutar();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}