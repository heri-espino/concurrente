public class ThreadExample {
    private static void pausa() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public static void main(String[] args) {
        // Crear el hilo secundario.
        Thread hilo = new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                System.out.println("Hilo secundario");
                pausa();
            }
        });

        hilo.start();

        for (int i = 1; i <= 5; i++) {
            System.out.println("Hilo principal");
            pausa();
        }

        try {
            // Esperar a que termine el hilo.
            hilo.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
