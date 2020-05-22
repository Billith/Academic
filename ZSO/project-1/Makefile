CC     = gcc
CFLAGS = -Wall -Wextra -Wpedantic -g
DEPS   =
BDIR   = build
LIBS   = -pthread

all:
	mkdir -p $(BDIR)
	$(CC) $(CFLAGS) $(LIBS) -o $(BDIR)/main main.c $(DEPS)

run:
	mkdir -p $(BDIR)
	$(CC) $(CFLAGS) $(LIBS) -o $(BDIR)/main main.c $(DEPS) && ./$(BDIR)/main

debug:
	mkdir -p $(BDIR)
	$(CC) $(CFLAGS) $(LIBS) -o $(BDIR)/main main.c $(DEPS) -DDEBUG -DDELAY && ./$(BDIR)/main

clear:
	rm $(BDIR)/main
