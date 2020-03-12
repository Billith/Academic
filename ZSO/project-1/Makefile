CC=g++
CFLAGS=-Wall -Wextra -std=c++17
DEPS=
BDIR=build
LIBS=-pthread

all:
	mkdir -p $(BDIR)
	$(CC) $(CFLAGS) $(LIBS) -o $(BDIR)/main main.cpp $(DEPS)

run:
	mkdir -p $(BDIR)
	$(CC) $(CFLAGS) $(LIBS) -o $(BDIR)/main main.cpp $(DEPS) && ./$(BDIR)/main

clean:
	rm $(BDIR)/main
