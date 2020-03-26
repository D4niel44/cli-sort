package mx.unam.ciencias.edd.proyecto1;

import java.io.IOException;
import java.io.BufferedReader;
import java.util.Comparator;
import java.text.Normalizer;
import java.text.Normalizer.Form;

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
     * @param bufer Entrada de la cual leer el archivo
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
     * @return Si se le han añadido lineas al archivo regresa true en otro caso false
     */
    public boolean esVacio() {
        return lineas.esVacia();
    }

    /**
     * Ordena el archivo lexicográficamente usando el orden por defecto.
     */
    public void ordena() {
        this.ordena((a, b) -> compara(a, b));
    }

    /**
     * Ordena el archivo lexicográficamente usando el inverso del orden por defecto
     */
    public void ordenaReversa() {
        this.ordena((a, b) -> -compara(a, b));
    }

    /**
     * Ordena el archivo usando el comparador provisto
     * 
     * @param comparador
     */
    public void ordena(Comparator<String> comparador) {
        lineas = lineas.mergeSort(comparador);
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

    private int compara(String a, String b) {
        a = Normalizer.normalize(a, Form.NFKD).replaceAll("\\p{M}", "");
        b = Normalizer.normalize(b, Form.NFKD).replaceAll("\\p{M}", "");
        int minimo = Math.min(a.length(), b.length());
        int contA = 0, contB = 0;
        for (int i = 0; i < minimo; i++) {
            int charA = a.codePointAt(contA);
            int charB = b.codePointAt(contB);
            if (Character.isWhitespace(charA)) {
                contA++;
            } else if (Character.isWhitespace(charB)) {
                contB++;
            } else if (charA != charB) {
                return charA - charB;
            }
        }
        return a.length() - b.length();
    }
}