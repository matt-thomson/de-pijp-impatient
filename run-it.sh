#!/bin/sh
/etc/bootstrap.sh
hdfs dfs -put /opt/de-pijp-impatient/data/ /data

hadoop jar /opt/de-pijp-impatient/target/de-pijp-impatient-jar-with-dependencies.jar io.github.mattthomson.depijp.impatient.Part1 /data/rain.txt /output/part1.tsv
hadoop jar /opt/de-pijp-impatient/target/de-pijp-impatient-jar-with-dependencies.jar io.github.mattthomson.depijp.impatient.Part2 /data/rain.txt /output/part2.tsv
hadoop jar /opt/de-pijp-impatient/target/de-pijp-impatient-jar-with-dependencies.jar io.github.mattthomson.depijp.impatient.Part3 /data/rain.txt /output/part3.tsv

hadoop fs -getmerge /output/part1.tsv /opt/de-pijp-impatient/target/part1.tsv
hadoop fs -getmerge /output/part2.tsv /opt/de-pijp-impatient/target/part2.tsv
hadoop fs -getmerge /output/part3.tsv /opt/de-pijp-impatient/target/part3.tsv
