package com.madhouse.madmax

import com.madhouse.madmax.utils.Constant._
import org.apache.commons.cli._
import com.madhouse.madmax.utils.ConfigReader._
import org.apache.spark.SparkConf
import org.apache.spark.streaming.{Seconds, StreamingContext}
import com.madhouse.madmax.utils.OffsetUtils._
import com.madhouse.madmax.utils.Methods._
import kafka.message.MessageAndMetadata
import org.apache.spark.streaming.kafka.{HasOffsetRanges, KafkaUtils, OffsetRange}
import kafka.serializer.{DefaultDecoder, StringDecoder}
import org.apache.spark.sql.SparkSession
import com.madhouse.madmax.utils.AvroUtils
import avro.{ClickTrack, ImpressionTrack, MediaBid, WinNotice}

/**
  *
  * focus Create in 14:59 2019/5/5
  */
object LogCollector {
  def main(args: Array[String]): Unit = {
    var offset = ZK
    var logType = WIN

    val option = new Options()
    option.addOption("h", "help", false, "help message")
    option.addOption("o", "offset", true, "set the offset of kafka:earliest, latest, zk")
    option.addOption("t", "type", true, "the type of log: req, imp, clk, win")
    val formatstr = "sh run.sh mesos ..."
    val formatter = new HelpFormatter
    val parser = new PosixParser

    var cl:CommandLine = null
    try
      cl = parser.parse(option, args)
    catch {
      case e:ParseException => e.printStackTrace()
        formatter.printHelp(formatstr, option)
        System.exit(1)
    }

    if (cl.hasOption("h")) {
      formatter.printHelp(formatstr, option)
      System.exit(0)
    }

    if (cl.hasOption("o"))
      offset = cl.getOptionValue("o")
    if (cl.hasOption("t"))
      logType = cl.getOptionValue("t")

    val (maxRatePerPartition, interval, topics, startOffsetWithoutZk, partitions) = logType match {
      case IMP => (impMaxRatePerPartition, impStreamingInterval, impTopic, impStartOffset, impPartitions)
      case CLK => (clkMaxRatePerPartition, clkStreamingInterval, clkTopic, clkStartOffset, clkPartitions)
      case WIN => (winMaxRatePerPartition, winStreamingInterval, winTopic, winStartOffset, winPartitions)
    }

    val sparkConf = new SparkConf().setAppName(s"logCollector_$logType").set("spark.streaming.kafka.maxRatePerPartition", s"$maxRatePerPartition")
    val streamingContext = new StreamingContext(sparkConf, Seconds(interval))

    val topicSet = topics.split(",").toSet
    var kafkaParams = Map[String, String]("metadata.broker.list" -> bootstrapServers, "group.id" -> (s"Log_collector_" + topics.replaceAll(",", "_")))

    //使用zookeeper维护offset
    val fromOffset = getOffset(logType, offset)

    val messages = if (fromOffset.nonEmpty) {
      for(o <- fromOffset) {
        log(s"------- topic: ${o._1.topic}, partition: ${o._1.partition}, offset: ${o._2}")
      }
      KafkaUtils.createDirectStream[String, Array[Byte], StringDecoder, DefaultDecoder, (String, Array[Byte])](
        streamingContext, kafkaParams, fromOffset, (mmd: MessageAndMetadata[String, Array[Byte]]) => (mmd.key, mmd.message))
    } else {
      log("##### the offset is not generated in zookeeper....")
      if (startOffsetWithoutZk.equalsIgnoreCase(EARLIEST)) {
        kafkaParams += ("auto.offset.reset" -> "smallest")
      }
      KafkaUtils.createDirectStream[String, Array[Byte], StringDecoder, DefaultDecoder](streamingContext, kafkaParams, topicSet)
    }

    var offsetRanges = Array[OffsetRange]()
    //提取kafka<K, V>里的V，value是我们需要处理的message内容
    val logBeforeDecoded = messages.transform(rdd => {
      offsetRanges = rdd.asInstanceOf[HasOffsetRanges].offsetRanges
      rdd.map(r => r._2)
    })

    logBeforeDecoded.repartition(partitions).foreachRDD(rdd => {
      if (!rdd.isEmpty()) {
        val sparkSession = SparkSession.builder.config(sparkConf).getOrCreate()
        import sparkSession.implicits._
        //将DStream[String] 转换为DStream[ts, object]
        val jsonDs = rdd.map(r => {
          logType match {
            case REQ => val mediaBid: MediaBid = AvroUtils.decode(r, MediaBid.SCHEMA$).asInstanceOf[MediaBid]
              val ts = timeProcess(mediaBid.getTs, HALF_HOUR)
              val jsonString = if (mediaBid.getResponse == null) mediaBid.toString.replaceAll(", \"response\": null", "") else mediaBid.toString
              (ts, jsonString)
            case IMP => val impressionTrack: ImpressionTrack = AvroUtils.decode(r, ImpressionTrack.SCHEMA$).asInstanceOf[ImpressionTrack]
              val ts = timeProcess(impressionTrack.getTs, HALF_HOUR)
              (ts, impressionTrack.toString)
            case CLK => val clickTrack: ClickTrack = AvroUtils.decode(r, ClickTrack.SCHEMA$).asInstanceOf[ClickTrack]
              val ts = timeProcess(clickTrack.getTs, HALF_HOUR)
              (ts, clickTrack.toString)
            case WIN => val winNotice: WinNotice = AvroUtils.decode(r, WinNotice.SCHEMA$).asInstanceOf[WinNotice]
              val ts = timeProcess(winNotice.getTs, HALF_HOUR)
              (ts, winNotice.toString)
          }
        }).toDS().cache()
        //获取60秒内所有kafka消息里的ts
        val tss = jsonDs.map(_._1).distinct().collect()
        log(s"##### there are ${tss.length} timestamps in current dataset...")
        for (ts <- tss) {
          //按照ts过滤DStream[ts, object]，将DStream[ts, object]里的object写到对应的ts路径下
          val df = sparkSession.read.json(jsonDs.filter(r => r._1 == ts).map(r => r._2))
          val dateTime = mkPath(ts)
          val path = s"$hdfsBasePath/$topics/$dateTime"
          log(s"##### start to write df of logs to hdfs, path is $path")
          df.coalesce(partitions).write.format(FORMAT).mode(MODE).save(path)
          log(s"##### saving df of $topics logs to hdfs finished...")
        }
      } else {
        showOffset(logType, offsetRanges)
        log(s"##### rdd of $topics logs from kafka is empty, and no needing update offset in zookeeper...")
      }
    })

    streamingContext.start()
    streamingContext.awaitTermination()
  }
}
