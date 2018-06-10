#!/bin/bash
echo -n "Podaj pierwszą liczbe : "
read J
echo -n "Podaj drugą liczbe : "
read D
echo -n "Podaj nazwe pliku : "
read F
W=$[J+D]
Y=$[J-D]
Z=$[J*D]
X=$[J/D]
C=$[J**D]
B=$[W+Y+Z+X+C]
echo "$W, $Y, $Z, $X, $C, $B" >> $F
cat $F


