OPTS = -g -c -Werror

silc: silc.o token.o lexer.o parser.o
	g++ -o silc silc.o lexer.o token.o parser.o

silc.o: silc.cc lexer.h token.h parser.h
	g++ $(OPTS) silc.cc

lexer.o: lexer.cc lexer.h token.h
	g++ $(OPTS) lexer.cc

token.o: token.h token.cc
	g++ $(OPTS) token.cc

parser.o: parser.cc parser.h lexer.h token.h
	g++ $(OPTS) parser.cc

clean:
	rm -f *.o *~