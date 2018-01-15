name := "SparkExamples"

version := "0.1"

scalaVersion := "2.11.8"

val sparkVersion = "2.2.0"

val coreNLP = "3.8.0"

val tika = "1.17"


//resolvers ++= Seq(
//  "apache-snapshots" at "http://repository.apache.org/snapshots/"
//)

libraryDependencies ++= Seq(
  //Apache Spark
  "org.apache.spark" %% "spark-core"      % sparkVersion,
  "org.apache.spark" %% "spark-sql"       % sparkVersion,
  "org.apache.spark" %% "spark-mllib"     % sparkVersion,
  "org.apache.spark" %% "spark-streaming" % sparkVersion,
  "org.apache.spark" %% "spark-hive"      % sparkVersion,

  //XML Reader
  "com.databricks" %% "spark-xml" % "0.4.1",

  //Stanford's CoreMLP
  "edu.stanford.nlp" % "stanford-corenlp" % coreNLP,
  "edu.stanford.nlp" % "stanford-corenlp" % coreNLP classifier "models",
  "edu.stanford.nlp" % "stanford-corenlp" % coreNLP classifier "models-spanish",
  "edu.stanford.nlp" % "stanford-corenlp" % coreNLP classifier "models-french",
  "edu.stanford.nlp" % "stanford-parser"  % coreNLP,

  //Apache TIKA (language detection)
  "org.apache.tika" % "tika-core"       % tika,
  "org.apache.tika" % "tika-langdetect" % tika,
  "com.google.guava" % "guava"          % "23.0"
)