#!/bin/sh
rm -rf out
mkdir out
find ./ -name "*.java" > sources.txt
javac @sources.txt -d out
cd out 
java com/avaj/simulator/Simulator ../scenario.txt
cat -e simulation.txt
