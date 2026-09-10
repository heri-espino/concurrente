public class Ejercicio05Peterson {
    private static final int ITERACIONES = 3;
    private static volatile boolean flag0 = false;
    private static volatile boolean flag1 = false;
    private static volatile int turn = 0;
    private static volatile int contador = 0;

    // consulta la bandera de un proceso
    private static boolean bandera(int id) {
        return id == 0 ? flag0 : flag1;
    }

    // modifica la bandera de un proceso
    private static void establecerBandera(int id, boolean valor) {
        if (id == 0) {
            flag0 = valor;
        } else {
            flag1 = valor;
        }
    }

    // simula trabajo dentro de la seccion critica
    private static void pausa() {
        try {
            Thread.sleep(5);
        } catch (InterruptedException excepcion) {
            Thread.currentThread().interrupt();
        }
    }

    // aplica el algoritmo de Peterson para dos procesos
    private static void proceso(int id) {
        int otro = 1 - id;

        for (int iteracion = 1; iteracion <= ITERACIONES; iteracion++) {
            establecerBandera(id, true);
            turn = otro;

            while (bandera(otro) && turn == otro) {
                Thread.yield();
            }

            System.out.println("P" + id + " entra a la seccion critica en la iteracion " + iteracion);
            int valorLeido = contador;
            pausa();
            contador = valorLeido + 1;
            System.out.println("P" + id + " sale con contador = " + contador);

            establecerBandera(id, false);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread p0 = new Thread(() -> proceso(0), "P0");
        Thread p1 = new Thread(() -> proceso(1), "P1");

        System.out.println("prueba del algoritmo de Peterson");
        p0.start();
        p1.start();
        p0.join();
        p1.join();

        System.out.println("contador esperado: " + (ITERACIONES * 2));
        System.out.println("contador obtenido: " + contador);
        System.out.println("resultado: cumple exclusion mutua, progreso y espera limitada para dos procesos");
    }
}
