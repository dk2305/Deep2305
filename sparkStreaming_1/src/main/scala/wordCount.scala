import org.apache.spark._
import org.apache.spark.streaming._
import org.apache.spark.streaming.StreamingContext._

object wordCount{

	def main(args : Array[String]){
	
		val conf = new SparkConf().setAppName("Word Count").setMaster("local[2]")
		val ssc = new StreamingContext(conf,Seconds(10))

		val lines = ssc.socketTextStream("localhost",9999)
		val words = lines.flatMap(x => x.split(" ")).map(x => (x,1))
		val wordCounts = words.reduceByKey(_+_)

		wordCounts.print()
	
		ssc.start()
		ssc.awaitTermination()
	
	}

}
