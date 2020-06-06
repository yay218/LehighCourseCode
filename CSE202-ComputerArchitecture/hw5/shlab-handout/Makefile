# Makefile for the CS:APP Shell Lab

TEAM = NOBODY
VERSION = 1
HANDINDIR = /afs/cs/academic/class/15213-f02/L5/handin
DRIVER = ./sdriver.pl
TSH = ./tsh
TSHREF = ./tshref
TSHARGS = "-p"
CC = g++
CFLAGS = -Wall -O2
FILES = $(TSH) ./myspin ./mysplit ./mystop ./myint
TURNIN = submission.tar
SRCS=tsh.o Node.o
OBJS=$(SRCS:.c=.o)


all: $(FILES)

%.o: %.c
	$(CC) $(CFLAGS) -c $< -o $@

tsh: tsh.o Node.o
	$(CC) $(CFLAGS) tsh.o Node.o -o tsh

tsh-handout: tsh-handout.o Node.o
	$(CC) $(CFLAGS) tsh-handout.o Node.o -o tsh-handout

##################
# Handin your work
##################
handin:
	cp tsh.c $(HANDINDIR)/$(TEAM)-$(VERSION)-tsh.c


##################
# Regression tests
##################

# Run tests using the student's shell program
test01:
	$(DRIVER) -t trace01.txt -s $(TSH) -a $(TSHARGS)
test02:
	$(DRIVER) -t trace02.txt -s $(TSH) -a $(TSHARGS)
test03:
	$(DRIVER) -t trace03.txt -s $(TSH) -a $(TSHARGS)
test04:
	$(DRIVER) -t trace04.txt -s $(TSH) -a $(TSHARGS)
test05:
	$(DRIVER) -t trace05.txt -s $(TSH) -a $(TSHARGS)
test06:
	$(DRIVER) -t trace06.txt -s $(TSH) -a $(TSHARGS)
test07:
	$(DRIVER) -t trace07.txt -s $(TSH) -a $(TSHARGS)
test08:
	$(DRIVER) -t trace08.txt -s $(TSH) -a $(TSHARGS)
test09:
	$(DRIVER) -t trace09.txt -s $(TSH) -a $(TSHARGS)
test10:
	$(DRIVER) -t trace10.txt -s $(TSH) -a $(TSHARGS)
test11:
	$(DRIVER) -t trace11.txt -s $(TSH) -a $(TSHARGS)
test12:
	$(DRIVER) -t trace12.txt -s $(TSH) -a $(TSHARGS)
test13:
	$(DRIVER) -t trace13.txt -s $(TSH) -a $(TSHARGS)
test14:
	$(DRIVER) -t trace14.txt -s $(TSH) -a $(TSHARGS)
test15:
	$(DRIVER) -t trace15.txt -s $(TSH) -a $(TSHARGS)
test16:
	$(DRIVER) -t trace16.txt -s $(TSH) -a $(TSHARGS)
test17:
	$(DRIVER) -t trace17.txt -s $(TSH) -a $(TSHARGS)
test18:
	$(DRIVER) -t trace18.txt -s $(TSH) -a $(TSHARGS)
test19:
	$(DRIVER) -t trace19.txt -s $(TSH) -a $(TSHARGS)
test20:
	$(DRIVER) -t trace20.txt -s $(TSH) -a $(TSHARGS)
test21:
	$(DRIVER) -t trace21.txt -s $(TSH) -a $(TSHARGS)
test22:
	$(DRIVER) -t trace22.txt -s $(TSH) -a $(TSHARGS)
test23:
	$(DRIVER) -t trace23.txt -s $(TSH) -a $(TSHARGS)
test24:
	$(DRIVER) -t trace24.txt -s $(TSH) -a $(TSHARGS)
test25:
	$(DRIVER) -t trace25.txt -s $(TSH) -a $(TSHARGS)

# Run the tests using the reference shell program
rtest01:
	$(DRIVER) -t trace01.txt -s $(TSHREF) -a $(TSHARGS)
rtest02:
	$(DRIVER) -t trace02.txt -s $(TSHREF) -a $(TSHARGS)
rtest03:
	$(DRIVER) -t trace03.txt -s $(TSHREF) -a $(TSHARGS)
rtest04:
	$(DRIVER) -t trace04.txt -s $(TSHREF) -a $(TSHARGS)
rtest05:
	$(DRIVER) -t trace05.txt -s $(TSHREF) -a $(TSHARGS)
rtest06:
	$(DRIVER) -t trace06.txt -s $(TSHREF) -a $(TSHARGS)
rtest07:
	$(DRIVER) -t trace07.txt -s $(TSHREF) -a $(TSHARGS)
rtest08:
	$(DRIVER) -t trace08.txt -s $(TSHREF) -a $(TSHARGS)
rtest09:
	$(DRIVER) -t trace09.txt -s $(TSHREF) -a $(TSHARGS)
rtest10:
	$(DRIVER) -t trace10.txt -s $(TSHREF) -a $(TSHARGS)
rtest11:
	$(DRIVER) -t trace11.txt -s $(TSHREF) -a $(TSHARGS)
rtest12:
	$(DRIVER) -t trace12.txt -s $(TSHREF) -a $(TSHARGS)
rtest13:
	$(DRIVER) -t trace13.txt -s $(TSHREF) -a $(TSHARGS)
rtest14:
	$(DRIVER) -t trace14.txt -s $(TSHREF) -a $(TSHARGS)
rtest15:
	$(DRIVER) -t trace15.txt -s $(TSHREF) -a $(TSHARGS)
rtest16:
	$(DRIVER) -t trace16.txt -s $(TSHREF) -a $(TSHARGS)
rtest17:
	$(DRIVER) -t trace17.txt -s $(TSHREF) -a $(TSHARGS)
rtest18:
	$(DRIVER) -t trace18.txt -s $(TSHREF) -a $(TSHARGS)
rtest19:
	$(DRIVER) -t trace19.txt -s $(TSHREF) -a $(TSHARGS)
rtest20:
	$(DRIVER) -t trace20.txt -s $(TSHREF) -a $(TSHARGS)
rtest21:
	$(DRIVER) -t trace21.txt -s $(TSHREF) -a $(TSHARGS)
rtest22:
	$(DRIVER) -t trace22.txt -s $(TSHREF) -a $(TSHARGS)
rtest23:
	$(DRIVER) -t trace23.txt -s $(TSHREF) -a $(TSHARGS)
rtest24:
	$(DRIVER) -t trace24.txt -s $(TSHREF) -a $(TSHARGS)
rtest25:
	$(DRIVER) -t trace25.txt -s $(TSHREF) -a $(TSHARGS)


# clean up
clean:
	rm -f $(FILES) tsh.o *~


submit:
	@rm -f $(TURNIN)
	@cp tsh.c $(USER)-tsh.c
	@tar czf $(TURNIN) $(USER)-tsh.c
	@~jloew/submitShlab.pl
	@rm -f $(TURNIN) $(USER)-bits.c
