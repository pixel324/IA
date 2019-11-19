[ -d build ] || mkdir build
javac -d build src/*/*.java -Xlint:unchecked
echo "donne moi une frequence"
read a
echo "donne moi la confiance"
read b
echo "le titre du fichier de la base de donne qui se trouve dans bases"
read c
java -cp build examples.ExtractData $a $b $c
