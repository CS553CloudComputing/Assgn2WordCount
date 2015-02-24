All the results are uploaded at:
https://www.dropbox.com/s/jg8xjj45r68s3pv/Programming%20assignment%202.zip?dl=0


SharedMemory - 
wc.jar file in the source folder can be executed as follows:

$ java -jar wc.jar <num of threads> <input file>

e.g.
$ java -jar wc.jar 2 wiki10gb


Hadoop - 
wordcount.jar file in the source folder needs to be executed as follows to get the result:

$ hadoop -jar wordcount.jar org.cs553ksingh14.WordCount <input> <output>

e.g.
$ hadoop -jar wordcount.jar org.cs553ksingh14.WordCount input output

In order to alter the number of slave nodes, we need to make changes in the slaves file at conf/slaves
Write the public access domain name of the slave node to the conf/slaves file.


Swift - 
Copy the following files to the 'part05' folder in the github clone of swift-cloud-tutorial
>p5.swift
>filemerge.sh
>filewordcount.sh
>splitfile.sh

>modify the swift.conf file to set the no. of workers
>Create 'input' folder in the current directory

> ./splitfile.sh <datafile-name>                               	run splitfile.sh script
> swift p5.swift						run the swift code to get the word count
> ./filemerge.sh	