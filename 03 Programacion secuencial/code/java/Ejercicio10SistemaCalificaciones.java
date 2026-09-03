import java.util.Scanner;

// administra las calificaciones de varios estudiantes
public class Ejercicio10SistemaCalificaciones {
    // calcula el promedio de tres calificaciones
    public static double calcularPromedio(double c1, double c2, double c3) {
        return (c1 + c2 + c3) / 3;
    }

    // determina si un promedio indica aprobacion
    public static boolean estaAprobado(double promedio) {
        return promedio >= 6;
    }

    // lee una calificacion valida
    private static double leerCalificacion(Scanner scanner, String mensaje) {
        // repite la lectura hasta obtener un valor valido
        while (true) {
            System.out.print(mensaje);
            if (scanner.hasNextDouble()) {
                double calificacion = scanner.nextDouble();
                if (calificacion >= 0 && calificacion <= 10) {
                    return calificacion;
                }
            } else {
                scanner.next();
            }
            System.out.println("Error: la calificacion debe estar entre 0 y 10.");
        }
    }

    // inicia el sistema de calificaciones
    public static void main(String[] args) {
        // crea el lector de datos
        Scanner scanner = new Scanner(System.in);
        // solicita y valida la cantidad de estudiantes
        System.out.print("Cantidad de estudiantes: ");
        if (!scanner.hasNextInt()) {
            System.out.println("Error: introduce una cantidad entera.");
            scanner.close();
            return;
        }

        // guarda la cantidad de estudiantes
        int cantidadEstudiantes = scanner.nextInt();
        if (cantidadEstudiantes <= 0) {
            System.out.println("Error: la cantidad debe ser mayor que cero.");
            scanner.close();
            return;
        }

        double sumaPromedios = 0;
        double promedioMasAlto = 0;
        double promedioMasBajo = 0;
        int aprobados = 0;

        // procesa a cada estudiante
        for (int estudiante = 1; estudiante <= cantidadEstudiantes; estudiante++) {
            // identifica al estudiante actual
            System.out.println("Estudiante " + estudiante + ":");
            // lee sus tres calificaciones
            double calificacion1 = leerCalificacion(scanner, "  Calificacion 1: ");
            double calificacion2 = leerCalificacion(scanner, "  Calificacion 2: ");
            double calificacion3 = leerCalificacion(scanner, "  Calificacion 3: ");
            // calcula y registra el promedio individual
            double promedio = calcularPromedio(calificacion1, calificacion2, calificacion3);

            sumaPromedios += promedio;
            if (estudiante == 1 || promedio > promedioMasAlto) {
                promedioMasAlto = promedio;
            }
            if (estudiante == 1 || promedio < promedioMasBajo) {
                promedioMasBajo = promedio;
            }
            if (estaAprobado(promedio)) {
                aprobados++;
            }

            System.out.printf("  Promedio individual: %.2f%n", promedio);
            System.out.println("  Estado: "
                    + (estaAprobado(promedio) ? "Aprobado" : "Reprobado"));
        }

        // calcula los resumenes del grupo
        int reprobados = cantidadEstudiantes - aprobados;
        double promedioGeneral = sumaPromedios / cantidadEstudiantes;
        double porcentajeAprobacion = (double) aprobados / cantidadEstudiantes * 100;

        // muestra los resultados finales
        System.out.printf("Promedio general: %.2f%n", promedioGeneral);
        System.out.printf("Promedio mas alto: %.2f%n", promedioMasAlto);
        System.out.printf("Promedio mas bajo: %.2f%n", promedioMasBajo);
        System.out.println("Aprobados: " + aprobados);
        System.out.println("Reprobados: " + reprobados);
        System.out.printf("Porcentaje de aprobacion: %.2f%%%n", porcentajeAprobacion);
        // cierra el lector de datos
        scanner.close();
    }
}
