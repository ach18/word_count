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
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

public class WordCount extends Configured implements Tool {
    public static void main(String[] args) throws Exception {
	int res = ToolRunner.run(new Configuration(), new WordCount(), args);
	System.exit(res);
    }

    public int run(String[] args) throws Exception {
	if (args.length != 2) {
	    System.err.println("usage: " + getClass().getSimpleName() + " [generic options] <inputfile> <outputdir>");
	    System.exit(1);
	}
	Configuration conf = this.getConf();
	Job job = Job.getInstance(conf, "Word Count");
	job.setJarByClass(WordCount.class);
	job.setMapperClass(TokenizerMapper.class);
	//job.setCombinerClass(IntSumReducer.class);
	job.setReducerClass(IntSumReducer.class);
	job.setOutputKeyClass(Text.class);
	job.setOutputValueClass(IntWritable.class);
	FileInputFormat.addInputPath(job, new Path(args[0]));
	FileOutputFormat.setOutputPath(job, new Path(args[1]));
	return job.waitForCompletion(true) ? 0 : 1;
    }
}
