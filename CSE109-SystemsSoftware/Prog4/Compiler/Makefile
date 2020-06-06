OPTS = -g -c -Wall -Werror

lextest: lexer.o token.o test.o
	g++ -o lextest lexer.o token.o test.o


test.o: test.cc lexer.h token.h
	g++ $(OPTS) test.cc


lexer.o: lexer.cc lexer.h token.h
	g++ $(OPTS) lexer.cc


token.o: token.h token.cc
	g++ $(OPTS) token.cc


clean:
	rm -f *.o *~

