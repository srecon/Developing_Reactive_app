#!/bin/bash

java -XX:CRaCCheckpointTo=/crac -jar /app/spring-boot-crac-0.0.1.jar&
sleep 10
jcmd /app/spring-boot-crac-0.0.1.jar JDK.checkpoint
sleep 10

echo checkpoint process completed.