// Importa la clase que permite leer datos desde la consola.
import java.util.Scanner;

// Declara la clase principal del ejercicio 1.
public class Ejercicio01ConversionTemperatura {
    // Declara el punto de entrada del programa.
    public static void main(String[] args) {
        // Crea un lector asociado con la entrada estandar.
        Scanner scanner = new Scanner(System.in);

        // Solicita la temperatura en grados Celsius.
        System.out.print("Temperatura en grados Celsius: ");
        // Comprueba que la entrada tenga formato decimal.
        if (!scanner.hasNextDouble()) {
            // Informa que la entrada no es valida.
            System.out.println("Error: introduce un valor numerico.");
            // Libera el lector antes de terminar.
            scanner.close();
            // Finaliza el metodo porque no hay un dato que procesar.
            return;
        }

        // Lee y guarda la temperatura Celsius.
        double celsius = scanner.nextDouble();
        // Aplica la formula de conversion a Fahrenheit.
        double fahrenheit = (celsius * 1.8) + 32;

        // Muestra ambas temperaturas con dos decimales.
        System.out.printf("%.2f grados Celsius equivalen a %.2f grados Fahrenheit.%n",
                celsius, fahrenheit);
        // Cierra el lector de la consola.
        scanner.close();
    }
}
