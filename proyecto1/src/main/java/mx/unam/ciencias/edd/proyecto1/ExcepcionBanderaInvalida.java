package mx.unam.ciencias.edd.proyecto1;

/**
 * Excepcion que se lanza cuando se pasa una bandera inválida al programa
 */
public class ExcepcionBanderaInvalida extends RuntimeException {

    /**
     * Constructor que no recibe parámetros
     */
    public ExcepcionBanderaInvalida() {}

    /**
     * Constructor que recibe un mensaje.
     * @param mensaje Mensaje recibido
     */
    public ExcepcionBanderaInvalida(String mensaje) {
        super(mensaje);
    }

}