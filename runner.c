#include <stdio.h>
#include <sys/types.h>
#include <unistd.h>

// Llamamos a las funciones cuya interfaz está en listado.c
int main(void) {
    printf("PID: %d\n", (int)getpid());
    printf("PPID: %d\n", (int)getppid());
    printf("UID: %d\n", (int)getuid());
    printf("EUID: %d\n", (int)geteuid());
    return 0;
}