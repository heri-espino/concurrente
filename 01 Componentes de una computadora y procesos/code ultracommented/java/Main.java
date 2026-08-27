// BufferedReader permite leer texto de forma eficiente desde un flujo.
// En este programa se usa para leer la salida del comando externo 'id'.
import java.io.BufferedReader;

// InputStreamReader convierte bytes del proceso en caracteres.
// Se combina con BufferedReader para leer la salida línea por línea.
import java.io.InputStreamReader;

// Main es la clase pública del programa.
// El archivo debe tener el mismo nombre que esta clase: Main.java.
public class Main {

    // main() es el punto de entrada de cualquier programa Java.
    // String[] args contiene los argumentos recibidos desde la terminal.
    public static void main(String[] args) {

        // ProcessHandle representa un proceso administrado por el sistema operativo.
        // current() obtiene el proceso correspondiente a esta JVM.
        ProcessHandle actual = ProcessHandle.current();

        // pid() obtiene el identificador del proceso actual.
        // long permite almacenar identificadores de proceso grandes.
        long pid = actual.pid();

        // parent() devuelve el proceso padre dentro de un Optional.
        // map(ProcessHandle::pid) obtiene el PID si el padre existe.
        // orElse(-1L) usa -1 cuando no se puede encontrar un proceso padre.
        long ppid = actual.parent().map(ProcessHandle::pid).orElse(-1L);

        // getUnixId("-u") ejecuta 'id -u' para consultar el UID del usuario.
        long uid = getUnixId("-u");

        // Esta línea vuelve a ejecutar 'id -u' para obtener el valor mostrado
        // como EUID. En este código, UID y EUID se consultan de la misma manera.
        long euid = getUnixId("-u");

        // println() imprime el texto y agrega un salto de línea.
        System.out.println("PID: " + pid);
        System.out.println("PPID: " + ppid);
        System.out.println("UID: " + uid);
        System.out.println("EUID: " + euid);
    }

    // Este método auxiliar consulta un identificador POSIX mediante el comando 'id'.
    // flag indica la opción que se enviará al comando, por ejemplo '-u'.
    private static long getUnixId(String flag) {
        try {
            // ProcessBuilder configura un proceso externo.
            // En este caso ejecuta el comando 'id' junto con la opción recibida.
            Process process = new ProcessBuilder("id", flag).start();

            // getInputStream() obtiene la salida estándar del proceso hijo.
            // InputStreamReader convierte los bytes en caracteres.
            // BufferedReader permite leer una línea completa con readLine().
            // try (...) cierra automáticamente el lector al terminar el bloque.
            try (BufferedReader reader = new BufferedReader(
                    new InputStreamReader(process.getInputStream()))) {

                // readLine() lee la primera línea producida por el comando 'id'.
                String line = reader.readLine();

                // Se comprueba que la línea exista antes de intentar convertirla.
                if (line != null) {
                    // trim() elimina espacios sobrantes.
                    // parseLong() convierte el texto en un número de tipo long.
                    return Long.parseLong(line.trim());
                }
            }
        } catch (Exception ignored) {
            // Si el comando falla o el sistema no es POSIX, se continúa sin detener
            // el programa y se devuelve el valor indicado al final del método.
        }

        // -1 indica que no fue posible obtener el identificador solicitado.
        return -1L;
    }
}
