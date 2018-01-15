
import functions._
import edu.stanford.nlp.simple.Sentence
import org.apache.spark.SparkContext
import org.apache.spark.rdd.RDD

import org.apache.tika.language.detect.LanguageDetector
import org.apache.tika.langdetect.OptimaizeLangDetector
//import org.apache.spark.sql.{DataFrame, SQLContext, SparkSession}
import org.apache.spark.sql._
import org.apache.spark.sql.functions._

class TestSpark {

  val spark: SparkSession = SparkSession
    .builder
    .master("local")
    .appName("SparkALS")
    .getOrCreate()


  val sc: SparkContext = spark.sparkContext
  val sqlContext: SQLContext = spark.sqlContext


  def readTexFile(name: String): DataFrame = {
    spark.read.text(name)
  }

  def countWords(data: RDD[String]): Array[(String, Int)] = {
    val counts = data.flatMap(f = line => {
      println(line)
      line.split(" ")
    })
      .map(word => (word, 1))
      .reduceByKey(_ + _)

    counts
      .sortBy(_._2, ascending = false)
      .take(2)
  }

  def readXMLFile(name: String): DataFrame = {
      val df = sqlContext.read
        .format("com.databricks.spark.xml")
        .option("rowTag", "book")
        .load(name)

      df.select("description")
  }
}

object Test {
  def main(args: Array[String]) {

    val t = new TestSpark()

    val text = t.readTexFile("./src/main/data/texto.txt")
    val result = text.select(col("value"), language(col("value")).as("lang"))

    result.show()


//    val text = t.readTexFile("./src/main/data/texto.txt")
//    text.foreach({ case (w, c) => println(w, c) })

/*    val xml = t.readXMLFile("./src/main/data/book.xml")
    val result = xml.select(col("description"), ner(col("description")).as("ner"),
                                   pos(col("description")).as("pos"),
                                   lemma(col("description")).as("lemma"),
                                   sentiment(col("description")).as("ner"))*/



  }
}