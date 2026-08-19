# x86

```pwsh
herie@heri:~/projects/concurrente/2_procesos/c$ gcc -g primitivas.c runner.c -o x86/program
herie@heri:~/projects/concurrente/2_procesos/c$ valgrind --tool=cachegrind ./x86/program

==39424== Cachegrind, a high-precision tracing profiler
==39424== Copyright (C) 2002-2024, and GNU GPL'd, by Nicholas Nethercote et al.
==39424== Using Valgrind-3.26.0 and LibVEX; rerun with -h for copyright info
==39424== Command: ./x86/program
==39424== 
PID: 39424
PPID: 1326
UID: 1000
EUID: 1000

Tiempo de ejecucion: 6597 microsegundos
==39424== 
==39424== I refs:        140,811
```