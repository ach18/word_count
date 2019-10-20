package vsst;

import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class IntSumReducer extends Reducer<Text,Text,Text,IntWritable> {
    private IntWritable result = new IntWritable();
    public void reduce(Text key, Text values, Context context) throws IOException, InterruptedException {
    String[] items = values.split(";");
    int a = Integer.ParseInt(items[0]);
    int b = Integer.ParseInt(items[1]);
    int c = Integer.ParseInt(items[2]);
    int sum = (2 * a * b) * Math.abs(c + (4 * a));
	result.set(sum);
	context.write(key, result);
    }
}
