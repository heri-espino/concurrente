#include <stdio.h>
#include <sys/wait.h>
#include <unistd.h>

int main() {
    // Crear un proceso hijo.
    pid_t pid = fork();

    if (pid > 0) {
        // Proceso padre.
        wait(NULL); // El padre espera a que el hijo termine.
        printf("Soy el proceso padre\n");
        printf("Mi PID es: %d\n", getpid());
        printf("Mi PPID es: %d\n", getppid());
        printf("PID de mi hijo: %d\n", pid);

    } else if (pid == 0) {
        // Proceso hijo.
        printf("Soy el proceso hijo\n");
        printf("Mi PID es: %d\n", getpid());
        printf("Mi PPID es: %d\n", getppid());
    } else {
        printf("Error al crear el proceso\n");
    }

    return 0;
}
