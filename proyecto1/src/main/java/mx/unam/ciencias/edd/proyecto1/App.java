package mx.unam.ciencias.edd.proyecto1;

public class App {

    public static void main(String[] args) {
        try {
            Sort app = new Sort(args);
            String resultado = app.ejecutar();
            System.out.print(resultado);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}