for file in *
	do
		if [[ $file == t*  ]] && [[ $file == *.txt ]]
		then
			LICZLINII=`wc -l $file | awk '{ print $1 }'`;
			echo "Ilosc linii: $LICZLINII";
			MAX_ZNAKOW=0;
			while read LINIA
			do
				ILOSC_ZNAKOW_W_LINII=`echo -n $LINIA | wc -c`
			if [ $MAX_ZNAKOW -lt $ILOSC_ZNAKOW_W_LINII ]
			then
				let MAX_ZNAKOW=ILOSC_ZNAKOW_W_LINII
			fi
			done<$file
		fi
done 
echo "Najwiecej znakow w linii $MAX_ZNAKOW";
