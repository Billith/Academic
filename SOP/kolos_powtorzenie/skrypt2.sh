#!/bin/bash
echo ""
for file in `ls *.txt | grep "^t"` ; do 
	echo "Liczba linii w $file wynosi `cat $file | wc -l`"
	echo "Najdłuższa linia w $file ma `cat $file | wc -L`"
	echo ""  
done
