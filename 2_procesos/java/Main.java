// Importación de BufferedReader para la lectura eficiente de flujos de texto del subproceso OS
import java.io.BufferedReader;
// Importación de InputStreamReader para transformar el flujo de bytes del proceso en caracteres
import java.io.InputStreamReader;


public class Main {

    // Punto de entrada del programa; 'static' permite su ejecución sin instanciar la clase
    public static void main(String[] args) {

        // Obtención del manejador de proceso nativo correspondiente al hilo de ejecución de la JVM
        ProcessHandle actual = ProcessHandle.current();

        // Process IDentifier
        long pid = actual.pid();
        // Parent Process IDentifier; se obtiene del manejador de proceso padre si existe, o -1 si no hay padre
        long ppid = actual.parent().map(ProcessHandle::pid).orElse(-1L);
        // User IDentifier; se obtiene invocando un subproceso nativo que ejecuta el comando 'id -u' en sistemas POSIX
        long uid = getUnixId("-u");
        // Effective User IDentifier; se obtiene invocando un subproceso nativo que ejecuta el comando 'id -u' en sistemas POSIX
        long euid = getUnixId("-u");

        // Impresión
        System.out.println("PID: " + pid);
        System.out.println("PPID: " + ppid);
        System.out.println("UID: " + uid);
        System.out.println("EUID: " + euid);
    }

    // Método privado auxiliar para consultar identidades POSIX leyendo la salida del binario '/usr/bin/id'
    private static long getUnixId(String flag) {
        try {
            // Instanciación y ejecución del subproceso nativo 'id' enviando la bandera especificada
            Process process = new ProcessBuilder("id", flag).start();

            // Envolvimiento del InputStream del proceso en un buffer para lectura no bloqueante de caracteres
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
                
                // Lectura de la primera línea impresa en stdout por la ejecución del comando 'id'
                String line = reader.readLine();

                // Verificación de existencia de respuesta válida enviada por el subproceso
                if (line != null) {
                    // Conversión de la representación textual del UID en un entero numérico de 64 bits (long)
                    return Long.parseLong(line.trim());
                }
            }
        } catch (Exception ignored) {
            // Captura silenciosa de excepciones para manejar entornos no POSIX (e.g., Windows)
        }

        // Valor por defecto devuelto en caso de fallo de IO, ausencia de entorno POSIX o error de parseo
        return -1L;
    }
}