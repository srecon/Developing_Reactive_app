#!/bin/bash

url=$1
if [ -z "$url" ]
then
    url="http://192.168.64.15:30932"
fi

while true
do curl $url
sleep .5
done
