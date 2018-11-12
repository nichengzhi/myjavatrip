package learnspark

import org.apache.spark._
import org.apache.spark.SparkContext._
import org.apache.spark.streaming._
import org.apache.spark.streaming.twitter._
import org.apache.spark.streaming.StreamingContext._
import Utilities._

/** Listens to a stream of tweets and saves them to disk. */
object SaveTweets {
  
  /** Our main function where the action happens */
  def main(args: Array[String]) {

    // Configure Twitter credentials using twitter.txt
    setupTwitter()
    
    // Set up a Spark streaming context named "SaveTweets" that runs locally using
    // all CPU cores and one-second batches of data
    val ssc = new StreamingContext("local[*]", "SaveTweets", Seconds(1))
    
    // Get rid of log spam (should be called after the context is set up)
    setupLogging()

    // Create a DStream from Twitter using our streaming context
    val tweets = TwitterUtils.createStream(ssc, None)
    
    // Now extract the text of each status update into RDD's using map()
    val statuses = tweets.map(status => status.getText())
    //build a new Dstream
    // Here's one way to just dump every partition of every stream to individual files:
    //statuses.saveAsTextFiles("Tweets", "txt")
    
    // But let's do it the hard way to get a bit more control.
    
    // Keep count of how many Tweets we've received so we can stop automatically
    // (and not fill up your disk!)
    var totalTweets:Long = 0
    //foreachRDD access each individual RDD in that DStream
    statuses.foreachRDD((rdd, time) => {
      // Don't bother with empty batches
      if (rdd.count() > 0) {//make sure each rdd have result
        // Combine each partition's results into a single RDD:
        //consolidate the rdd down into a single partition
        //because rdd distribute across cluster, we want to get access everything at once
        //this is very important in ingest data to database, because not every computer in cluster 
        // can access to database
        //cache(): every time spark sees ana action, it will go and compute that directed acyclic graph
        //if you do anoher action on that EDD it might do that all over again and actually
        //repross the entire RDD from the ground up
        //use cache() ensure the same RDD stays in memory, if you call multiple action on it
        // it doesn't have to go back and recreate it from scratch
        //if don't use repartition there will be multiple parts in result
        val repartitionedRDD = rdd.repartition(1).cache()
        //val repartitionedRDD = rdd.cache()
        //when use this rdd to save as textfile, because cache() spark will not do the thing above again
        // And print out a directory with the results.
        repartitionedRDD.saveAsTextFile("/Users/chengzhini/streamresult/results/"+"Tweets_" + time.milliseconds.toString)
        // Stop once we've collected 1000 tweets.
        totalTweets += repartitionedRDD.count()
        println("Tweet count: " + totalTweets)
        if (totalTweets > 1000) {
          System.exit(0)
        }
      }
    })
    //althrough this work semms sequential manner, but we just define the workflow, foreachRDD happen
    // at the same time with the DStream come in
    
    // You can also write results into a database of your choosing, but we'll do that later.
    
    // Set a checkpoint directory, and kick it all off
    ssc.checkpoint("/Users/chengzhini/streamresult/checkpoint")
    ssc.start()
    ssc.awaitTermination()
  }  
}
