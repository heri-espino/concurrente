// importa la excepcion de una barrera rota
import java.util.concurrent.BrokenBarrierException;
// importa la barrera ciclica para coordinar los dos hilos
import java.util.concurrent.CyclicBarrier;

// declara la clase principal del ejercicio
public class Ejercicio01VariableCerrojo {
    // define el numero de repeticiones de cada proceso
    private static final int ITERACIONES = 3;
    // declara la variable compartida que representa el cerrojo
    private static volatile int cerrojo = 0;
    // declara el contador compartido de la seccion critica
    private static volatile int contador = 0;
    // coordina a los hilos despues de revisar el cerrojo
    private static final CyclicBarrier despuesDeComprobar = new CyclicBarrier(2);

    // declara el metodo que simula trabajo dentro de la seccion critica
    private static void pausa() {
        // inicia el bloque para manejar una interrupcion
        try {
            // detiene el hilo durante unos milisegundos
            Thread.sleep(5);
        // captura la interrupcion del hilo
        } catch (InterruptedException excepcion) {
            // conserva la marca de interrupcion del hilo
            Thread.currentThread().interrupt();
        }
    }

    // declara el metodo que ejecuta el algoritmo
    private static void proceso(int id) {
        // repite la entrada a la seccion critica
        for (int iteracion = 1; iteracion <= ITERACIONES; iteracion++) {
            // espera mientras el cerrojo parece ocupado
            while (cerrojo == 1) {
                // cede el procesador a otro hilo
                Thread.yield();
            }

            // inicia el bloque para sincronizar la prueba de escritorio
            try {
                // hace que ambos hilos pasen despues de leer cero
                despuesDeComprobar.await();
            // captura una interrupcion durante la espera
            } catch (InterruptedException excepcion) {
                // conserva la marca de interrupcion
                Thread.currentThread().interrupt();
                // abandona el proceso
                return;
            // captura una barrera que dejo de funcionar
            } catch (BrokenBarrierException excepcion) {
                // abandona el proceso
                return;
            }

            // activa el cerrojo despues de la comprobacion
            cerrojo = 1;
            // informa que el proceso entro
            System.out.println("P" + id + " entra a la seccion critica en la iteracion " + iteracion);
            // copia el valor actual del contador
            int valorLeido = contador;
            // simula una pausa entre leer y escribir
            pausa();
            // escribe el nuevo valor del contador
            contador = valorLeido + 1;
            // informa que el proceso salio
            System.out.println("P" + id + " sale con contador = " + contador);
            // libera el cerrojo
            cerrojo = 0;
        }
    }

    // declara el punto de entrada del programa
    public static void main(String[] args) throws InterruptedException {
        // crea el hilo que representa al proceso cero
        Thread p0 = new Thread(() -> proceso(0), "P0");
        // crea el hilo que representa al proceso uno
        Thread p1 = new Thread(() -> proceso(1), "P1");

        // muestra el nombre de la prueba
        System.out.println("prueba de variable cerrojo");
        // inicia el proceso cero
        p0.start();
        // inicia el proceso uno
        p1.start();
        // espera que termine el proceso cero
        p0.join();
        // espera que termine el proceso uno
        p1.join();

        // muestra el contador que debia obtenerse
        System.out.println("contador esperado: " + (ITERACIONES * 2));
        // muestra el contador realmente obtenido
        System.out.println("contador obtenido: " + contador);
        // explica el problema mostrado en la prueba
        System.out.println("resultado: comprobar y modificar cerrojo son dos operaciones separadas");
    }
}
