#!/bin/bash
echo "All arguments $@"
for file in $@
do
	if [ -f $file ] ; then
		egrep ' CPU | CPU.| CPU,' $file > /dev/null
		if [ $? -eq 0 ] ; then
			echo "String \"CPU\" found in $file"
		fi
	fi
done
