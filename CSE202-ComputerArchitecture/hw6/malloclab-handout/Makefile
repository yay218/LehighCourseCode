#
# Students' Makefile for the Malloc Lab
#

CC = g++
CFLAGS = -Wall -O2 -m64

TURNIN=submission.tar

OBJS = mdriver.o mm.o memlib.o fsecs.o fcyc.o clock.o ftimer.o

mdriver: $(OBJS)
	$(CC) $(CFLAGS) -o mdriver $(OBJS)

mdriver.o: mdriver.c fsecs.h fcyc.h clock.h memlib.h config.h mm.h
memlib.o: memlib.c memlib.h
mm.o: mm.c mm.h memlib.h
fsecs.o: fsecs.c fsecs.h config.h
fcyc.o: fcyc.c fcyc.h
ftimer.o: ftimer.c ftimer.h config.h
clock.o: clock.c clock.h

clean:
	rm -f *~ *.o mdriver

submit:
	@rm -f $(TURNIN)
	@cp mm.c $(USER)-mm.c
	@tar czf $(TURNIN) $(USER)-mm.c
	@~jloew/submitMallocLab.pl
	@rm -f $(TURNIN) $(USER)-mm.c
