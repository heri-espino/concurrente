// stdio.h proporciona printf(), usada para mostrar mensajes en la terminal.
#include <stdio.h>
// sys/wait.h declara wait(), usada por el padre para esperar al hijo.
#include <sys/wait.h>
// unistd.h declara fork(), getpid() y getppid() en sistemas POSIX.
#include <unistd.h>

// main() es el punto de entrada del proceso original.
int main() {
    // fork() crea un proceso hijo.
    // En el padre devuelve el PID del hijo; en el hijo devuelve 0.
    // Si falla, devuelve un valor negativo.
    pid_t pid = fork();

    // Este bloque solo lo ejecuta el proceso padre.
    if (pid > 0) {
        printf("Soy el proceso padre\n");
        // getpid() obtiene el identificador del proceso que se esta ejecutando.
        printf("Mi PID es: %d\n", getpid());
        // getppid() obtiene el identificador del padre de este proceso.
        printf("Mi PPID es: %d\n", getppid());
        // La variable pid contiene el PID que fork() asigno al hijo.
        printf("PID de mi hijo: %d\n", pid);

        // wait(NULL) bloquea al padre hasta que el hijo termina.
        // NULL indica que no se necesita guardar el estado de salida del hijo.
        wait(NULL);

    // Este bloque solo lo ejecuta el proceso hijo.
    } else if (pid == 0) {
        printf("Soy el proceso hijo\n");
        printf("Mi PID es: %d\n", getpid());
        printf("Mi PPID es: %d\n", getppid());

    // Un valor negativo significa que no fue posible crear el hijo.
    } else {
        printf("Error al crear el proceso\n");
    }

    // El proceso termina correctamente.
    return 0;
}
