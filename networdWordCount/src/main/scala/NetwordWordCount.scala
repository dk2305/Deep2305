import org.apache.spark.SparkConf
import org.apache.spark.streaming._

object WordCount{

	def main(args : Array[String]) = {
	
		println("This is sample project for Word count from network feed")
		
		val sparkConf = new SparkConf().setAppName("Word Count").setMaster("local[2]")
		
		val ssc = new StreamingContext(sparkConf,Seconds(30))
		
		val line = ssc.socketTextStream("localhost", 9999)

		val words = line.flatMap(x => x.split(" ")).map(x => (x , 1)).reduceByKey(_+_)
		
		words.print()
		
		ssc.start()
		ssc.awaitTermination()
		
	}
}
