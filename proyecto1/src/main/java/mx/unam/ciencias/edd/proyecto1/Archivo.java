package mx.unam.ciencias.edd.proyecto1;

import java.io.*;
import java.util.Comparator;
import java.text.Normalizer;

import mx.unam.ciencias.edd.Lista;

/**
 * 
 */
public class Archivo {

    private Lista<String> lineas;

    /**
     * Constructor que crea un archivo a partir de los BufferedReader de la entrada,
     * Todos son tratados como un único archivo.
     * 
     * @param lector Arreglo con un BufferedReader que representa cada archivo a
     *               ordenar
     * @throws IOException
     */
    public Archivo(BufferedReader[] lector) throws IOException {
        for (int i = 0; i < lector.length; i++) {
            String linea;
            do {
                linea = lector[i].readLine();
                if (linea != null)
                    lineas.agregaFinal(linea);
            } while (linea != null);
        }
    }

    /**
     * Ordena el archivo lexicográficamente usando el orden por defecto.
     */
    public void ordena() {

    }

    /**
     * Ordena el archivo lexicográficamente usando el inverso del orden por defecto
     */
    public void ordenaReversa() {

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
        int minimo = Math.min(a.length(), b.length());
        int contA = 0, contB = 0;
        for (int i = 0; i < minimo; i++) {
            int charA = a.charAt(contA);
            int charB = b.charAt(contB);
            if (!Character.isLetterOrDigit(charA)) {
                contA++;
            } else if (!Character.isLetterOrDigit(charB)) {
                contB++;
            } else if (Character.toLowerCase(charA) == Character.toLowerCase(charB) && charA != charB) {

            }
        }
    }

    private int cambiaChar(char a) {
        switch (a) {
            case 'á':
            case 'ä':
                a = 'a';
                break;
            case 'Á':
            case 'Ä':
                a = 'A';
                break;
            case 'é':
            case 'ë':
                a = 'e';
                break;
            case 'É':
            case 'Ë':
                a = 'E';
                break;
            case 'í':
            case 'ï':
                a = 'i';
                break;
            case 'Í':
            case 'Ï':
                a = 'I';
                break;
            case 'ó':
            case 'ö':
                a = 'o';
                break;
            case 'Ó':
            case 'Ö':
                a = 'O';
                break;
            case 'ú':
            case 'ü':
                a = 'u';
                break;
            case 'Ú':
            case 'Ü':
                a = 'U';
                break;
            case 'ñ':
                a = 'n';
                break;
            case 'Ñ':
                a = 'N';
                break;
            default:
                break;
        }
        return a;
    }
}