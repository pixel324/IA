#!/bin/bash
[ -d build ] || mkdir build
javac -d build src/*/*.java -Xlint:unchecked -Xlint:deprecation
echo "--- choix de l'heuristique : "
echo "1 / pour un heuristique qui trie les variables;"
echo "2 / pour un heuristique qui trie aléatoirement;"
echo "3 / pour un heuristique qui trie les valeurs."
echo " ton choix ?"; 
read choix
if [ $choix = "1" ]; then
		echo " --- choix de son fonctionnnement :"
        echo "1 / Choix de la variable la moins contrainte;"
        echo "2 / Choix de la variable la plus contrainte;"
        echo "3 / Choix de la variable avec le domaine le plus petit;"
        echo "4 / Choix de la variable avec le domaine le plus grande."
        echo " ton choix ?"; 
        read choix2
        java -cp build examples.BacktrackMain $choix $choix2
elif [ $choix = "2" ]; then
        java -cp build examples.BacktrackMain $choix ""
elif [ $choix = "3" ]; then
		echo "1 / Choix de la plus petite valeur;"
        echo "2 / Choix de la plus grande valeur."
        echo " ton choix ?"; 
        echo Choix de la plus petite valeur 1 et ou de la plus grand 2
        read choix2
        java -cp build examples.BacktrackMain $choix $choix2
else java -cp build examples.BacktrackMain
fi

