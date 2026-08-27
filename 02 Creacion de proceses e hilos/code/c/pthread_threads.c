#include <stdio.h>
#include <pthread.h>
// Funcion que ejecuta el hilo hijo.
void *tarea(void *argumento) {
    (void)argumento;
    printf("Hola desde el hilo hijo! ID: %lu\n", (unsigned long)pthread_self());
    for (int i = 1; i <= 5; i++) {
        printf("Hilo hijo: %d\n", i);
    }
    return NULL;
}

int main() {
    pthread_t hilo;
    // Crear el hilo.
    pthread_create(&hilo, NULL, tarea, NULL);
    printf("Hola desde el hilo principal! ID: %lu\n", (unsigned long)pthread_self());
    for (int i = 1; i <= 5; i++) {
        printf("Hilo principal: %d\n", i);
    }
    // Esperar al hilo hijo.
    pthread_join(hilo, NULL);
    return 0;
}
