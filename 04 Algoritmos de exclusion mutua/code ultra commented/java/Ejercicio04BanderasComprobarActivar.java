// importa la excepcion de una barrera rota
import java.util.concurrent.BrokenBarrierException;
// importa la barrera ciclica para sincronizar la prueba
import java.util.concurrent.CyclicBarrier;

// declara la clase principal del ejercicio
public class Ejercicio04BanderasComprobarActivar {
    // define el numero de repeticiones de cada proceso
    private static final int ITERACIONES = 3;
    // declara la bandera del proceso cero
    private static volatile boolean flag0 = false;
    // declara la bandera del proceso uno
    private static volatile boolean flag1 = false;
    // declara el contador compartido
    private static volatile int contador = 0;
    // coordina a los procesos despues de comprobar la bandera
    private static final CyclicBarrier despuesDeComprobar = new CyclicBarrier(2);

    // declara el metodo que consulta la bandera contraria
    private static boolean banderaDelOtro(int id) {
        // devuelve la bandera del proceso contrario
        return id == 0 ? flag1 : flag0;
    }

    // declara el metodo que simula trabajo dentro de la seccion critica
    private static void pausa() {
        // inicia el bloque para manejar una interrupcion
        try {
            // detiene el hilo durante unos milisegundos
            Thread.sleep(5);
        // captura la interrupcion del hilo
        } catch (InterruptedException excepcion) {
            // conserva la marca de interrupcion
            Thread.currentThread().interrupt();
        }
    }

    // declara el metodo que ejecuta el algoritmo
    private static void proceso(int id) {
        // repite el acceso a la seccion critica
        for (int iteracion = 1; iteracion <= ITERACIONES; iteracion++) {
            // espera mientras la bandera contraria este activa
            while (banderaDelOtro(id)) {
                // cede el procesador a otro hilo
                Thread.yield();
            }

            // inicia el bloque para coordinar la prueba de escritorio
            try {
                // obliga a que ambos procesos hayan comprobado false
                despuesDeComprobar.await();
            // captura la interrupcion del hilo
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

            // activa la bandera del proceso cero
            if (id == 0) {
                // indica que el proceso cero quiere entrar
                flag0 = true;
            // activa la bandera del proceso uno
            } else {
                // indica que el proceso uno quiere entrar
                flag1 = true;
            }

            // informa que el proceso entro
            System.out.println("P" + id + " entra a la seccion critica en la iteracion " + iteracion);
            // copia el valor del contador
            int valorLeido = contador;
            // simula una pausa antes de escribir
            pausa();
            // escribe el nuevo valor del contador
            contador = valorLeido + 1;
            // informa el valor del contador
            System.out.println("P" + id + " sale con contador = " + contador);

            // desactiva la bandera del proceso cero
            if (id == 0) {
                // indica que el proceso cero ya salio
                flag0 = false;
            // desactiva la bandera del proceso uno
            } else {
                // indica que el proceso uno ya salio
                flag1 = false;
            }
        }
    }

    // declara el punto de entrada del programa
    public static void main(String[] args) throws InterruptedException {
        // crea el hilo del proceso cero
        Thread p0 = new Thread(() -> proceso(0), "P0");
        // crea el hilo del proceso uno
        Thread p1 = new Thread(() -> proceso(1), "P1");

        // muestra el nombre de la prueba
        System.out.println("prueba de banderas comprobar y activar");
        // inicia el proceso cero
        p0.start();
        // inicia el proceso uno
        p1.start();
        // espera el fin del proceso cero
        p0.join();
        // espera el fin del proceso uno
        p1.join();

        // muestra el contador esperado
        System.out.println("contador esperado: " + (ITERACIONES * 2));
        // muestra el contador obtenido
        System.out.println("contador obtenido: " + contador);
        // explica que la prueba permite una entrada simultanea
        System.out.println("resultado: los dos procesos pueden entrar a la vez");
    }
}
