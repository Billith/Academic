
if [ $# -eq 0 ]
then
	echo "Min jeden arg"
else
	for arg in $@
	do
		case $1 in
			"wtorek") echo "W" ;;
			"sroda") echo "S" ;;
			"piatek") echo "P" ;;
		esac
		shift
	done
fi 
