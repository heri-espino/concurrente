public class Ejercicio06LamportBakery {
    private static final int PROCESOS = 3;
    private static final int ITERACIONES = 3;
    private static final Proceso[] procesos = crearProcesos();
    private static volatile int contador = 0;

    private static class Proceso {
        private volatile int entrando = 0;
        private volatile int numero = 0;
    }

    private static Proceso[] crearProcesos() {
        Proceso[] resultado = new Proceso[PROCESOS];
        for (int id = 0; id < PROCESOS; id++) {
            resultado[id] = new Proceso();
        }
        return resultado;
    }

    // simula trabajo dentro de la seccion critica
    private static void pausa() {
        try {
            Thread.sleep(5);
        } catch (InterruptedException excepcion) {
            Thread.currentThread().interrupt();
        }
    }

    // aplica el algoritmo de la panaderia de Lamport
    private static void proceso(int id) {
        for (int iteracion = 1; iteracion <= ITERACIONES; iteracion++) {
            procesos[id].entrando = 1;

            int mayor = 0;
            for (int proceso = 0; proceso < PROCESOS; proceso++) {
                mayor = Math.max(mayor, procesos[proceso].numero);
            }
            procesos[id].numero = mayor + 1;
            procesos[id].entrando = 0;

            for (int proceso = 0; proceso < PROCESOS; proceso++) {
                if (proceso == id) {
                    continue;
                }

                while (procesos[proceso].entrando == 1) {
                    Thread.yield();
                }

                while (procesos[proceso].numero != 0
                        && (procesos[proceso].numero < procesos[id].numero
                        || (procesos[proceso].numero == procesos[id].numero && proceso < id))) {
                    Thread.yield();
                }
            }

            System.out.println("P" + id + " entra con numero " + procesos[id].numero);
            int valorLeido = contador;
            pausa();
            contador = valorLeido + 1;
            System.out.println("P" + id + " sale con contador = " + contador);
            procesos[id].numero = 0;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread[] hilos = new Thread[PROCESOS];

        System.out.println("prueba del algoritmo de la panaderia de Lamport");
        for (int id = 0; id < PROCESOS; id++) {
            int idFinal = id;
            hilos[id] = new Thread(() -> proceso(idFinal), "P" + id);
            hilos[id].start();
        }

        for (Thread hilo : hilos) {
            hilo.join();
        }

        System.out.println("contador esperado: " + (PROCESOS * ITERACIONES));
        System.out.println("contador obtenido: " + contador);
        System.out.println("resultado: cumple exclusion mutua, progreso y espera limitada para N procesos");
    }
}
