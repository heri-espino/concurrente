// declara la clase principal del ejercicio
public class Ejercicio06LamportBakery {
    // define el numero de procesos de la prueba
    private static final int PROCESOS = 3;
    // define el numero de entradas de cada proceso
    private static final int ITERACIONES = 3;
    // declara el arreglo de procesos compartidos
    private static final Proceso[] procesos = crearProcesos();
    // declara el contador compartido
    private static volatile int contador = 0;

    // declara la clase que representa los datos de un proceso
    private static class Proceso {
        // declara el dato que indica quien esta tomando turno
        private volatile int entrando = 0;
        // declara el dato que guarda el numero de turno
        private volatile int numero = 0;
    }

    // declara el metodo que crea los procesos compartidos
    private static Proceso[] crearProcesos() {
        // crea el arreglo de procesos
        Proceso[] resultado = new Proceso[PROCESOS];
        // recorre los identificadores de los procesos
        for (int id = 0; id < PROCESOS; id++) {
            // crea los datos del proceso actual
            resultado[id] = new Proceso();
        }
        // devuelve el arreglo creado
        return resultado;
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

    // declara el metodo que ejecuta el algoritmo Bakery
    private static void proceso(int id) {
        // repite el acceso a la seccion critica
        for (int iteracion = 1; iteracion <= ITERACIONES; iteracion++) {
            // indica que el proceso esta eligiendo su turno
            procesos[id].entrando = 1;

            // inicia el numero mayor encontrado
            int mayor = 0;
            // recorre los turnos actuales
            for (int proceso = 0; proceso < PROCESOS; proceso++) {
                // conserva el turno mas grande
                mayor = Math.max(mayor, procesos[proceso].numero);
            }
            // asigna el siguiente turno al proceso actual
            procesos[id].numero = mayor + 1;
            // indica que termino de elegir su turno
            procesos[id].entrando = 0;

            // compara el turno con cada proceso
            for (int proceso = 0; proceso < PROCESOS; proceso++) {
                // ignora la comparacion consigo mismo
                if (proceso == id) {
                    // salta al siguiente proceso
                    continue;
                }

                // espera a que el otro proceso termine de elegir turno
                while (procesos[proceso].entrando == 1) {
                    // cede el procesador a otro hilo
                    Thread.yield();
                }

                // espera mientras el otro tenga prioridad
                while (procesos[proceso].numero != 0
                        && (procesos[proceso].numero < procesos[id].numero
                        || (procesos[proceso].numero == procesos[id].numero && proceso < id))) {
                    // cede el procesador a otro hilo
                    Thread.yield();
                }
            }

            // informa que el proceso entro con su turno
            System.out.println("P" + id + " entra con numero " + procesos[id].numero);
            // copia el valor del contador
            int valorLeido = contador;
            // simula trabajo dentro de la seccion critica
            pausa();
            // actualiza el contador compartido
            contador = valorLeido + 1;
            // informa el valor del contador
            System.out.println("P" + id + " sale con contador = " + contador);
            // libera el turno del proceso actual
            procesos[id].numero = 0;
        }
    }

    // declara el punto de entrada del programa
    public static void main(String[] args) throws InterruptedException {
        // crea el arreglo de hilos
        Thread[] hilos = new Thread[PROCESOS];

        // muestra el nombre de la prueba
        System.out.println("prueba del algoritmo de la panaderia de Lamport");
        // recorre los identificadores de los procesos
        for (int id = 0; id < PROCESOS; id++) {
            // copia el identificador para usarlo dentro de la lambda
            int idFinal = id;
            // crea el hilo del proceso actual
            hilos[id] = new Thread(() -> proceso(idFinal), "P" + id);
            // inicia el hilo del proceso actual
            hilos[id].start();
        }

        // recorre los hilos creados
        for (Thread hilo : hilos) {
            // espera el fin del hilo actual
            hilo.join();
        }

        // muestra el contador esperado
        System.out.println("contador esperado: " + (PROCESOS * ITERACIONES));
        // muestra el contador obtenido
        System.out.println("contador obtenido: " + contador);
        // explica las propiedades que cumple
        System.out.println("resultado: cumple exclusion mutua, progreso y espera limitada para N procesos");
    }
}
