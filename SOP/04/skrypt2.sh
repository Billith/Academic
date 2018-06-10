ls > /dev/null

for file in *
do
	if [ ! -x file ]
	then
	echo "Brak dostepu $file"
	fi
done
ls -al | awk '{ print $9 " " $5 }' | tail -n 2
