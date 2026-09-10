import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Ejercicio01VariableCerrojo {
    private static final int ITERACIONES = 3;
    private static volatile int cerrojo = 0;
    private static volatile int contador = 0;
    private static final CyclicBarrier despuesDeComprobar = new CyclicBarrier(2);

    // simula trabajo dentro de la seccion critica
    private static void pausa() {
        try {
            Thread.sleep(5);
        } catch (InterruptedException excepcion) {
            Thread.currentThread().interrupt();
        }
    }

    // aplica el algoritmo de variable cerrojo
    private static void proceso(int id) {
        for (int iteracion = 1; iteracion <= ITERACIONES; iteracion++) {
            while (cerrojo == 1) {
                Thread.yield();
            }

            // fuerza el interleaving que muestra la falla del algoritmo
            try {
                despuesDeComprobar.await();
            } catch (InterruptedException excepcion) {
                Thread.currentThread().interrupt();
                return;
            } catch (BrokenBarrierException excepcion) {
                return;
            }

            cerrojo = 1;
            System.out.println("P" + id + " entra a la seccion critica en la iteracion " + iteracion);
            int valorLeido = contador;
            pausa();
            contador = valorLeido + 1;
            System.out.println("P" + id + " sale con contador = " + contador);
            cerrojo = 0;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread p0 = new Thread(() -> proceso(0), "P0");
        Thread p1 = new Thread(() -> proceso(1), "P1");

        System.out.println("prueba de variable cerrojo");
        p0.start();
        p1.start();
        p0.join();
        p1.join();

        System.out.println("contador esperado: " + (ITERACIONES * 2));
        System.out.println("contador obtenido: " + contador);
        System.out.println("resultado: comprobar y modificar cerrojo son dos operaciones separadas");
    }
}
