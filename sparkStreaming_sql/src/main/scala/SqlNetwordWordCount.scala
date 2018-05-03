import org.apache.spark.sql.SparkSession
import org.apache.spark._
import org.apache.spark.rdd.RDD
import org.apache.spark.streaming._
import org.apache.spark.streaming.StreamingContext._
import org.apache.spark.sql.types._


object SqlNetworkWordCount{

	def main(args : Array[String]){
		
		if(args.length < 2 ){
			
			System.err.println("Usage: NetworkWordCount <hostname> <port>")
			System.exit(1)
		}

		StreamingExamples.setStreamingLevels()

		val sprakConf = new SparkConf().setAppName("SqlNetworkWordCount")
		val ssc = new StreamingContext(sprakConf,Seconds(10))


		val lines = ssc.socketTextStream(args(0),args(1).toInt)
		val words = lines.flatMap(x => x.split(" "))
		
		words.foreachRDD{ (rdd: RDD[String],time:Time) =>
			val spark = SparkSessionSingleton.getInstance(rdd.sparkContext.getConf) 
			//val spark = SparkSesssion.builder().appName("SPark SQL").config("spark.some.config.option", "some-value").getOrCreate()
			import spark.implicits._

			val wordDataFrame = rdd.map(w => Record(w)).toDF()
	
			wordDataFrame.createOrReplaceTempView("words")
			val wordCountsDataFrame = spark.sql("select word, count(*) as total from words group by word")
      			println(s"========= $time =========")
			wordCountsDataFrame.show()
		}

		ssc.start()
		ssc.awaitTermination()
	}
}

/** Case class for converting RDD to DataFrame */
case class Record(word: String)


object SparkSessionSingleton{
	@transient private var instance: SparkSession = _

	def getInstance(sparkConf: SparkConf): SparkSession = {
	
		if(instance == null){
			instance = SparkSession.builder.config(sparkConf).getOrCreate()
		}
		instance
	}
}	
