// Importa la clase para leer datos desde la consola.
import java.util.Scanner;

// Declara la clase principal del ejercicio 2.
public class Ejercicio02Rectangulo {
    // Declara el metodo que inicia la ejecucion.
    public static void main(String[] args) {
        // Crea el objeto que lee la entrada estandar.
        Scanner scanner = new Scanner(System.in);

        // Solicita la base del rectangulo.
        System.out.print("Base del rectangulo: ");
        // Verifica que la base sea un numero decimal.
        if (!scanner.hasNextDouble()) {
            // Muestra un mensaje de error para una entrada invalida.
            System.out.println("Error: introduce una base numerica.");
            // Cierra el lector.
            scanner.close();
            // Termina porque no se puede calcular el rectangulo.
            return;
        }
        // Guarda la base introducida.
        double base = scanner.nextDouble();

        // Solicita la altura del rectangulo.
        System.out.print("Altura del rectangulo: ");
        // Verifica que la altura sea un numero decimal.
        if (!scanner.hasNextDouble()) {
            // Muestra un mensaje de error para una entrada invalida.
            System.out.println("Error: introduce una altura numerica.");
            // Cierra el lector.
            scanner.close();
            // Termina porque no se puede calcular el rectangulo.
            return;
        }
        // Guarda la altura introducida.
        double altura = scanner.nextDouble();

        // Valida que las dimensiones no sean negativas.
        if (base < 0 || altura < 0) {
            // Informa que las dimensiones no son validas.
            System.out.println("Error: la base y la altura no pueden ser negativas.");
            // Cierra el lector.
            scanner.close();
            // Termina el programa sin calcular resultados invalidos.
            return;
        }

        // Calcula el area usando base por altura.
        double area = base * altura;
        // Calcula el perimetro usando dos por la suma de dimensiones.
        double perimetro = 2 * (base + altura);

        // Muestra el area calculada.
        System.out.printf("Area: %.2f%n", area);
        // Muestra el perimetro calculado.
        System.out.printf("Perimetro: %.2f%n", perimetro);
        // Cierra el lector de la consola.
        scanner.close();
    }
}
