CC=g++
CFLAGS=-Wall -Wextra -std=c++17
DEPS=engine.cpp tasks.cpp
BDIR=build
LIBS=-pthread

all:
	mkdir -p $(BDIR)
	$(CC) $(CFLAGS) $(LIBS) -o $(BDIR)/server server.cpp $(DEPS)

run:
	mkdir -p $(BDIR)
	$(CC) $(CFLAGS) $(LIBS) -o $(BDIR)/server server.cpp $(DEPS) && ./$(BDIR)/server

clean:
	rm $(BDIR)/server
