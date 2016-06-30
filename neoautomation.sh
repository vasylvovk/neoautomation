#!/usr/bin/env sh
tmp="$(date +%Y-%m-%d)"
save_path=$1$tmp
mkdir $save_path
java -jar target/neoautomation-1.0-SNAPSHOT-jar-with-dependencies.jar $save_path'/'