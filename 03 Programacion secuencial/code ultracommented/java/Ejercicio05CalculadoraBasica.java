// Importa la clase para leer datos desde la consola.
import java.util.Scanner;

// Declara la clase principal del ejercicio 5.
public class Ejercicio05CalculadoraBasica {
    // Declara el metodo que inicia la calculadora.
    public static void main(String[] args) {
        // Crea el lector de la entrada estandar.
        Scanner scanner = new Scanner(System.in);

        // Solicita el primer numero.
        System.out.print("Primer numero: ");
        // Valida que el primer dato sea decimal.
        if (!scanner.hasNextDouble()) {
            // Muestra un mensaje de entrada invalida.
            System.out.println("Error: introduce un numero valido.");
            // Cierra el lector.
            scanner.close();
            // Termina el programa.
            return;
        }
        // Lee el primer numero.
        double primerNumero = scanner.nextDouble();

        // Solicita el segundo numero.
        System.out.print("Segundo numero: ");
        // Valida que el segundo dato sea decimal.
        if (!scanner.hasNextDouble()) {
            // Muestra un mensaje de entrada invalida.
            System.out.println("Error: introduce un numero valido.");
            // Cierra el lector.
            scanner.close();
            // Termina el programa.
            return;
        }
        // Lee el segundo numero.
        double segundoNumero = scanner.nextDouble();

        // Muestra la opcion de suma.
        System.out.println("1. Suma");
        // Muestra la opcion de resta.
        System.out.println("2. Resta");
        // Muestra la opcion de multiplicacion.
        System.out.println("3. Multiplicacion");
        // Muestra la opcion de division.
        System.out.println("4. Division");
        // Solicita la operacion.
        System.out.print("Selecciona una operacion: ");
        // Valida que la opcion sea entera.
        if (!scanner.hasNextInt()) {
            // Informa que la opcion no es valida.
            System.out.println("Error: selecciona una opcion del 1 al 4.");
            // Cierra el lector.
            scanner.close();
            // Termina el programa.
            return;
        }
        // Lee la opcion seleccionada.
        int opcion = scanner.nextInt();

        // Declara la variable que guardara el resultado.
        double resultado;
        // Selecciona la operacion mediante una estructura switch.
        switch (opcion) {
            // Procesa la suma.
            case 1:
                // Calcula la suma.
                resultado = primerNumero + segundoNumero;
                // Termina este caso.
                break;
            // Procesa la resta.
            case 2:
                // Calcula la resta.
                resultado = primerNumero - segundoNumero;
                // Termina este caso.
                break;
            // Procesa la multiplicacion.
            case 3:
                // Calcula la multiplicacion.
                resultado = primerNumero * segundoNumero;
                // Termina este caso.
                break;
            // Procesa la division.
            case 4:
                // Comprueba el divisor antes de dividir.
                if (segundoNumero == 0) {
                    // Informa que la division entre cero no es valida.
                    System.out.println("Error: no es posible dividir entre cero.");
                    // Cierra el lector.
                    scanner.close();
                    // Termina para evitar una operacion invalida.
                    return;
                }
                // Calcula la division.
                resultado = primerNumero / segundoNumero;
                // Termina este caso.
                break;
            // Atiende cualquier opcion fuera del intervalo.
            default:
                // Informa que la opcion no existe.
                System.out.println("Error: opcion no valida.");
                // Cierra el lector.
                scanner.close();
                // Termina el programa.
                return;
        }

        // Muestra el resultado de la operacion elegida.
        System.out.printf("Resultado: %.2f%n", resultado);
        // Cierra el lector de la consola.
        scanner.close();
    }
}
