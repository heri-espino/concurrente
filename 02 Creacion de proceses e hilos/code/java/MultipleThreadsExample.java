public class MultipleThreadsExample {
    private static void pausaCorta() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException exception) {
            Thread.currentThread().interrupt();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        // Hilo 1: numeros.
        Thread hilo1 = new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                System.out.println(Thread.currentThread().getName() + ": " + i);
                pausaCorta();
            }
        });

        // Hilo 2: letras.
        Thread hilo2 = new Thread(() -> {
            for (char letra = 'A'; letra <= 'E'; letra++) {
                System.out.println(Thread.currentThread().getName() + ": " + letra);
                pausaCorta();
            }
        });

        // Hilo 3: saludos.
        Thread hilo3 = new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                System.out.println(Thread.currentThread().getName() + ": saludo " + i);
                pausaCorta();
            }
        });

        hilo1.setName("Hilo 1 - numeros");
        hilo2.setName("Hilo 2 - letras");
        hilo3.setName("Hilo 3 - saludos");

        // Iniciar los tres hilos.
        hilo1.start();
        hilo2.start();
        hilo3.start();

        // Esperar a que todos terminen.
        hilo1.join();
        hilo2.join();
        hilo3.join();
        System.out.println("Los tres hilos terminaron.");
    }
}
