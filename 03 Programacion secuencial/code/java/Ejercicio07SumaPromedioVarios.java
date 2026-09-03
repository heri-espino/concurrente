import java.util.Scanner;

// calcula estadisticas de varios numeros
public class Ejercicio07SumaPromedioVarios {
    // inicia el programa
    public static void main(String[] args) {
        // crea el lector de datos
        Scanner scanner = new Scanner(System.in);

        // solicita y valida la cantidad de numeros
        System.out.print("Cantidad de numeros: ");
        if (!scanner.hasNextInt()) {
            System.out.println("Error: introduce una cantidad entera.");
            scanner.close();
            return;
        }
        // guarda la cantidad y verifica que sea positiva
        int cantidad = scanner.nextInt();
        if (cantidad <= 0) {
            System.out.println("Error: la cantidad debe ser mayor que cero.");
            scanner.close();
            return;
        }

        double suma = 0;
        double mayor = 0;
        double menor = 0;
        // recorre todos los numeros recibidos
        for (int indice = 1; indice <= cantidad; indice++) {
            // solicita y valida el numero actual
            System.out.print("Numero " + indice + ": ");
            if (!scanner.hasNextDouble()) {
                System.out.println("Error: introduce un numero valido.");
                scanner.close();
                return;
            }
            // actualiza la suma y los extremos
            double numero = scanner.nextDouble();
            suma += numero;
            if (indice == 1 || numero > mayor) {
                mayor = numero;
            }
            if (indice == 1 || numero < menor) {
                menor = numero;
            }
        }

        // calcula el promedio
        double promedio = suma / cantidad;
        // muestra los resultados
        System.out.printf("Suma: %.2f%n", suma);
        System.out.printf("Promedio: %.2f%n", promedio);
        System.out.printf("Numero mayor: %.2f%n", mayor);
        System.out.printf("Numero menor: %.2f%n", menor);
        // cierra el lector de datos
        scanner.close();
    }
}
