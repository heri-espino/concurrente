import java.util.Scanner;

// muestra la tabla de multiplicar de un numero
public class Ejercicio06TablaMultiplicar {
    // inicia el programa
    public static void main(String[] args) {
        // crea el lector de datos
        Scanner scanner = new Scanner(System.in);

        // solicita y valida el numero
        System.out.print("Numero entero: ");
        if (!scanner.hasNextInt()) {
            System.out.println("Error: introduce un numero entero.");
            scanner.close();
            return;
        }

        // lee el numero seleccionado
        int numero = scanner.nextInt();
        // muestra el titulo de la tabla
        System.out.println("Tabla de multiplicar del " + numero + ":");
        // recorre los multiplicadores del uno al diez
        for (int multiplicador = 1; multiplicador <= 10; multiplicador++) {
            // muestra cada operacion
            System.out.printf("%d x %d = %d%n",
                    numero, multiplicador, numero * multiplicador);
        }
        // cierra el lector de datos
        scanner.close();
    }
}
