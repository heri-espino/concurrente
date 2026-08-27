// No se requieren imports: Thread y System pertenecen a java.lang.
public class MultipleThreadsExample {
    // Metodo auxiliar usado por los tres hilos para hacer visible la concurrencia.
    private static void pausaCorta() {
        try {
            // Una pausa corta permite observar la ejecucion intercalada.
            Thread.sleep(100);
        } catch (InterruptedException exception) {
            // Se conserva la interrupcion del hilo para no perder esa senal.
            Thread.currentThread().interrupt();
        }
    }

    // main() crea, inicia y espera los tres hilos de la practica.
    public static void main(String[] args) throws InterruptedException {
        // Hilo 1: imprime los numeros del 1 al 5.
        Thread hilo1 = new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                // currentThread() obtiene el hilo actual y getName() su nombre.
                System.out.println(Thread.currentThread().getName() + ": " + i);
                pausaCorta();
            }
        });

        // Hilo 2: imprime las letras de A hasta E.
        Thread hilo2 = new Thread(() -> {
            for (char letra = 'A'; letra <= 'E'; letra++) {
                System.out.println(Thread.currentThread().getName() + ": " + letra);
                pausaCorta();
            }
        });

        // Hilo 3: imprime cinco mensajes de saludo.
        Thread hilo3 = new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                System.out.println(Thread.currentThread().getName() + ": saludo " + i);
                pausaCorta();
            }
        });

        // setName() asigna un nombre para identificar cada hilo en la salida.
        hilo1.setName("Hilo 1 - numeros");
        hilo2.setName("Hilo 2 - letras");
        hilo3.setName("Hilo 3 - saludos");

        // Los tres hilos se inician antes de esperar a cualquiera de ellos.
        // El orden de estas llamadas no garantiza el orden de ejecucion.
        hilo1.start();
        hilo2.start();
        hilo3.start();

        // join() garantiza que el hilo principal espere a cada hilo.
        // Cuando las tres llamadas terminan, todos los hilos finalizaron.
        // En este programa, el hilos principal es el que ejecuta main().
        hilo1.join();
        hilo2.join();
        hilo3.join();
        System.out.println("Los tres hilos terminaron.");
    }
}
