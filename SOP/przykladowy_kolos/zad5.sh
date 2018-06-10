#!/bin/bash
ILOSC_L=0
ILOSC_C=0
while read -n 1 CHAR;
do
	expr $CHAR + 1 > /dev/null 2>/dev/null
	if [ $? -eq 0 ] ; then
#		echo $CHAR
		let ILOSC_C++
	fi
#	echo "-------------------------"
	echo $CHAR | grep '[A-Za-z]' > /dev/null
	if [ $? -eq 0 ] ; then
#		echo $CHAR
		let ILOSC_L++
	fi
done < $1
echo "Ilosc liter $ILOSC_L"
echo "Ilosc cyfr $ILOSC_C"

#$FILE=`cat $1 | read
