#! /bin/bash
#Word count of individual file 
echo `date`
cat /home/ubuntu/swift-cloud-tutorial/part05/outwc/*.out > wcmerge.txt
sort wcmerge.txt>wordcount.txt
awk -F: '{a[$1]+=$2;}END{for(i in a)print i", "a[i];}' wordcount.txt |cat -v > wordcount-swift.txt
echo `date`
