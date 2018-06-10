#read file
#i = 0
#while [  ]
#cat $file | awk 'NR == 5'



s=1 
while read linia; do
	D=${#linia}
	echo "Linia nr $s liczba znakow: $D"
	let s=s+1
done < "$Plik"
