CC     = g++
CFLAGS = -Wall -Werror -Wextra -Wpedantic -std=c++17 -g
DEPS   =
BDIR   = build
LIBS   = -pthread

all:
	mkdir -p $(BDIR)
	$(CC) $(CFLAGS) $(LIBS) -o $(BDIR)/main main.cpp $(DEPS)

run:
	mkdir -p $(BDIR)
	$(CC) $(CFLAGS) $(LIBS) -o $(BDIR)/main main.cpp $(DEPS) && ./$(BDIR)/main

clear:
	rm $(BDIR)/main
