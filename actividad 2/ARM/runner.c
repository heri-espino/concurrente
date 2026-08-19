#include <stdio.h>
#include <sys/types.h>
#include <unistd.h>
#include <time.h> // Necesario para clock_gettime y struct timespec

int main(void) {
    struct timespec inicio, fin;
    // 1. Tomar el tiempo justo antes de empezar
    clock_gettime(CLOCK_MONOTONIC, &inicio);

    printf("PID: %d\n", (int)getpid());
    printf("PPID: %d\n", (int)getppid());
    printf("UID: %d\n", (int)getuid());
    printf("EUID: %d\n", (int)geteuid());

    // 2. Tomar el tiempo inmediatamente después de terminar
    clock_gettime(CLOCK_MONOTONIC, &fin);

    // 3. Calcular la diferencia en microsegundos (us)
    long segundos = fin.tv_sec - inicio.tv_sec;
    long nanosegundos = fin.tv_nsec - inicio.tv_nsec;
    long microsegundos = (segundos * 1000000) + (nanosegundos / 1000);

    printf("\nTiempo de ejecucion: %ld microsegundos\n", microsegundos);

    return 0;
}
