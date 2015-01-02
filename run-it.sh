#!/bin/sh
/etc/bootstrap.sh
hdfs dfs -put /opt/de-pijp-impatient/data/ /data

for i in 1 2 3
do
    hadoop jar /opt/de-pijp-impatient/target/de-pijp-impatient-jar-with-dependencies.jar io.github.mattthomson.depijp.impatient.Part${i} /data/rain.txt /output/part${i}.tsv
done

for i in 4
do
    hadoop jar /opt/de-pijp-impatient/target/de-pijp-impatient-jar-with-dependencies.jar io.github.mattthomson.depijp.impatient.Part${i} /data/rain.txt /data/en.stop /output/part${i}.tsv
done

for i in 1 2 3 4
do
    hadoop fs -getmerge /output/part${i}.tsv /opt/de-pijp-impatient/target/part${i}.tsv
done
