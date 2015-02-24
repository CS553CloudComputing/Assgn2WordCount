package org.cs553ksingh14;

import java.io.IOException;
import java.util.*;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.conf.*;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapred.*;
import org.apache.hadoop.util.*;

public class WordCount {

  public static class Map extends MapReduceBase implements Mapper<LongWritable, Text, Text, IntWritable> 
  {
    private Text _word = new Text();
    private final static IntWritable oneIW = new IntWritable(1);
    public void map(LongWritable _key, Text _value, OutputCollector<Text, IntWritable> _output, Reporter _reporter) throws IOException 
    {
      String lineString = _value.toString();
      StringTokenizer stringTokenizer = new StringTokenizer(lineString);
      while (stringTokenizer.hasMoreTokens()) 
      {
        _word.set(stringTokenizer.nextToken());
        _output.collect(_word, oneIW);
      }
    }
  }

  public static class Reduce extends MapReduceBase implements Reducer<Text, IntWritable, Text, IntWritable> 
  {
    public void reduce(Text _key, Iterator<IntWritable> _values, OutputCollector<Text, IntWritable> _output, Reporter _reporter) throws IOException 
    {
      int sum = 0;
      while (_values.hasNext()) 
      {
        sum += _values.next().get();
      }
      IntWritable sumIW = new IntWritable(sum);
      _output.collect(_key, sumIW);
    }
  }

  public static void main(String[] args) throws Exception 
  {
    JobConf jobConf = new JobConf(WordCount.class);
    jobConf.setJobName("wordcount_cs553");
    jobConf.setMapperClass(Map.class);
    jobConf.setCombinerClass(Reduce.class);
    jobConf.setReducerClass(Reduce.class);
    
    jobConf.setOutputKeyClass(Text.class);
    jobConf.setOutputValueClass(IntWritable.class);
      
    jobConf.setInputFormat(TextInputFormat.class);
    jobConf.setOutputFormat(TextOutputFormat.class);

    FileInputFormat.setInputPaths(jobConf, new Path(args[0]));
    FileOutputFormat.setOutputPath(jobConf, new Path(args[1]));

    JobClient.runJob(jobConf);
  }
}
