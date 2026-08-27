// Proceso hijo iniciado desde ProcessBuilderExample.
public class Hijo {
    public static void main(String[] args) {
        System.out.println("Soy el proceso hijo");
        System.out.println("Mi PID: " + ProcessHandle.current().pid());
    }
}
