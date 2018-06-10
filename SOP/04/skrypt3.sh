read nazwa
a=`ps -aux | grep @nazwa | awk ' { print $2 }'`
echo $a
kill -9 $a
