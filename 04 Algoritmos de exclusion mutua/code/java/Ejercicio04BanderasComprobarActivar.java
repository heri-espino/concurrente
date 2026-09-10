import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Ejercicio04BanderasComprobarActivar {
    private static final int ITERACIONES = 3;
    private static volatile boolean flag0 = false;
    private static volatile boolean flag1 = false;
    private static volatile int contador = 0;
    private static final CyclicBarrier despuesDeComprobar = new CyclicBarrier(2);

    // consulta la bandera del otro proceso
    private static boolean banderaDelOtro(int id) {
        return id == 0 ? flag1 : flag0;
    }

    // simula trabajo dentro de la seccion critica
    private static void pausa() {
        try {
            Thread.sleep(5);
        } catch (InterruptedException excepcion) {
            Thread.currentThread().interrupt();
        }
    }

    // comprueba la bandera y despues activa la propia
    private static void proceso(int id) {
        for (int iteracion = 1; iteracion <= ITERACIONES; iteracion++) {
            while (banderaDelOtro(id)) {
                Thread.yield();
            }

            // fuerza que ambos procesos comprueben false antes de activar su bandera
            try {
                despuesDeComprobar.await();
            } catch (InterruptedException excepcion) {
                Thread.currentThread().interrupt();
                return;
            } catch (BrokenBarrierException excepcion) {
                return;
            }

            if (id == 0) {
                flag0 = true;
            } else {
                flag1 = true;
            }

            System.out.println("P" + id + " entra a la seccion critica en la iteracion " + iteracion);
            int valorLeido = contador;
            pausa();
            contador = valorLeido + 1;
            System.out.println("P" + id + " sale con contador = " + contador);

            if (id == 0) {
                flag0 = false;
            } else {
                flag1 = false;
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread p0 = new Thread(() -> proceso(0), "P0");
        Thread p1 = new Thread(() -> proceso(1), "P1");

        System.out.println("prueba de banderas comprobar y activar");
        p0.start();
        p1.start();
        p0.join();
        p1.join();

        System.out.println("contador esperado: " + (ITERACIONES * 2));
        System.out.println("contador obtenido: " + contador);
        System.out.println("resultado: los dos procesos pueden entrar a la vez");
    }
}
