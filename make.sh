#!/bin/sh
rm -rf out
mkdir out
find ./ -name "*.java" > sources
javac @sources -d out
cd out 
java com/avaj/simulator/Simulator ../scenario
cat -e simulation
