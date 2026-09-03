import java.util.Scanner;

// calcula el area y el perimetro de un rectangulo
public class Ejercicio02Rectangulo {
    // inicia el programa
    public static void main(String[] args) {
        // crea el lector de datos
        Scanner scanner = new Scanner(System.in);

        // solicita y valida la base
        System.out.print("Base del rectangulo: ");
        if (!scanner.hasNextDouble()) {
            System.out.println("Error: introduce una base numerica.");
            scanner.close();
            return;
        }
        // guarda la base
        double base = scanner.nextDouble();

        // solicita y valida la altura
        System.out.print("Altura del rectangulo: ");
        if (!scanner.hasNextDouble()) {
            System.out.println("Error: introduce una altura numerica.");
            scanner.close();
            return;
        }
        // guarda la altura
        double altura = scanner.nextDouble();

        // comprueba que las dimensiones sean validas
        if (base < 0 || altura < 0) {
            System.out.println("Error: la base y la altura no pueden ser negativas.");
            scanner.close();
            return;
        }

        // calcula el area y el perimetro
        double area = base * altura;
        double perimetro = 2 * (base + altura);

        // muestra los resultados
        System.out.printf("Area: %.2f%n", area);
        System.out.printf("Perimetro: %.2f%n", perimetro);
        // cierra el lector de datos
        scanner.close();
    }
}
