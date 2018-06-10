#!/bin/bash
for file in *
do
	ZNAKI=`echo $file | wc -c`
	let ZNAKI=ZNAKI-1
	if [ $ZNAKI -ge 4 ] ; then
		echo $file $ZNAKI
	fi
done
