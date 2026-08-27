public class ProcessBuilderExample {
    public static void main(String[] args) throws Exception {
        // Crear el proceso hijo.
        Process hijo = new ProcessBuilder(
                "java", "-cp", System.getProperty("java.class.path"), "Hijo"
        ).inheritIO().start();

        System.out.println("Soy el proceso padre");
        System.out.println("PID de mi hijo: " + hijo.pid());
        System.out.println("Mi PID: " + ProcessHandle.current().pid());

        // Esperar a que termine el hijo.
        int salida = hijo.waitFor();
        System.out.println("Codigo de salida: " + salida);
    }
}
