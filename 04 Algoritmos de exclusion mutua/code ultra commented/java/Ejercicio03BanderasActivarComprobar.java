// importa el cerrojo de cuenta regresiva para coordinar los hilos
import java.util.concurrent.CountDownLatch;

// declara la clase principal del ejercicio
public class Ejercicio03BanderasActivarComprobar {
    // declara la bandera del proceso cero
    private static volatile boolean flag0 = false;
    // declara la bandera del proceso uno
    private static volatile boolean flag1 = false;
    // declara el contador compartido
    private static volatile int contador = 0;
    // espera hasta que ambos procesos activen sus banderas
    private static final CountDownLatch banderasActivadas = new CountDownLatch(2);

    // declara el metodo que consulta la bandera contraria
    private static boolean banderaDelOtro(int id) {
        // devuelve la bandera del proceso contrario
        return id == 0 ? flag1 : flag0;
    }

    // declara el metodo que ejecuta el algoritmo
    private static void proceso(int id) {
        // activa la bandera del proceso cero
        if (id == 0) {
            // indica que el proceso cero quiere entrar
            flag0 = true;
        // activa la bandera del proceso uno
        } else {
            // indica que el proceso uno quiere entrar
            flag1 = true;
        }
        // informa que este proceso ya activo su bandera
        banderasActivadas.countDown();

        // inicia el bloque para manejar una interrupcion
        try {
            // espera a que ambas banderas esten activas
            banderasActivadas.await();
        // captura la interrupcion del hilo
        } catch (InterruptedException excepcion) {
            // conserva la marca de interrupcion
            Thread.currentThread().interrupt();
            // abandona el proceso
            return;
        }

        // espera mientras la bandera contraria este activa
        while (banderaDelOtro(id) && !Thread.currentThread().isInterrupted()) {
            // cede el procesador a otro hilo
            Thread.yield();
        }

        // termina la demostracion si el hilo fue interrumpido
        if (banderaDelOtro(id)) {
            // abandona el proceso sin entrar
            return;
        }

        // informa que el proceso entro
        System.out.println("P" + id + " entra a la seccion critica");
        // incrementa el contador compartido
        contador++;
        // informa el valor del contador
        System.out.println("P" + id + " sale con contador = " + contador);
    }

    // declara el punto de entrada del programa
    public static void main(String[] args) throws InterruptedException {
        // crea el hilo del proceso cero
        Thread p0 = new Thread(() -> proceso(0), "P0");
        // crea el hilo del proceso uno
        Thread p1 = new Thread(() -> proceso(1), "P1");

        // muestra el nombre de la prueba
        System.out.println("prueba de banderas activar y comprobar");
        // inicia el proceso cero
        p0.start();
        // inicia el proceso uno
        p1.start();
        // espera un tiempo limitado al proceso cero
        p0.join(200);
        // espera un tiempo limitado al proceso uno
        p1.join(200);

        // interrumpe el proceso cero si sigue esperando
        if (p0.isAlive()) {
            // solicita que el proceso cero termine
            p0.interrupt();
        }
        // interrumpe el proceso uno si sigue esperando
        if (p1.isAlive()) {
            // solicita que el proceso uno termine
            p1.interrupt();
        }
        // espera el fin del proceso cero
        p0.join();
        // espera el fin del proceso uno
        p1.join();

        // muestra el contador obtenido
        System.out.println("contador obtenido: " + contador);
        // explica que ambos procesos quedan esperando
        System.out.println("resultado: ambos procesos quedan esperando cuando las dos banderas valen true");
    }
}
