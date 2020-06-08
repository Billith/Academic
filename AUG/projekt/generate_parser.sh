#!/bin/bash +x

cd grammar
java -jar /root/antlr-4.8-complete.jar -Dlanguage=Python3 HttpLang.g4 -o ../gen
antlr4 -Dlanguage=Python3 HttpLang.g4 -o ../gen
