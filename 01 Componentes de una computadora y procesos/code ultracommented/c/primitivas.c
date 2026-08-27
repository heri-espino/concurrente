// sys/types.h define tipos de datos del sistema, como pid_t y uid_t.
#include <sys/types.h>
// unistd.h declara funciones POSIX relacionadas con procesos y usuarios.
#include <unistd.h>

// Declaracion de getpid(): devuelve el PID del proceso actual.
// pid_t es el tipo que el sistema utiliza para representar un PID.
pid_t getpid(void);

// Declaracion de getppid(): devuelve el PID del proceso padre.
pid_t getppid(void);

// Declaracion de getuid(): devuelve el identificador del usuario real.
uid_t getuid(void);

// Declaracion de geteuid(): devuelve el identificador efectivo del usuario.
uid_t geteuid(void);
