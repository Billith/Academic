CC     = gcc
CFLAGS = -Wall -Werror -Wextra -Wpedantic -g
DEPS   =
BDIR   = build
LIBS   = -pthread

all:
	mkdir -p $(BDIR)
	$(CC) $(CFLAGS) $(LIBS) -o $(BDIR)/main main.c $(DEPS)

run:
	mkdir -p $(BDIR)
	$(CC) $(CFLAGS) $(LIBS) -o $(BDIR)/main main.c $(DEPS) && ./$(BDIR)/main

clear:
	rm $(BDIR)/main
