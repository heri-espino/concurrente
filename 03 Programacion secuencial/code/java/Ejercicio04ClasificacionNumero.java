import java.util.Scanner;

// clasifica un numero por signo y paridad
public class Ejercicio04ClasificacionNumero {
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

        // lee el numero y determina su signo
        int numero = scanner.nextInt();
        String signo;
        if (numero > 0) {
            signo = "positivo";
        } else if (numero < 0) {
            signo = "negativo";
        } else {
            signo = "cero";
        }

        // determina la paridad con el operador modulo
        String paridad = numero % 2 == 0 ? "par" : "impar";
        String conjuncion = paridad.equals("impar") ? "e" : "y";
        // muestra la clasificacion final
        System.out.println("El numero es " + signo + " " + conjuncion + " " + paridad + ".");
        // cierra el lector de datos
        scanner.close();
    }
}
