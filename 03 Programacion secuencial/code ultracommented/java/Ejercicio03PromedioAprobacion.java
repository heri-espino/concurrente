// Importa la clase que lee datos desde la consola.
import java.util.Scanner;

// Declara la clase principal del ejercicio 3.
public class Ejercicio03PromedioAprobacion {
    // Declara el metodo de inicio del programa.
    public static void main(String[] args) {
        // Crea el lector de la entrada estandar.
        Scanner scanner = new Scanner(System.in);

        // Solicita la primera calificacion.
        System.out.print("Primera calificacion (0 a 10): ");
        // Valida el tipo de dato de la primera calificacion.
        if (!scanner.hasNextDouble()) {
            // Informa que la entrada debe ser numerica.
            System.out.println("Error: introduce una calificacion numerica.");
            // Cierra el lector.
            scanner.close();
            // Detiene el programa por falta de datos validos.
            return;
        }
        // Lee la primera calificacion.
        double calificacion1 = scanner.nextDouble();

        // Solicita la segunda calificacion.
        System.out.print("Segunda calificacion (0 a 10): ");
        // Valida el tipo de dato de la segunda calificacion.
        if (!scanner.hasNextDouble()) {
            // Informa que la entrada debe ser numerica.
            System.out.println("Error: introduce una calificacion numerica.");
            // Cierra el lector.
            scanner.close();
            // Detiene el programa por falta de datos validos.
            return;
        }
        // Lee la segunda calificacion.
        double calificacion2 = scanner.nextDouble();

        // Solicita la tercera calificacion.
        System.out.print("Tercera calificacion (0 a 10): ");
        // Valida el tipo de dato de la tercera calificacion.
        if (!scanner.hasNextDouble()) {
            // Informa que la entrada debe ser numerica.
            System.out.println("Error: introduce una calificacion numerica.");
            // Cierra el lector.
            scanner.close();
            // Detiene el programa por falta de datos validos.
            return;
        }
        // Lee la tercera calificacion.
        double calificacion3 = scanner.nextDouble();

        // Comprueba que todas las calificaciones esten en el intervalo permitido.
        if (calificacion1 < 0 || calificacion1 > 10
                || calificacion2 < 0 || calificacion2 > 10
                || calificacion3 < 0 || calificacion3 > 10) {
            // Informa que alguna calificacion esta fuera del intervalo.
            System.out.println("Error: las calificaciones deben estar entre 0 y 10.");
            // Cierra el lector.
            scanner.close();
            // Detiene el programa por datos invalidos.
            return;
        }

        // Calcula el promedio de las tres calificaciones.
        double promedio = (calificacion1 + calificacion2 + calificacion3) / 3;
        // Selecciona el estado de acuerdo con el promedio minimo de seis.
        String resultado = promedio >= 6 ? "Aprobado" : "Reprobado";

        // Muestra el promedio calculado.
        System.out.printf("Promedio: %.2f%n", promedio);
        // Muestra si el estudiante aprobo o reprobo.
        System.out.println("Resultado: " + resultado);
        // Cierra el lector.
        scanner.close();
    }
}
