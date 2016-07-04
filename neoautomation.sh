#!/usr/bin/env sh
<<<<<<< HEAD
tmp="$(date +%Y-%m-%d)"
save_path=$1$tmp
mkdir $save_path
java -jar target/neoautomation-1.0-SNAPSHOT-jar-with-dependencies.jar $save_path'/'
=======

tmp="$(date +%Y-%m-%d)"
arg1=$1
save_path=$arg1$tmp
find $arg1 -name "*.neo" -exec rm -rf {} \;
if [ -d $save_path ]; then
    rm -rf $save_path
fi
mkdir $save_path
java -jar target/neoautomation-1.0-SNAPSHOT-jar-with-dependencies.jar $arg1
#cp $arg1"*.neo" $save_path"/"
find $arg1 -name "*.neo" -exec cp {} $save_path"/" \;
#for f in $arg1"*.csv"
#do
#   cp -v $f $save_path"/"
#done
>>>>>>> origin/master
