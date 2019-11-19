[ -d build ] || mkdir build
javac -d build src/*/*.java -Xlint:unchecked -Xlint:deprecation
java -cp build examples.ExampleData