package mx.unam.ciencias.edd.proyecto1;

import java.io.IOException;
import java.io.BufferedReader;
import java.util.Comparator;
import java.text.Normalizer;
import java.text.Normalizer.Form;

import mx.unam.ciencias.edd.Lista;
import mx.unam.ciencias.edd.Cola;

/**
 * 
 */
public class Archivo {

    private Lista<String> lineas;

    /**
     * Constructor que crea un archivo a partir de los BufferedReader de la entrada,
     * Todos son tratados como un único archivo.
     * 
     * @param cola Cola con los buffers de entrada a ser procesados
     * @throws IOException
     * @throws IllegalArgumentException Si la cola es vacía.
     */
    public Archivo(Cola<BufferedReader> cola) throws IOException {
        if (cola == null)
            throw new IllegalArgumentException("La cola no puede estar vacía");
        while (!cola.esVacia()) {
            BufferedReader lector = cola.saca();
            String linea = lector.readLine();
            while (linea != null) {
                lineas.agregaFinal(linea);
                linea = lector.readLine();
            }
            lector.close();
        }
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