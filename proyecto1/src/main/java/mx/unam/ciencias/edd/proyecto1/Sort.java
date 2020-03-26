package mx.unam.ciencias.edd.proyecto1;

public class Sort {

    public static void main(String[] args) {
        try {
            App app = new App(args);
            app.ejecutar();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}