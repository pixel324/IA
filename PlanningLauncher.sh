[ -d build ] || mkdir build
javac -d build src/*/*.java -Xlint:unchecked
java -cp build examples.PlanningMain

sleep 50
