#!/bin/bash
if [ $# -eq 0 ] 
then
	echo "$#<0!"
else
	for arg in $@
		do
			case $1 in 
				pon*) DZIEN="Mondey";;
				wto*) DZIEN="Tuesday";;
				śro*) DZIEN="Wednesday";;
				czw*) DZIEN="Thursday";;
				pią*) DZIEN="Friday";;
				sob*) DZIEN="Saturday";;
				nie*) DZIEN="Sunday";;
				*) DZIEN="Błąd";;
			esac
		shift
		echo $DZIEN
	done
fi
