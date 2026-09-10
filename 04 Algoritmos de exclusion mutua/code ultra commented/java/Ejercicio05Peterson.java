// declara la clase principal del ejercicio
public class Ejercicio05Peterson {
    // define el numero de repeticiones de cada proceso
    private static final int ITERACIONES = 3;
    // declara la bandera del proceso cero
    private static volatile boolean flag0 = false;
    // declara la bandera del proceso uno
    private static volatile boolean flag1 = false;
    // declara el turno compartido
    private static volatile int turn = 0;
    // declara el contador compartido
    private static volatile int contador = 0;

    // declara el metodo que consulta una bandera
    private static boolean bandera(int id) {
        // devuelve la bandera solicitada
        return id == 0 ? flag0 : flag1;
    }

    // declara el metodo que cambia una bandera
    private static void establecerBandera(int id, boolean valor) {
        // cambia la bandera del proceso cero
        if (id == 0) {
            // guarda el valor de la bandera cero
            flag0 = valor;
        // cambia la bandera del proceso uno
        } else {
            // guarda el valor de la bandera uno
            flag1 = valor;
        }
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

    // declara el metodo que ejecuta Peterson
    private static void proceso(int id) {
        // calcula el identificador del proceso contrario
        int otro = 1 - id;

        // repite el acceso a la seccion critica
        for (int iteracion = 1; iteracion <= ITERACIONES; iteracion++) {
            // anuncia la intencion de entrar
            establecerBandera(id, true);
            // cede la prioridad al otro proceso
            turn = otro;

            // espera mientras el otro quiera entrar y tenga prioridad
            while (bandera(otro) && turn == otro) {
                // cede el procesador a otro hilo
                Thread.yield();
            }

            // informa que el proceso entro
            System.out.println("P" + id + " entra a la seccion critica en la iteracion " + iteracion);
            // copia el valor del contador
            int valorLeido = contador;
            // simula trabajo dentro de la seccion critica
            pausa();
            // actualiza el contador compartido
            contador = valorLeido + 1;
            // informa el valor del contador
            System.out.println("P" + id + " sale con contador = " + contador);

            // anuncia que el proceso ya salio
            establecerBandera(id, false);
        }
    }

    // declara el punto de entrada del programa
    public static void main(String[] args) throws InterruptedException {
        // crea el hilo del proceso cero
        Thread p0 = new Thread(() -> proceso(0), "P0");
        // crea el hilo del proceso uno
        Thread p1 = new Thread(() -> proceso(1), "P1");

        // muestra el nombre de la prueba
        System.out.println("prueba del algoritmo de Peterson");
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
        // explica las propiedades que cumple
        System.out.println("resultado: cumple exclusion mutua, progreso y espera limitada para dos procesos");
    }
}
