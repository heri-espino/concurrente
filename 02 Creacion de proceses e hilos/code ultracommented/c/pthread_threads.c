// stdio.h proporciona printf(), usada para mostrar la salida de cada hilo.
#include <stdio.h>
// pthread.h declara pthread_t, pthread_create(), pthread_self() y pthread_join().
#include <pthread.h>

// Esta funcion es la tarea que ejecutara el hilo hijo.
// Debe recibir y devolver un puntero para cumplir con la firma de pthread_create().
void *tarea(void *argumento) {
    // El ejemplo no recibe datos del hilo principal, por eso se ignora argumento.
    (void)argumento;

    // pthread_self() devuelve el identificador del hilo que ejecuta esta funcion.
    printf("Hola desde el hilo hijo! ID: %lu\n", (unsigned long)pthread_self());

    // El hilo hijo ejecuta su propio ciclo independiente del hilo principal.
    for (int i = 1; i <= 5; i++) {
        printf("Hilo hijo: %d\n", i);
    }

    // NULL indica que el hilo no devuelve un resultado al terminar.
    return NULL;
}

// main() corresponde al hilo principal del proceso.
int main() {
    // pthread_t almacena el identificador del hilo que sera creado.
    pthread_t hilo;

    // pthread_create() crea el hilo y le asigna la funcion tarea().
    // El ultimo NULL indica que tarea() no recibe argumentos adicionales.
    pthread_create(&hilo, NULL, tarea, NULL);

    // El hilo principal tambien se identifica y ejecuta su propio ciclo.
    printf("Hola desde el hilo principal! ID: %lu\n", (unsigned long)pthread_self());
    for (int i = 1; i <= 5; i++) {
        printf("Hilo principal: %d\n", i);
    }

    // pthread_join() bloquea al hilo principal hasta que termina el hilo hijo.
    // El segundo NULL indica que no se necesita recibir el valor de retorno.
    pthread_join(hilo, NULL);

    // El programa termina cuando ambos hilos finalizaron.
    return 0;
}
