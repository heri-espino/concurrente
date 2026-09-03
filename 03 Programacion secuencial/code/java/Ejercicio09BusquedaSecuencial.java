import java.util.Scanner;

// busca un numero recorriendo el arreglo
public class Ejercicio09BusquedaSecuencial {
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

        // crea el arreglo y lee sus elementos
        int[] numeros = new int[tamano];
        for (int indice = 0; indice < tamano; indice++) {
            System.out.print("Elemento " + indice + ": ");
            if (!scanner.hasNextInt()) {
                System.out.println("Error: cada elemento debe ser entero.");
                scanner.close();
                return;
            }
            numeros[indice] = scanner.nextInt();
        }

        // solicita el valor que se buscara
        System.out.print("Numero que deseas buscar: ");
        if (!scanner.hasNextInt()) {
            System.out.println("Error: introduce un numero entero.");
            scanner.close();
            return;
        }
        // guarda el valor de busqueda
        int buscado = scanner.nextInt();

        // recorre el arreglo y acumula las coincidencias
        StringBuilder posiciones = new StringBuilder();
        int apariciones = 0;
        for (int indice = 0; indice < numeros.length; indice++) {
            if (numeros[indice] == buscado) {
                if (apariciones > 0) {
                    posiciones.append(", ");
                }
                posiciones.append(indice);
                apariciones++;
            }
        }

        // muestra las posiciones o el mensaje de ausencia
        if (apariciones == 0) {
            System.out.println("El numero no se encuentra en el arreglo.");
        } else {
            System.out.println("Posiciones encontradas: " + posiciones);
            System.out.println("Cantidad de apariciones: " + apariciones);
        }
        // cierra el lector de datos
        scanner.close();
    }
}
