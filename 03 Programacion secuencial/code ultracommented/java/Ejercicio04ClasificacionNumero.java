// Importa la clase que permite leer desde la consola.
import java.util.Scanner;

// Declara la clase principal del ejercicio 4.
public class Ejercicio04ClasificacionNumero {
    // Declara el punto de entrada del programa.
    public static void main(String[] args) {
        // Crea el lector para la entrada estandar.
        Scanner scanner = new Scanner(System.in);

        // Solicita un numero entero.
        System.out.print("Numero entero: ");
        // Comprueba que el usuario haya escrito un entero.
        if (!scanner.hasNextInt()) {
            // Muestra un mensaje de error.
            System.out.println("Error: introduce un numero entero.");
            // Cierra el lector.
            scanner.close();
            // Termina porque no existe un entero que clasificar.
            return;
        }

        // Lee el entero introducido.
        int numero = scanner.nextInt();
        // Declara la variable donde se guardara el signo.
        String signo;
        // Comprueba si el numero es mayor que cero.
        if (numero > 0) {
            // Guarda la clasificacion positiva.
            signo = "positivo";
        // Comprueba si el numero es menor que cero.
        } else if (numero < 0) {
            // Guarda la clasificacion negativa.
            signo = "negativo";
        // Si no es mayor ni menor, el numero es cero.
        } else {
            // Guarda la clasificacion de cero.
            signo = "cero";
        }

        // Usa el residuo para determinar la paridad.
        String paridad = numero % 2 == 0 ? "par" : "impar";
        // Elige la conjuncion correcta antes de la palabra impar.
        String conjuncion = paridad.equals("impar") ? "e" : "y";
        // Muestra ambas clasificaciones.
        System.out.println("El numero es " + signo + " " + conjuncion + " " + paridad + ".");
        // Cierra el lector de la consola.
        scanner.close();
    }
}
