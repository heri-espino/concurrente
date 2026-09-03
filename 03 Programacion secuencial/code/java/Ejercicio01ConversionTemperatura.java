import java.util.Scanner;

// procesa la conversion de temperatura
public class Ejercicio01ConversionTemperatura {
    // inicia el programa
    public static void main(String[] args) {
        // crea el lector de datos
        Scanner scanner = new Scanner(System.in);

        // solicita la temperatura en celsius
        System.out.print("Temperatura en grados Celsius: ");
        // valida que la entrada sea numerica
        if (!scanner.hasNextDouble()) {
            System.out.println("Error: introduce un valor numerico.");
            scanner.close();
            return;
        }

        // lee la temperatura y calcula su conversion
        double celsius = scanner.nextDouble();
        double fahrenheit = (celsius * 1.8) + 32;

        // muestra el resultado de la conversion
        System.out.printf("%.2f grados Celsius equivalen a %.2f grados Fahrenheit.%n",
                celsius, fahrenheit);
        // cierra el lector de datos
        scanner.close();
    }
}
