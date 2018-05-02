import org.apache.spark._
import org.apache.spark.streaming._
import org.apache.spark.streaming.StreamingContext._

object wordCount{

	def main(args : Array[String]){

		val conf = new SparkConf().setAppName("File Stream")
		val ssc = new StreamingContext(conf,Seconds(10))

		val lines = ssc.textFileStream("/home/edureka/Deepak/Data")
		val words = lines.flatMap(x => x.split("|"))
		val wordCount = words.map(x => (x,1)).reduceByKey(_+_)

		wordCount.print()
		ssc.start()
		ssc.awaitTermination()
	}
}
