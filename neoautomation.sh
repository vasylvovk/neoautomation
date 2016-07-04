#!/usr/bin/env sh
tmp="$(date +%Y-%m-%d)"
arg1=$1
save_path=$arg1$tmp
find $arg1 -name "*.neo" -exec rm -rf {} \;
if [ -d $save_path ]; then
    rm -rf $save_path
fi
mkdir $save_path
java -jar target/neoautomation-1.0-SNAPSHOT-jar-with-dependencies.jar $arg1
find $arg1 -name "*.neo" -exec cp {} $save_path"/" \;

