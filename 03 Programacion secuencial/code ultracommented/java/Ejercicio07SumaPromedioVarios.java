// Importa la clase para leer datos desde la consola.
import java.util.Scanner;

// Declara la clase principal del ejercicio 7.
public class Ejercicio07SumaPromedioVarios {
    // Declara el punto de entrada del programa.
    public static void main(String[] args) {
        // Crea el lector de la entrada estandar.
        Scanner scanner = new Scanner(System.in);

        // Solicita cuantas cantidades se procesaran.
        System.out.print("Cantidad de numeros: ");
        // Comprueba que la cantidad tenga formato entero.
        if (!scanner.hasNextInt()) {
            // Informa que la cantidad no es valida.
            System.out.println("Error: introduce una cantidad entera.");
            // Cierra el lector.
            scanner.close();
            // Termina el programa.
            return;
        }
        // Guarda la cantidad de numeros.
        int cantidad = scanner.nextInt();
        // Valida que exista al menos un numero para procesar.
        if (cantidad <= 0) {
            // Informa que la cantidad debe ser positiva.
            System.out.println("Error: la cantidad debe ser mayor que cero.");
            // Cierra el lector.
            scanner.close();
            // Termina para evitar una division entre cero.
            return;
        }

        // Inicializa el acumulador de la suma.
        double suma = 0;
        // Reserva la variable para el numero mayor.
        double mayor = 0;
        // Reserva la variable para el numero menor.
        double menor = 0;
        // Recorre la cantidad de datos solicitada.
        for (int indice = 1; indice <= cantidad; indice++) {
            // Solicita el numero actual.
            System.out.print("Numero " + indice + ": ");
            // Comprueba que el dato sea decimal.
            if (!scanner.hasNextDouble()) {
                // Informa que el dato no es valido.
                System.out.println("Error: introduce un numero valido.");
                // Cierra el lector.
                scanner.close();
                // Termina el programa por falta de un dato valido.
                return;
            }
            // Lee el numero actual.
            double numero = scanner.nextDouble();
            // Agrega el numero al acumulador.
            suma += numero;
            // Inicializa o actualiza el valor mayor.
            if (indice == 1 || numero > mayor) {
                // Guarda el numero mayor encontrado.
                mayor = numero;
            }
            // Inicializa o actualiza el valor menor.
            if (indice == 1 || numero < menor) {
                // Guarda el numero menor encontrado.
                menor = numero;
            }
        }

        // Calcula el promedio con la suma y la cantidad.
        double promedio = suma / cantidad;
        // Muestra la suma total.
        System.out.printf("Suma: %.2f%n", suma);
        // Muestra el promedio.
        System.out.printf("Promedio: %.2f%n", promedio);
        // Muestra el numero mayor.
        System.out.printf("Numero mayor: %.2f%n", mayor);
        // Muestra el numero menor.
        System.out.printf("Numero menor: %.2f%n", menor);
        // Cierra el lector.
        scanner.close();
    }
}
