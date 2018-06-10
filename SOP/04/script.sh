#mkdir ~/001
echo Nazwa: $0 
echo Liczba arg $#
#echo $1 $2 $3 $@
i=0
while [ $i -lt $# ]
do
	b=$i
	echo parametr nr $i wynoisi $b
	let i=i+1
done
#echo parametr $1
#echo parametr $2
#echo parametr $3
#echo parametr $4

#shift
#echo $1 $2 $3 $@
echo ---------------------------------

#dir ; dir - 2 komendy po sobie
#dir || dir - wykona się pierwsza komenda bez błedu
#dir && dir - koniunkcja, po błednym poleceniu kończy wejście
#dd - usuwanie jednej lini
#v - zaznaczanie tesktu
#x - ustwanie znaku pod kursorem
#u - cofnij
#midnight commander
#kill [level np. -9] [pid] - moc zabijania procesu
#ps -ef

