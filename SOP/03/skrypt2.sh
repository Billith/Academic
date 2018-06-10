#!/bin/bash

echo -n "Liczba : "
read L

expr "$L" + 0  2> /dev/null

if [ $? -eq 0 ] ; then
	if [ $L -gt 0 ] ; then
		echo "Liczba $L jest wieksza od zera"
  	 else
		echo "Liczba $L nie jest wieksza od zera" 
	fi
 else
	echo "Podana wartosc nie jest liczba"
fi

exit 0
