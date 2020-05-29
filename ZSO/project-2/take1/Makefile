CC=gcc
CFLAGS=-Wall -Wextra
DEPS=
BDIR=build
LIBS=

all:
	mkdir -p $(BDIR)
	$(CC) $(CFLAGS) $(LIBS) -o $(BDIR)/client client.c $(DEPS)
	$(CC) $(CFLAGS) $(LIBS) -o $(BDIR)/process-1 process-1.c $(DEPS)
	$(CC) $(CFLAGS) $(LIBS) -o $(BDIR)/process-2 process-2.c $(DEPS)

clean:
	rm $(BDIR)/*