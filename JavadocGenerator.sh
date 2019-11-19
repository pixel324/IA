#!bin/sh
[ -d javadoc ] || mkdir javadoc/
javadoc -d javadoc/ src/*/*.java
