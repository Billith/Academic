#!/bin/sh

cd grammar
antlr4 -Dlanguage=Python3 HttpLang.g4 -o ../parser
