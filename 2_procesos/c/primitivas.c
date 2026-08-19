#include <sys/types.h>
#include <unistd.h>

pid_t getpid (void); // ID proceso.
pid_t getppid (void); // ID proceso padre.

uid_t getuid (void); // ID usuario.
uid_t geteuid (void); // ID usuario efectivo.