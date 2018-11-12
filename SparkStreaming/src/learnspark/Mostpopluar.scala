package learnspark
import org.apache.spark._
import org.apache.spark.SparkContext._
import org.apache.spark.streaming._
import org.apache.spark.streaming.twitter._
import org.apache.spark.streaming.StreamingContext._
import Utilities._


object Mostpopluar {
  def main(args: Array[String]) {
    
    // Configure Twitter credentials using twitter.txt
    setupTwitter()
    
    // Set up a Spark streaming context named "AverageTweetLength" that runs locally using
    // all CPU cores and one-second batches of data
    val ssc = new StreamingContext("local[*]", "MaxTweetLength", Seconds(1))//decide how long to split a rdd
    
    // Get rid of log spam (should be called after the context is set up)
    setupLogging()

    // Create a DStream from Twitter using our streaming context
    val tweets = TwitterUtils.createStream(ssc, None)
    
    // Now extract the text of each status update into DStreams using map()
    val statuses = tweets.map(status => status.getText())
    //get key value pair hashtag
    
    val words = statuses.flatMap(text => text.split(" "))
    //filter the word into hashtag
    val hashtags = words.filter(word => word.startsWith("#"))
    //generate key value pairs
    val hashtagKeyValues = hashtags.map(tag => (tag,1))
    //reducebyKeyandwindow can reduce value by certain window interval and slide interval
    //val hashtagCounts = hashtagkeyvalue.reduceByKeyAndWindow((x,y) => x+y, (x,y) => x-y,Seconds(300),Seconds(1))
    //x,y x-y will make it run faster
    //就是把新进入窗口数据中各个单词个数“增加”到各个单词统计结果上，
    //同时把离开窗口数据中各个单词的统计个数从相应的统计结果中“减掉”
    //!!reducebywindow application will give us a single rdd
    //so you can use this to do transform
    // Sort the results by the count values
    //sortedResults = hashtagCounts.transform(rdd => rdd.sortBy(x => x._2, false))
    //this also create a new dstream
    //transform(func) : 通过对源DStream的每个RDD应用RDD-to-RDD函数，创建一个新的DStream。这个可以在DStream中的任何RDD操作中使用
    // Print the top 10
    //sortedResults.print
    val hashtagCounts = hashtagKeyValues.reduceByKeyAndWindow( (x,y) => x + y, (x,y) => x - y, Seconds(300), Seconds(1))
    //  You will often see this written in the following shorthand:
    //val hashtagCounts = hashtagKeyValues.reduceByKeyAndWindow( _ + _, _ -_, Seconds(300), Seconds(1))
    
    // Sort the results by the count values
    val sortedResults = hashtagCounts.transform(rdd => rdd.sortBy(x => x._2, false))
    
    // Print the top 10
    sortedResults.print(5)
    // Set a checkpoint directory, and kick it all off
    // I could watch this all day!
    ssc.checkpoint("/Users/chengzhini/streamresult/checkpoint")
    ssc.start()
    ssc.awaitTermination()
    
  }
}