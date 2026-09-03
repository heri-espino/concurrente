// Importa la clase para leer las calificaciones desde la consola.
import java.util.Scanner;

// Declara la clase principal del ejercicio 10.
public class Ejercicio10SistemaCalificaciones {
    // Declara el metodo solicitado para calcular un promedio individual.
    public static double calcularPromedio(double c1, double c2, double c3) {
        // Devuelve la media aritmetica de las tres calificaciones.
        return (c1 + c2 + c3) / 3;
    }

    // Declara el metodo solicitado para determinar la aprobacion.
    public static boolean estaAprobado(double promedio) {
        // Devuelve verdadero cuando el promedio es al menos seis.
        return promedio >= 6;
    }

    // Declara un metodo auxiliar para leer y validar una calificacion.
    private static double leerCalificacion(Scanner scanner, String mensaje) {
        // Repite la lectura hasta recibir una calificacion valida.
        while (true) {
            // Muestra el mensaje de la calificacion actual.
            System.out.print(mensaje);
            // Comprueba si la entrada tiene formato decimal.
            if (scanner.hasNextDouble()) {
                // Lee la calificacion introducida.
                double calificacion = scanner.nextDouble();
                // Comprueba que la calificacion este entre cero y diez.
                if (calificacion >= 0 && calificacion <= 10) {
                    // Devuelve la calificacion valida.
                    return calificacion;
                }
            } else {
                // Descarta el token que no era numerico.
                scanner.next();
            }
            // Informa que se debe introducir una calificacion valida.
            System.out.println("Error: la calificacion debe estar entre 0 y 10.");
        }
    }

    // Declara el punto de entrada del sistema de calificaciones.
    public static void main(String[] args) {
        // Crea el lector de la entrada estandar.
        Scanner scanner = new Scanner(System.in);
        // Solicita la cantidad de estudiantes.
        System.out.print("Cantidad de estudiantes: ");
        // Comprueba que la cantidad sea entera.
        if (!scanner.hasNextInt()) {
            // Informa que la cantidad no es valida.
            System.out.println("Error: introduce una cantidad entera.");
            // Cierra el lector.
            scanner.close();
            // Termina el programa.
            return;
        }

        // Lee la cantidad de estudiantes.
        int cantidadEstudiantes = scanner.nextInt();
        // Valida que exista al menos un estudiante.
        if (cantidadEstudiantes <= 0) {
            // Informa que la cantidad debe ser positiva.
            System.out.println("Error: la cantidad debe ser mayor que cero.");
            // Cierra el lector.
            scanner.close();
            // Termina para evitar calculos sin estudiantes.
            return;
        }

        // Inicializa la suma de promedios individuales.
        double sumaPromedios = 0;
        // Reserva la variable para el promedio mas alto.
        double promedioMasAlto = 0;
        // Reserva la variable para el promedio mas bajo.
        double promedioMasBajo = 0;
        // Inicializa el contador de estudiantes aprobados.
        int aprobados = 0;

        // Recorre a todos los estudiantes.
        for (int estudiante = 1; estudiante <= cantidadEstudiantes; estudiante++) {
            // Identifica al estudiante que se esta registrando.
            System.out.println("Estudiante " + estudiante + ":");
            // Lee la primera calificacion del estudiante.
            double calificacion1 = leerCalificacion(scanner, "  Calificacion 1: ");
            // Lee la segunda calificacion del estudiante.
            double calificacion2 = leerCalificacion(scanner, "  Calificacion 2: ");
            // Lee la tercera calificacion del estudiante.
            double calificacion3 = leerCalificacion(scanner, "  Calificacion 3: ");
            // Calcula el promedio individual usando el metodo solicitado.
            double promedio = calcularPromedio(calificacion1, calificacion2, calificacion3);

            // Acumula el promedio para calcular el promedio general.
            sumaPromedios += promedio;
            // Inicializa o actualiza el promedio maximo.
            if (estudiante == 1 || promedio > promedioMasAlto) {
                // Guarda el promedio mas alto encontrado.
                promedioMasAlto = promedio;
            }
            // Inicializa o actualiza el promedio minimo.
            if (estudiante == 1 || promedio < promedioMasBajo) {
                // Guarda el promedio mas bajo encontrado.
                promedioMasBajo = promedio;
            }
            // Comprueba el estado del estudiante.
            if (estaAprobado(promedio)) {
                // Cuenta al estudiante como aprobado.
                aprobados++;
            }

            // Muestra el promedio individual.
            System.out.printf("  Promedio individual: %.2f%n", promedio);
            // Muestra el estado de acuerdo con el promedio.
            System.out.println("  Estado: "
                    + (estaAprobado(promedio) ? "Aprobado" : "Reprobado"));
        }

        // Calcula los estudiantes reprobados por diferencia.
        int reprobados = cantidadEstudiantes - aprobados;
        // Calcula el promedio general del grupo.
        double promedioGeneral = sumaPromedios / cantidadEstudiantes;
        // Calcula el porcentaje de estudiantes aprobados.
        double porcentajeAprobacion = (double) aprobados / cantidadEstudiantes * 100;

        // Muestra el promedio general.
        System.out.printf("Promedio general: %.2f%n", promedioGeneral);
        // Muestra el promedio individual mas alto.
        System.out.printf("Promedio mas alto: %.2f%n", promedioMasAlto);
        // Muestra el promedio individual mas bajo.
        System.out.printf("Promedio mas bajo: %.2f%n", promedioMasBajo);
        // Muestra la cantidad de aprobados.
        System.out.println("Aprobados: " + aprobados);
        // Muestra la cantidad de reprobados.
        System.out.println("Reprobados: " + reprobados);
        // Muestra el porcentaje de aprobacion.
        System.out.printf("Porcentaje de aprobacion: %.2f%%%n", porcentajeAprobacion);
        // Cierra el lector de la consola.
        scanner.close();
    }
}
