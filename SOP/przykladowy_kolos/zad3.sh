#!/bin/bash
for file in *
do
	if [ -d $file ] ; then
		cd $file
		for PLIK in * 
		do
			if [ -r $PLIK ] ; then
				echo "$file has at least one readable file"
				break
			fi
			#echo $PLIK in $file
		done
		cd ..
	fi
done
