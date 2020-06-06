OPTS = -g -c -Wall -Werror

arraymap: arraymap.o item.o prog3.o
	g++ -o arraymap arraymap.o item.o prog3.o


prog3.o: prog3.cc
	g++ $(OPTS) prog3.cc


arraymap.o: arraymap.h arraymap.cc
	g++ $(OPTS) arraymap.cc


item.o: item.h item.cc
	g++ $(OPTS) item.cc


clean:
	rm -f *.o *~


