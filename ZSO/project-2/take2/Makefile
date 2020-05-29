CC=gcc
CFLAGS=-Wall -Wextra -std=c11
DEPS=-pthread
BDIR=
LIBS=

all:
	$(CC) $(CFLAGS) $(LIBS) -o client client.c
	$(CC) $(CFLAGS) $(LIBS) -o main main.c $(DEPS)
	$(CC) $(CFLAGS) $(LIBS) -o cleaner cleaner.c

clean:
	rm client main cleaner