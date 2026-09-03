import java.util.Scanner;

// analiza los elementos de un arreglo entero
public class Ejercicio08AnalisisArreglo {
    // inicia el programa
    public static void main(String[] args) {
        // crea el lector de datos
        Scanner scanner = new Scanner(System.in);

        // solicita y valida el tamano del arreglo
        System.out.print("Tamano del arreglo: ");
        if (!scanner.hasNextInt()) {
            System.out.println("Error: introduce un tamano entero.");
            scanner.close();
            return;
        }
        int tamano = scanner.nextInt();
        if (tamano <= 0) {
            System.out.println("Error: el tamano debe ser mayor que cero.");
            scanner.close();
            return;
        }

        // crea el arreglo y sus contadores
        int[] numeros = new int[tamano];
        int suma = 0;
        int positivos = 0;
        int negativos = 0;
        int ceros = 0;
        int pares = 0;
        int impares = 0;

        // recorre el arreglo y clasifica cada elemento
        for (int indice = 0; indice < tamano; indice++) {
            // solicita y valida el elemento actual
            System.out.print("Elemento " + indice + ": ");
            if (!scanner.hasNextInt()) {
                System.out.println("Error: cada elemento debe ser entero.");
                scanner.close();
                return;
            }
            // guarda el elemento y actualiza la suma
            numeros[indice] = scanner.nextInt();
            suma += numeros[indice];

            if (numeros[indice] > 0) {
                positivos++;
            } else if (numeros[indice] < 0) {
                negativos++;
            } else {
                ceros++;
            }

            if (numeros[indice] % 2 == 0) {
                pares++;
            } else {
                impares++;
            }
        }

        // calcula el promedio del arreglo
        double promedio = (double) suma / tamano;
        // muestra el resumen del analisis
        System.out.println("Suma: " + suma);
        System.out.printf("Promedio: %.2f%n", promedio);
        System.out.println("Positivos: " + positivos);
        System.out.println("Negativos: " + negativos);
        System.out.println("Ceros: " + ceros);
        System.out.println("Pares: " + pares);
        System.out.println("Impares: " + impares);
        // cierra el lector de datos
        scanner.close();
    }
}
