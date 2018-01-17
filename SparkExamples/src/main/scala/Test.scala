
import functions._
import org.apache.spark.SparkContext

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
}

object Test {
  def main(args: Array[String]) {

    val t = new TestSpark()

    val result = t.readTexFile("./src/main/data/texto.txt").select(col("value").as("text"))
      .withColumn("lang",  language(col("text")))
      .withColumn("lemma", lemma(col("lang"), col("text")))
      .withColumn("ner",   ner(col("lang"), col("text")))
      .withColumn("pos",   pos(col("lang"), col("text")))

    result.foreach(row => {
      println(row.getString(0), "\t", row.getString(1))
      print("\t")
      for ((l, n) <- (row.getSeq[String](2) zip row.getSeq[String](4))) {
        println(l, n)
      }
    })

  }
}