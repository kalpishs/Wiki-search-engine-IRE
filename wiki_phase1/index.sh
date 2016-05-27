#!/bin/bash 
cd ./code
javac Driver.java 
cd ./../code
java Driver ../$1 ../$2 