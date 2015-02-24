#! /bin/bash
#File split file using shell
echo `date`
split -d -nl/16 --additional-suffix=.txt $1 
mv x* /home/ubuntu/swift-cloud-tutorial/part05/input/
echo `date`