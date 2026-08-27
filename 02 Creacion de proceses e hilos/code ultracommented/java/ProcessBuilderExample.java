// No se requieren imports: Process, ProcessBuilder, ProcessHandle y System
// pertenecen a java.lang, paquete disponible automaticamente en Java.
public class ProcessBuilderExample {
    // throws Exception simplifica el ejemplo al propagar errores de creacion
    // del proceso y la posible interrupcion durante waitFor().
    public static void main(String[] args) throws Exception {
        // ProcessBuilder define el comando que ejecutara el proceso hijo.
        // -cp conserva el classpath actual para que Java pueda encontrar Hijo.
        Process hijo = new ProcessBuilder(
                "java", "-cp", System.getProperty("java.class.path"), "Hijo"
        ).inheritIO().start();

        // inheritIO() conecta la salida del hijo con esta misma terminal.
        // start() inicia el proceso y devuelve el objeto Process que lo representa.
        System.out.println("Soy el proceso padre");
        // pid() obtiene el PID asignado al proceso hijo.
        System.out.println("PID de mi hijo: " + hijo.pid());
        // ProcessHandle.current().pid() obtiene el PID del proceso padre actual.
        System.out.println("Mi PID: " + ProcessHandle.current().pid());

        // waitFor() espera hasta que termina el proceso hijo y devuelve su salida.
        int salida = hijo.waitFor();
        System.out.println("Codigo de salida: " + salida);
    }
}
