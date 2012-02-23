#! /bin/bash
i=0;
while [ $i -ne 100 ];
do
	rand1=`date +%N`
	rand2=`date +%N`
	e=`java -cp jatek.jar quixo.engine.Game 600 $1 $rand1 $2 $3 $4 $5 $6 $7 $rand2 $8 $9 ${10} ${11} ${12} 0`
	echo "600" $1 $rand1 $2 $3 $4 $5 $6 $7 $rand2 $8 $9 ${10} ${11} ${12} "0" $e>> rh.txt
	e=`java -cp jatek.jar quixo.engine.Game 600 $7 $rand2 $8 $9 ${10} ${11} ${12} $1 $rand1 $2 $3 $4 $5 $6 0`
	echo "600" $7 $rand2 $8 $9 ${10} ${11} ${12} $1 $rand1 $2 $3 $4 $5 $6 "0" $e>> hr.txt
	i=`expr $i + 1`
done