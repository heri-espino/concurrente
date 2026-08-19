gcc -g primitivas.c runner.c -o x86/act2.3
valgrind --tool=cachegrind ./x86/act2.3