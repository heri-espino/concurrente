// No se requieren imports: Thread y System pertenecen a java.lang.
public class ThreadExample {
    // Metodo auxiliar para pausar el hilo que lo llama durante 500 ms.
    private static void pausa() {
        try {
            // Thread.sleep() permite observar la intercalacion de mensajes.
            Thread.sleep(500);
        } catch (InterruptedException e) {
            // Se restaura la marca de interrupcion para no ignorar la senal.
            Thread.currentThread().interrupt();
        }
    }

    // main() se ejecuta inicialmente en el hilo principal.
    public static void main(String[] args) {
        // new Thread() crea un hilo hijo y la lambda define su tarea.
        Thread hilo = new Thread(() -> {
            // El hilo secundario imprime cinco mensajes.
            for (int i = 1; i <= 5; i++) {
                System.out.println("Hilo secundario");
                pausa();
            }
        });

        // start() solicita al planificador iniciar el hilo secundario.
        hilo.start();

        // Mientras el hijo se ejecuta, el hilo principal imprime cinco mensajes.
        for (int i = 1; i <= 5; i++) {
            System.out.println("Hilo principal");
            pausa();
        }

        try {
            // join() bloquea el hilo principal hasta que termina el hijo.
            hilo.join();
        } catch (InterruptedException e) {
            // Se conserva la interrupcion si el hilo principal es interrumpido.
            Thread.currentThread().interrupt();
        }
    }
}
