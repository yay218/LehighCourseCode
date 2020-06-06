OPTS= -g -c -Wall -Werror 

shell: prog2.o getopts.o
	gcc -o shell prog2.o getopts.o

prog2.o: prog2.c
	gcc $(OPTS) prog2.c

getopts.o: getopts.c
	gcc $(OPTS) getopts.c


clean:
	rm -f *.o *~ a.out shell prog2.o getopts.o
