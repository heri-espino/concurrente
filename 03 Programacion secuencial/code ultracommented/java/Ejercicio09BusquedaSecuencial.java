// Importa la clase para leer datos desde la consola.
import java.util.Scanner;

// Declara la clase principal del ejercicio 9.
public class Ejercicio09BusquedaSecuencial {
    // Declara el punto de entrada del programa.
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
        // Valida que el tamano sea positivo.
        if (tamano <= 0) {
            // Informa que el arreglo necesita al menos un elemento.
            System.out.println("Error: el tamano debe ser mayor que cero.");
            // Cierra el lector.
            scanner.close();
            // Termina el programa.
            return;
        }

        // Reserva el arreglo que se recorrera secuencialmente.
        int[] numeros = new int[tamano];
        // Recorre las posiciones para capturar los elementos.
        for (int indice = 0; indice < tamano; indice++) {
            // Solicita el elemento actual.
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
        }

        // Solicita el valor que se buscara.
        System.out.print("Numero que deseas buscar: ");
        // Comprueba que el valor buscado sea entero.
        if (!scanner.hasNextInt()) {
            // Informa que el valor no es valido.
            System.out.println("Error: introduce un numero entero.");
            // Cierra el lector.
            scanner.close();
            // Termina el programa.
            return;
        }
        // Guarda el valor objetivo de la busqueda.
        int buscado = scanner.nextInt();

        // Reserva una cadena para acumular las posiciones encontradas.
        StringBuilder posiciones = new StringBuilder();
        // Inicializa el contador de apariciones.
        int apariciones = 0;
        // Recorre el arreglo sin usar metodos de busqueda de Java.
        for (int indice = 0; indice < numeros.length; indice++) {
            // Compara el elemento actual con el valor buscado.
            if (numeros[indice] == buscado) {
                // Separa las posiciones posteriores con una coma.
                if (apariciones > 0) {
                    // Agrega el separador entre posiciones.
                    posiciones.append(", ");
                }
                // Agrega la posicion encontrada.
                posiciones.append(indice);
                // Cuenta la aparicion actual.
                apariciones++;
            }
        }

        // Comprueba si la busqueda no encontro coincidencias.
        if (apariciones == 0) {
            // Muestra el mensaje solicitado para el caso sin resultados.
            System.out.println("El numero no se encuentra en el arreglo.");
        } else {
            // Muestra todas las posiciones encontradas.
            System.out.println("Posiciones encontradas: " + posiciones);
            // Muestra el total de apariciones.
            System.out.println("Cantidad de apariciones: " + apariciones);
        }
        // Cierra el lector.
        scanner.close();
    }
}
