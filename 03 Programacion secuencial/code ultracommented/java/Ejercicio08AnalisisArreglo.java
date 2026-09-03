// Importa la clase que permite leer desde la consola.
import java.util.Scanner;

// Declara la clase principal del ejercicio 8.
public class Ejercicio08AnalisisArreglo {
    // Declara el metodo principal.
    public static void main(String[] args) {
        // Crea el lector de la entrada estandar.
        Scanner scanner = new Scanner(System.in);

        // Solicita el tamano del arreglo.
        System.out.print("Tamano del arreglo: ");
        // Comprueba que el tamano sea entero.
        if (!scanner.hasNextInt()) {
            // Informa que el tamano no es valido.
            System.out.println("Error: introduce un tamano entero.");
            // Cierra el lector.
            scanner.close();
            // Termina el programa.
            return;
        }
        // Guarda el tamano del arreglo.
        int tamano = scanner.nextInt();
        // Valida que el arreglo tenga al menos una posicion.
        if (tamano <= 0) {
            // Informa que el tamano debe ser positivo.
            System.out.println("Error: el tamano debe ser mayor que cero.");
            // Cierra el lector.
            scanner.close();
            // Termina para evitar un arreglo invalido.
            return;
        }

        // Reserva el arreglo de numeros enteros.
        int[] numeros = new int[tamano];
        // Inicializa el acumulador de la suma.
        int suma = 0;
        // Inicializa el contador de positivos.
        int positivos = 0;
        // Inicializa el contador de negativos.
        int negativos = 0;
        // Inicializa el contador de ceros.
        int ceros = 0;
        // Inicializa el contador de pares.
        int pares = 0;
        // Inicializa el contador de impares.
        int impares = 0;

        // Recorre todas las posiciones del arreglo.
        for (int indice = 0; indice < tamano; indice++) {
            // Solicita el elemento correspondiente a la posicion.
            System.out.print("Elemento " + indice + ": ");
            // Comprueba que el elemento sea entero.
            if (!scanner.hasNextInt()) {
                // Informa que el elemento no es valido.
                System.out.println("Error: cada elemento debe ser entero.");
                // Cierra el lector.
                scanner.close();
                // Termina el programa.
                return;
            }
            // Guarda el elemento en el arreglo.
            numeros[indice] = scanner.nextInt();
            // Acumula el elemento en la suma.
            suma += numeros[indice];

            // Clasifica el elemento por su signo.
            if (numeros[indice] > 0) {
                // Incrementa la cantidad de positivos.
                positivos++;
            } else if (numeros[indice] < 0) {
                // Incrementa la cantidad de negativos.
                negativos++;
            } else {
                // Incrementa la cantidad de ceros.
                ceros++;
            }

            // Clasifica el elemento por su paridad.
            if (numeros[indice] % 2 == 0) {
                // Incrementa la cantidad de pares, incluido el cero.
                pares++;
            } else {
                // Incrementa la cantidad de impares.
                impares++;
            }
        }

        // Calcula el promedio convirtiendo la suma a double.
        double promedio = (double) suma / tamano;
        // Muestra la suma de los elementos.
        System.out.println("Suma: " + suma);
        // Muestra el promedio.
        System.out.printf("Promedio: %.2f%n", promedio);
        // Muestra la cantidad de positivos.
        System.out.println("Positivos: " + positivos);
        // Muestra la cantidad de negativos.
        System.out.println("Negativos: " + negativos);
        // Muestra la cantidad de ceros.
        System.out.println("Ceros: " + ceros);
        // Muestra la cantidad de pares.
        System.out.println("Pares: " + pares);
        // Muestra la cantidad de impares.
        System.out.println("Impares: " + impares);
        // Cierra el lector de la consola.
        scanner.close();
    }
}
