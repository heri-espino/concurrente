import java.util.Scanner;

// realiza operaciones aritmeticas basicas
public class Ejercicio05CalculadoraBasica {
    // inicia la calculadora
    public static void main(String[] args) {
        // crea el lector de datos
        Scanner scanner = new Scanner(System.in);

        // solicita y valida el primer numero
        System.out.print("Primer numero: ");
        if (!scanner.hasNextDouble()) {
            System.out.println("Error: introduce un numero valido.");
            scanner.close();
            return;
        }
        // guarda el primer numero
        double primerNumero = scanner.nextDouble();

        // solicita y valida el segundo numero
        System.out.print("Segundo numero: ");
        if (!scanner.hasNextDouble()) {
            System.out.println("Error: introduce un numero valido.");
            scanner.close();
            return;
        }
        // guarda el segundo numero
        double segundoNumero = scanner.nextDouble();

        // muestra las operaciones disponibles
        System.out.println("1. Suma");
        System.out.println("2. Resta");
        System.out.println("3. Multiplicacion");
        System.out.println("4. Division");
        // solicita y valida la opcion
        System.out.print("Selecciona una operacion: ");
        if (!scanner.hasNextInt()) {
            System.out.println("Error: selecciona una opcion del 1 al 4.");
            scanner.close();
            return;
        }
        // guarda la operacion seleccionada
        int opcion = scanner.nextInt();

        // calcula el resultado segun la opcion
        double resultado;
        switch (opcion) {
            case 1:
                resultado = primerNumero + segundoNumero;
                break;
            case 2:
                resultado = primerNumero - segundoNumero;
                break;
            case 3:
                resultado = primerNumero * segundoNumero;
                break;
            case 4:
                // evita la division entre cero
                if (segundoNumero == 0) {
                    System.out.println("Error: no es posible dividir entre cero.");
                    scanner.close();
                    return;
                }
                resultado = primerNumero / segundoNumero;
                break;
            default:
                System.out.println("Error: opcion no valida.");
                scanner.close();
                return;
        }

        // muestra el resultado de la operacion
        System.out.printf("Resultado: %.2f%n", resultado);
        // cierra el lector de datos
        scanner.close();
    }
}
