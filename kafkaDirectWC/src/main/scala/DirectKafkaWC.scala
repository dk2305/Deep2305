import kafka.serializer.StringDecoder
import org.apache.spark.streaming._
import org.apache.spark.streaming.kafka._
import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import org.apache.spark.SparkContext._
object KafkaWordCount{

	def main(args : Array[String]){

		//val sparkContext = new SparkContext(new SparkConf().setAppName("Spark-Kafka").setMaster("local[2]"))
		val sparkConf = new SparkConf().setAppName("Word Count").setMaster("local[2]")
		val ssc = new StreamingContext(sparkConf,Seconds(10))

		val brokers = "localhost:9092"
		val topics = "topic1"
		val topicsSet = topics.split(",").toSet
		val kafkaParams = Map[String, String]("metadata.broker.list" -> brokers)
		val messages = KafkaUtils.createDirectStream[String, String, StringDecoder, StringDecoder](ssc, kafkaParams, topicsSet)
		
		val lines = messages.map(_._2)
		val words = lines.flatMap(_.split(" "))
		val wordCounts = words.map(x => (x, 1L)).reduceByKey(_ + _)
		wordCounts.print()

		ssc.start()
		ssc.awaitTermination()
	
	}
}
