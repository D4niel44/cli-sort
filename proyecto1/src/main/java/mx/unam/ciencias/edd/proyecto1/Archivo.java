package mx.unam.ciencias.edd.proyecto1;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.Writer;
import java.text.Collator;
import mx.unam.ciencias.edd.Lista;

/**
 * 
 */
public class Archivo {

    private Lista<String> lineas;

    /**
     * Constructor por omisión
     * 
     */
    public Archivo() {
        lineas = new Lista<>();
    }

    /**
     * Lee las lineas de un archivo y las agrega al objeto.
     * 
     * @param bufer Entrada de la cual leer el archivo
     * @throws IOException Si ocurre un error al leer el archivo.
     */
    public void cargarArchivo(BufferedReader bufer) throws IOException {
        String linea = bufer.readLine();
        while (linea != null) {
            lineas.agregaFinal(linea);
            linea = bufer.readLine();
        }
    }

    /**
     * Dice si el archivo es vacío
     * 
     * @return Si no se le han añadido lineas al archivo regresa true en otro caso
     *         false
     */
    public boolean esVacio() {
        return lineas.esVacia();
    }

    /**
     * Ordena el archivo lexicográficamente usando el orden por defecto.
     */
    public void ordena() {
        lineas = lineas.mergeSort((a, b) -> compara(a, b));
    }

    /**
     * Ordena el archivo lexicográficamente usando el inverso del orden por defecto
     */
    public void ordenaReversa() {
        lineas = lineas.mergeSort((a, b) -> -compara(a, b));
    }

    /**
     * Devuelve una representación en cadena del archivo.
     */
    @Override
    public String toString() {
        String s = "";
        for (String linea : lineas)
            s += linea + "\n";
        return s;
    }

    /**
     * Imprime el archivo en la salida estandar.
     */
    public void imprimirArchivo() {
        for (String linea : lineas)
            System.out.println(linea);
    }

    /**
     * Escribe el contenido del archivo en el Writer pasado como parametro.
     * No cierra el writer.
     * @param f Writer donde se va a escribir el archivo
     * @throws IOException Si ocurre un error I/O
     */
    public void escribirFichero(Writer f) throws IOException {
        for (String linea : lineas)
            f.write(linea + "\n");
    }

    /**
     * Compara dos cadenas Alfabéticamente de acuerdo con las reglas provistas por
     * el locale
     * 
     * @param a primera cadena a comparar
     * @param b segunda cadena a comparar
     * @return un número menor que cero si a<b, 0 si a=b y un numero mayor que cero
     *         si a>b
     */
    private int compara(String a, String b) {
        // Crea el objeto que va a utilizarse para comparar las cadenas
        Collator comparador = Collator.getInstance();
        // Hace que el comparador obvie Mayúsculas y acentos a la hora de comparar.
        comparador.setStrength(Collator.PRIMARY);
        // un caracter que no sea una letra o un dígito.
        String regex = "[^\\p{L}\\p{Nd}+]";
        return comparador.compare(a.replaceAll(regex, ""), b.replaceAll(regex, ""));
    }
}