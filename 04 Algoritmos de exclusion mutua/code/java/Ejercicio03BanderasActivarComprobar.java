import java.util.concurrent.CountDownLatch;

public class Ejercicio03BanderasActivarComprobar {
    private static volatile boolean flag0 = false;
    private static volatile boolean flag1 = false;
    private static volatile int contador = 0;
    private static final CountDownLatch banderasActivadas = new CountDownLatch(2);

    // consulta la bandera del otro proceso
    private static boolean banderaDelOtro(int id) {
        return id == 0 ? flag1 : flag0;
    }

    // coloca la bandera y despues comprueba la del otro proceso
    private static void proceso(int id) {
        if (id == 0) {
            flag0 = true;
        } else {
            flag1 = true;
        }
        banderasActivadas.countDown();

        try {
            banderasActivadas.await();
        } catch (InterruptedException excepcion) {
            Thread.currentThread().interrupt();
            return;
        }

        while (banderaDelOtro(id) && !Thread.currentThread().isInterrupted()) {
            Thread.yield();
        }

        if (banderaDelOtro(id)) {
            return;
        }

        System.out.println("P" + id + " entra a la seccion critica");
        contador++;
        System.out.println("P" + id + " sale con contador = " + contador);
    }

    public static void main(String[] args) throws InterruptedException {
        Thread p0 = new Thread(() -> proceso(0), "P0");
        Thread p1 = new Thread(() -> proceso(1), "P1");

        System.out.println("prueba de banderas activar y comprobar");
        p0.start();
        p1.start();
        p0.join(200);
        p1.join(200);

        if (p0.isAlive()) {
            p0.interrupt();
        }
        if (p1.isAlive()) {
            p1.interrupt();
        }
        p0.join();
        p1.join();

        System.out.println("contador obtenido: " + contador);
        System.out.println("resultado: ambos procesos quedan esperando cuando las dos banderas valen true");
    }
}
