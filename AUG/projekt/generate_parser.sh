#!/bin/bash +x

cd grammar
<<<<<<< HEAD
java -jar /root/antlr-4.8-complete.jar -Dlanguage=Python3 HttpLang.g4 -o ../gen
=======
antlr4 -Dlanguage=Python3 HttpLang.g4 -o ../gen
>>>>>>> 75981e43ac6b528b2ff1f440faab7fe9f5cd5641
