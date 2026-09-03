import java.util.Scanner;

// calcula el promedio y el estado del estudiante
public class Ejercicio03PromedioAprobacion {
    // inicia el programa
    public static void main(String[] args) {
        // crea el lector de datos
        Scanner scanner = new Scanner(System.in);

        // solicita la primera calificacion
        System.out.print("Primera calificacion (0 a 10): ");
        if (!scanner.hasNextDouble()) {
            System.out.println("Error: introduce una calificacion numerica.");
            scanner.close();
            return;
        }
        // guarda la primera calificacion
        double calificacion1 = scanner.nextDouble();

        // solicita la segunda calificacion
        System.out.print("Segunda calificacion (0 a 10): ");
        if (!scanner.hasNextDouble()) {
            System.out.println("Error: introduce una calificacion numerica.");
            scanner.close();
            return;
        }
        // guarda la segunda calificacion
        double calificacion2 = scanner.nextDouble();

        // solicita la tercera calificacion
        System.out.print("Tercera calificacion (0 a 10): ");
        if (!scanner.hasNextDouble()) {
            System.out.println("Error: introduce una calificacion numerica.");
            scanner.close();
            return;
        }
        // guarda la tercera calificacion
        double calificacion3 = scanner.nextDouble();

        // valida el rango de las calificaciones
        if (calificacion1 < 0 || calificacion1 > 10
                || calificacion2 < 0 || calificacion2 > 10
                || calificacion3 < 0 || calificacion3 > 10) {
            System.out.println("Error: las calificaciones deben estar entre 0 y 10.");
            scanner.close();
            return;
        }

        // calcula el promedio y determina el resultado
        double promedio = (calificacion1 + calificacion2 + calificacion3) / 3;
        String resultado = promedio >= 6 ? "Aprobado" : "Reprobado";

        // muestra el promedio y el estado
        System.out.printf("Promedio: %.2f%n", promedio);
        System.out.println("Resultado: " + resultado);
        // cierra el lector de datos
        scanner.close();
    }
}
