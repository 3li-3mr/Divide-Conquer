#!/bin/zsh
set -e

rm -rf bin/*
javac -d bin src/problem1/*.java src/problem2/*.java

cp src/problem1/*.java bin/problem1/
cp src/problem2/*.java bin/problem2/

jar cfe Problem1.jar problem1.Main -C bin problem1
jar cfe Problem2.jar problem2.Main -C bin problem2

echo "Built Problem1.jar and Problem2.jar (with source files included)."
