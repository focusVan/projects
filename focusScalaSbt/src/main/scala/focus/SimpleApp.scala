package focus
import org.apache.spark.sql.SparkSession

/**
  *
  * focus Create in 10:30 2018/11/29
  */
object SimpleApp {
  def main(args: Array[String]) {
    val logFile = "/usr/local/spark/README.md"
    val spark = SparkSession.builder().appName("SimpleApp").getOrCreate()
    var logData = spark.read.textFile(logFile).cache()
    val numAs = logData.filter(line => line.contains("a"))
    val numOs = logData.filter(line => line.contains("o"))
    println(s"Lines with a: $numAs, Lines with o: $numOs")
  }
}
