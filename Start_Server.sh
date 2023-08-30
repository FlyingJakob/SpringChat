#!/usr/bin/env bash
cd /home/ec2-user/server/

kill -9 $(lsof -t -i:8080)
java -jar SpringChat-0.0.1-SNAPSHOT.jar
