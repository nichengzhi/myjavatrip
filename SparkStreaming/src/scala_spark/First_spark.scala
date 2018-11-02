package scala_spark

import org.apache.spark._
import org.apache.spark.SparkContext._
import org.apache.spark.streaming.StreamingContext._//_ means * in java 
object First_spark {
  
  def main(args: Array[String]) {
    //val ssc = new StreamingContext("local[*]", "LogAlarmer", Seconds(1))
      val conf = new SparkConf().setAppName("Word")
      conf.setMaster("local[*]")
      val sc = new SparkContext(conf)
      sc.stop()
    }  
  
}