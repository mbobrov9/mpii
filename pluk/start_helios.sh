#!/usr/bin/env bash

mvn clean

mvn package -e

scp -P 2222 -i ~/.ssh/id_rsa \
    target/MPI_Project-1.0-SNAPSHOT.jar \
    s307712@se.ifmo.ru:/home/s307712/

ssh -P 2222 -i ~/.ssh/id_rsa s307712@se.ifmo.ru << EOF
pgrep -u s307712 java | xargs kill -9
nohup java -jar MPI_Project-1.0-SNAPSHOT.jar > log.txt &
EOF
