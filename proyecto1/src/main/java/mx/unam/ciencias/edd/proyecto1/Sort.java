package mx.unam.ciencias.edd.proyecto1;

import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.BufferedReader;

/**
 * Clase para ordenar archivos lexicograficamente. Los archivos a ordenar se
 * pasan como argumentos al constructor de la clase. Todos los archivos se
 * ordenan como uno solo. Admite las banderas -o para guardar el resultado en un
 * fichero y -r para invertir el orden.
 */
public class Sort {
    // indica si el arvhivo se debe ordenar en reversa
    private boolean reversa;
    // ruta donde se va a guardar el archivo ordenado. Si no se especifica la ruta
    // tiene valor null
    private String ruta;
    // el archivo a ordenar
    private Archivo archivo;

    /**
     * Constructor que crea la aplicación a partir de los argumentos que le pasen.
     * Los argumentos pueden ser -r para ordenar los archivos en reversa, -o seguido
     * de una ruta para guardar el archivo ordenado en la ruta. Además puede recibir
     * rutas correspondientes a los archivos a ordenar, estos se ordenan como uno
     * solo. Si no se reciben archivos a ordenar se toma la entrada estándar.
     * 
     * @param argumentos Un arreglo que contiene los argumentos que se le pasan a la
     *                   aplicación.
     * @throws ExcepcionArchivoNoEncontrado Si algún archivo pasado como parámetro no existe o no se pudo leer.
     * @throws ExcepcionBanderaInvalida Si se pasó una bandera inválida.
     * @throws ExcepcionArgumentoInvalido Si se introdujo un argumento inválido.
     */
    public Sort(String[] argumentos) {
        archivo = new Archivo();
        // procesa los argumentos
        for (int i = 0; i < argumentos.length; i++) {
            String cadena = argumentos[i];
            if (esBandera(cadena)) {
                int salto = manejarBandera(argumentos, cadena, i);
                i += salto; // se salta los argumentos ya procesados por manejarBandera.
            } else {
                leerArchivo(cadena);
            }
        }
        // Si no se pasó como parámetro ningún archivo se toma la entrada estándar.
        if (archivo.esVacio())
            leerArchivo(null);
    }

    /**
     * Ordena el archivo
     * 
     * @return Un string con la información del archivo ordenado o null si se
     *         especificó -o
     * @throws ExcepcionArchivoNoEncontrado Si no se puede acceder a la ruta para guardar el archivo.
     */
    public String ejecutar() {
        if (reversa)
            archivo.ordenaReversa();
        else
            archivo.ordena();
        if (ruta != null) {
            guardarArchivo();
            return "";
        }
        return archivo.toString();
    }

    /**
     * Guarda el archivo en el sistema.
     */
    private void guardarArchivo() {
        FileWriter f = null;
        try {
            f = new FileWriter(new File(ruta));
        } catch (FileNotFoundException e) {
            throw new ExcepcionArchivoNoEncontrado("No se pudo abrir: " + e.getMessage(), e);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            f.write(archivo.toString());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                f.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Lee un archivo del sistema. Si se le pasa una cadena null crea un Reader a
     * partir de la entrada estándar
     */
    private void leerArchivo(String rutaArchivo) {
        BufferedReader bufer = null;
        try {
            if (rutaArchivo == null)
                bufer = new BufferedReader(new InputStreamReader(System.in));
            else
                bufer = new BufferedReader(new FileReader(new File(rutaArchivo)));
        } catch (FileNotFoundException e) {
            throw new ExcepcionArchivoNoEncontrado("No se pudo leer: " + e.getMessage(), e);
        }
        try {
            archivo.cargarArchivo(bufer);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bufer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Maneja una cadena que contiene banderas
     * 
     * @param bandera Las banderas a procesarse
     * @return Entero con el número de argumentos que fueron procesados con la
     *         bandera y deben ser saltados
     * 
     */
    private int manejarBandera(String[] argumentos, String bandera, int indice) {
        int procesados = 0;
        for (int i = 1; i < bandera.length(); i++) {
            switch (bandera.charAt(i)) {
                case 'r':
                    reversa = true;
                    break;
                case 'o':
                    int siguiente = indice + procesados + 1;
                    if (ruta != null)
                        throw new ExcepcionArgumentoInvalido("Ya se especificó un archivo de salida.");
                    if (siguiente >= argumentos.length)
                        throw new ExcepcionArgumentoInvalido("La opcion 'o' debe recibir un argumento.");
                    ruta = argumentos[siguiente];
                    procesados++;
                    break;
                default:
                    throw new ExcepcionBanderaInvalida(bandera.charAt(i) + " es una opción inválida.");
            }
        }
        return procesados;
    }

    /**
     * Checa si un argumento es una bandera
     */
    private boolean esBandera(String s) {
        return s.charAt(0) == '-';
    }

}