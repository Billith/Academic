#!/bin/bash
for i in * ; do
	Z=`cat $i | grep $i`
	if [ -n "$Z" ] ; then
		echo "Plik \"$i\" zawiera w sobie swoja nazwe"
	fi
done

exit 0
