// declara la clase principal del ejercicio
public class Ejercicio02AlternanciaEstricta {
    // define el numero de repeticiones de cada proceso
    private static final int ITERACIONES = 3;
    // declara el turno compartido
    private static volatile int turn = 0;
    // declara el contador compartido
    private static volatile int contador = 0;

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

    // declara el metodo que ejecuta la alternancia estricta
    private static void proceso(int id) {
        // repite el acceso a la seccion critica
        for (int iteracion = 1; iteracion <= ITERACIONES; iteracion++) {
            // espera hasta que sea el turno de este proceso
            while (turn != id) {
                // cede el procesador a otro hilo
                Thread.yield();
            }

            // informa que el proceso entro
            System.out.println("P" + id + " entra con turn = " + turn);
            // copia el contador antes de modificarlo
            int valorLeido = contador;
            // simula trabajo dentro de la seccion critica
            pausa();
            // actualiza el contador compartido
            contador = valorLeido + 1;
            // informa el valor resultante
            System.out.println("P" + id + " sale con contador = " + contador);
            // entrega el turno al otro proceso
            turn = 1 - id;
        }
    }

    // declara el punto de entrada del programa
    public static void main(String[] args) throws InterruptedException {
        // crea el hilo del proceso cero
        Thread p0 = new Thread(() -> proceso(0), "P0");
        // crea el hilo del proceso uno
        Thread p1 = new Thread(() -> proceso(1), "P1");

        // muestra el nombre de la prueba
        System.out.println("prueba de alternancia estricta");
        // inicia el proceso cero
        p0.start();
        // inicia el proceso uno
        p1.start();
        // espera el fin del proceso cero
        p0.join();
        // espera el fin del proceso uno
        p1.join();

        // muestra el valor esperado
        System.out.println("contador esperado: " + (ITERACIONES * 2));
        // muestra el valor obtenido
        System.out.println("contador obtenido: " + contador);
        // explica la limitacion de progreso
        System.out.println("resultado: conserva exclusion mutua pero puede bloquear por falta de progreso");
    }
}
