// stdio.h declara printf(), que se utiliza para mostrar resultados en la terminal.
#include <stdio.h>
// sys/types.h proporciona tipos definidos por el sistema, como pid_t y uid_t.
#include <sys/types.h>
// unistd.h declara getpid(), getppid(), getuid() y geteuid().
#include <unistd.h>
// time.h declara clock_gettime() y struct timespec para medir tiempo.
#include <time.h>

// main() es el punto de entrada del programa.
// void indica que no se reciben argumentos desde la línea de comandos.
int main(void) {
    // inicio almacenará el instante en que comienza la medición.
    struct timespec inicio;
    // fin almacenará el instante en que termina la medición.
    struct timespec fin;

    // CLOCK_MONOTONIC utiliza un reloj que avanza de forma constante.
    // &inicio indica dónde debe guardarse el tiempo obtenido.
    clock_gettime(CLOCK_MONOTONIC, &inicio);

    // getpid() obtiene el identificador del proceso actual.
    // La conversión a int permite imprimirlo con el especificador %d.
    printf("PID: %d\n", (int)getpid());

    // getppid() obtiene el identificador del proceso padre.
    printf("PPID: %d\n", (int)getppid());

    // getuid() obtiene el identificador real del usuario que ejecuta el proceso.
    printf("UID: %d\n", (int)getuid());

    // geteuid() obtiene el identificador efectivo usado para comprobar permisos.
    printf("EUID: %d\n", (int)geteuid());

    // Se toma el segundo instante inmediatamente después de las operaciones.
    clock_gettime(CLOCK_MONOTONIC, &fin);

    // Se calcula la diferencia entre los segundos de ambos instantes.
    long segundos = fin.tv_sec - inicio.tv_sec;
    // Se calcula la diferencia entre las partes expresadas en nanosegundos.
    long nanosegundos = fin.tv_nsec - inicio.tv_nsec;
    // Los segundos se convierten a microsegundos y los nanosegundos se dividen
    // entre 1000 para obtener también microsegundos.
    long microsegundos = (segundos * 1000000) + (nanosegundos / 1000);

    // \n deja una línea en blanco antes de mostrar el tiempo total.
    // %ld imprime un valor de tipo long.
    printf("\nTiempo de ejecucion: %ld microsegundos\n", microsegundos);

    // El valor 0 informa al sistema que el programa terminó correctamente.
    return 0;
}
