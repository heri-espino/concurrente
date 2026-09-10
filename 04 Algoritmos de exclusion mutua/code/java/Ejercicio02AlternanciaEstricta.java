public class Ejercicio02AlternanciaEstricta {
    private static final int ITERACIONES = 3;
    private static volatile int turn = 0;
    private static volatile int contador = 0;

    // simula trabajo dentro de la seccion critica
    private static void pausa() {
        try {
            Thread.sleep(5);
        } catch (InterruptedException excepcion) {
            Thread.currentThread().interrupt();
        }
    }

    // aplica la alternancia estricta con turn
    private static void proceso(int id) {
        for (int iteracion = 1; iteracion <= ITERACIONES; iteracion++) {
            while (turn != id) {
                Thread.yield();
            }

            System.out.println("P" + id + " entra con turn = " + turn);
            int valorLeido = contador;
            pausa();
            contador = valorLeido + 1;
            System.out.println("P" + id + " sale con contador = " + contador);
            turn = 1 - id;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread p0 = new Thread(() -> proceso(0), "P0");
        Thread p1 = new Thread(() -> proceso(1), "P1");

        System.out.println("prueba de alternancia estricta");
        p0.start();
        p1.start();
        p0.join();
        p1.join();

        System.out.println("contador esperado: " + (ITERACIONES * 2));
        System.out.println("contador obtenido: " + contador);
        System.out.println("resultado: conserva exclusion mutua pero puede bloquear por falta de progreso");
    }
}
