package mx.unam.ciencias.edd.proyecto1;

public class Sort {

    public static void main(String[] args) {
        try {
            App app = new App(args);
            String resultado = app.ejecutar();
            System.out.print(resultado);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}