import org.apache.kafka.clients.producer.{KafkaProducer, ProducerConfig, ProducerRecord}
import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import org.apache.spark.SparkContext._
import org.apache.spark.streaming._
import org.apache.spark.streaming.kafka._

object kafkaWordCount{

	def main(args : Array[String]){

	StreamingExample.setStreamingLevels() 

	val Array(zkQuorum, group, topics, numThreads)=args
	val sparkConf = new SparkConf().setAppName("Integration with kafka").setMaster("local[*]")
	val ssc = new StreamingContext(sparkConf,Seconds(10))

	ssc.checkpoint("checkpoint")

      	val topicMap = topics.split(",").map((_, numThreads.toInt)).toMap
	val lines = KafkaUtils.createStream(ssc, zkQuorum, group, topicMap).map(_._2)
	val words = lines.flatMap(_.split(" "))
	val wordCounts = words.map(x => (x,1L)).reduceByKey(_+_)
	wordCounts.print()

	ssc.start()
	ssc.awaitTermination() 
	}
}
