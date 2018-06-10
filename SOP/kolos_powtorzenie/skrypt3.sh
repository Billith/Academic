#!/bin/bash
if [ $# -ne 3 ] || [ ! -e $1 ]
then
	echo "Za dużo lub za mało argumentów lub plik wejściowy nie istnieje"
else
	PLIKWE=$1
	PLIKWY1=$2
	PLIKWY2=$3
	ZEROS=0

	while read LINE
	do
		SUMA=0
		for NUMBER in $LINE
		do
			if [ $NUMBER -eq 0 ] ; then
			let ZEROS++
			fi
			let SUMA=SUMA+NUMBER
		done
		if [ $SUMA -lt 10 ] ; then
			echo $LINE >> $PLIKWY1
		fi
		if [ $SUMA -gt 20 ] ; then
			echo $LINE >> $PLIKWY2
		fi
	done < "$PLIKWE"
echo "Liczba zer to $ZEROS"
fi
