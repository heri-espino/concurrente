// Importa la clase que lee datos desde la consola.
import java.util.Scanner;

// Declara la clase principal del ejercicio 6.
public class Ejercicio06TablaMultiplicar {
    // Declara el metodo de inicio del programa.
    public static void main(String[] args) {
        // Crea el lector de la entrada estandar.
        Scanner scanner = new Scanner(System.in);

        // Solicita el numero cuya tabla se va a mostrar.
        System.out.print("Numero entero: ");
        // Comprueba que la entrada sea un numero entero.
        if (!scanner.hasNextInt()) {
            // Informa que la entrada no es valida.
            System.out.println("Error: introduce un numero entero.");
            // Cierra el lector.
            scanner.close();
            // Termina el programa por falta de un dato valido.
            return;
        }

        // Lee y guarda el numero seleccionado.
        int numero = scanner.nextInt();
        // Identifica la tabla que se va a imprimir.
        System.out.println("Tabla de multiplicar del " + numero + ":");
        // Repite la operacion para los multiplicadores del 1 al 10.
        for (int multiplicador = 1; multiplicador <= 10; multiplicador++) {
            // Muestra cada multiplicacion y su resultado.
            System.out.printf("%d x %d = %d%n",
                    numero, multiplicador, numero * multiplicador);
        }
        // Cierra el lector de la consola.
        scanner.close();
    }
}
