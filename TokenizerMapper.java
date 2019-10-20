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

public class TokenizerMapper extends Mapper<Object, Text, Text, Text> {
    private Text word = new Text();
    private javax.xml.soap.Text a = new Text();
    private org.w3c.dom.Text b = new Text();
    private org.w3c.dom.Text c = new Text();

    public void map(Object key, Text value, Context context) throws IOException, InterruptedException {
        StringTokenizer itr = new StringTokenizer(value.toString());
        while (itr.hasMoreTokens()) {
            word.set(itr.nextToken());
            a.set(itr.nextToken());
            b.set(itr.nextToken());
            c.set(itr.nextToken());
	        context.write(word, new Text(a.toString() + ";" + b.toString() + ";" + c.toString()));
	    }
    }
}
