#include <stdio.h>
#include <sys/types.h>
#include <unistd.h>

#include <x86intrin.h>

uint64_t rdtsc_start(void) {
    return __rdtsc();
}

int main(void) {
    // 1. Guardar el resultado de la INVOCACIÓN en variables
    pid_t id_proceso       = getpid();
    pid_t id_proceso_padre = getppid();

    uid_t id_usuario       = getuid();
    uid_t id_usuario_efec  = geteuid();

    // 2. Imprimir los valores en pantalla mediante printf
    printf("PID del proceso actual: %d\n", (int)id_proceso);
    printf("PID del proceso padre:  %d\n", (int)id_proceso_padre);
    printf("UID del usuario real:   %d\n", (int)id_usuario);
    printf("UID del usuario efect:  %d\n", (int)id_usuario_efec);

    return 0;
}