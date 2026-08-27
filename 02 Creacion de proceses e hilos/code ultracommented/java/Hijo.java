// No se requieren imports: ProcessHandle y System pertenecen a java.lang,
// paquete disponible automaticamente en todos los programas Java.
public class Hijo {
    // main() es el punto de entrada del proceso hijo iniciado por el padre.
    public static void main(String[] args) {
        System.out.println("Soy el proceso hijo");
        // ProcessHandle.current() representa al proceso Java actual.
        // pid() obtiene su identificador asignado por el sistema operativo.
        System.out.println("Mi PID: " + ProcessHandle.current().pid());
    }
}
