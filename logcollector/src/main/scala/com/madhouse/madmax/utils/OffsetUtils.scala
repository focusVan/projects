package com.madhouse.madmax.utils

import org.I0Itec.zkclient.ZkClient
import com.madhouse.madmax.utils.ConfigReader._
import com.madhouse.madmax.utils.Constant._
import com.madhouse.madmax.utils.Methods._
import kafka.api.{OffsetRequest, PartitionOffsetRequestInfo, TopicMetadataRequest}
import kafka.common.TopicAndPartition
import kafka.consumer.SimpleConsumer
import kafka.utils.ZkUtils
import org.apache.spark.streaming.kafka.OffsetRange

/**
  *
  * focus Create in 17:33 2019/4/9
  */
object OffsetUtils extends Serializable {
  var zkClient: ZkClient = _
  var offsetPath = ""
  var topic = ""

  def init(logType: String): Unit = {
    zkClient = new ZkClient(zookeeperServers, 50000, 50000)
    zkClient.setZkSerializer(MyZkSerializer)
    val t = logType.toLowerCase
    topic = t match {
      case REQ => reqTopic
      case IMP => impTopic
      case WIN => winTopic
      case CLK => clkTopic
    }
    offsetPath = zkBasePath + "/" + topic
    log(s"##### init finished,offset path of $topic in zookeeper is $offsetPath")
  }

  def getOffset(logType: String, offset: String): Map[TopicAndPartition, Long] = {
    if(zkClient == null || zkClient.getShutdownTrigger) {
      init(logType)
    }
    var fromOffset: Map[TopicAndPartition, Long] = Map()

    var children = zkClient.countChildren(offsetPath)
    if (children > 0) {
      val topicList = List(topic)
      val req = new TopicMetadataRequest(topicList, 0)
      val leaderConsumer = new SimpleConsumer(Methods.getHost(bootstrapServers), Methods.getPort(bootstrapServers), 10*1000, 10000, "OffsetLookup")
      val res = leaderConsumer.send(req)
      val topicMetaOption = res.topicsMetadata.headOption
      val partitions = topicMetaOption match {
        case Some(tm) => tm.partitionsMetadata.map(pm => (pm.partitionId, pm.leader.get.host)).toMap[Int, String]
        case None => Map[Int, String]()
      }

      if (offset.equalsIgnoreCase(EARLIEST)) {
        for (i <- 0 until children) {
          val tp = TopicAndPartition(topic, i)
          val requestMin = OffsetRequest(Map(tp -> PartitionOffsetRequestInfo(OffsetRequest.EarliestTime, 1)))
          val consumerMin = new SimpleConsumer(partitions(i), Methods.getPort(bootstrapServers), 10*1000, 10000, "getMinOffset")
          val currentOffsets = consumerMin.getOffsetsBefore(requestMin).partitionErrorAndOffsets(tp).offsets
          fromOffset += (tp -> currentOffsets.head)
        }
      } else if (offset.equalsIgnoreCase(LATEST)) {
        for (i <- 0 until children) {
          val tp = TopicAndPartition(topic, i)
          val requestMax = OffsetRequest(Map(tp -> PartitionOffsetRequestInfo(OffsetRequest.LatestTime, 1)))
          val consumerMax = new SimpleConsumer(partitions(i), Methods.getPort(bootstrapServers), 10*1000, 10000, "getMaxOffset")
          val currentOffsets = consumerMax.getOffsetsBefore(requestMax).partitionErrorAndOffsets(tp).offsets'
          fromOffset += (tp -> currentOffsets.head)
        }
      } else {
        for (i <- 0 until children) {
          val tp = TopicAndPartition(topic, i)
          val partionOffset = zkClient.readData[String](s"$offsetPath/$i")
          val requestMin = OffsetRequest(Map(tp -> PartitionOffsetRequestInfo(OffsetRequest.EarliestTime, 1)))
          val consumerMin = new SimpleConsumer(partitions(i), Methods.getPort(bootstrapServers), 10*1000, 10000, "getMinOffset")
          val currentOffsets = consumerMin.getOffsetsBefore(requestMin).partitionErrorAndOffsets(tp).offsets
          var nextOffsets = partionOffset.toLong
          if (currentOffsets.nonEmpty && nextOffsets < currentOffsets.head) {
            nextOffsets = currentOffsets.head
          }
          fromOffset += (tp -> nextOffsets)
        }
      }
    } else {

    }
    fromOffset
  }

  def writeOffset(logType: String, offsetRanges: Array[OffsetRange]): Unit = {
    if (zkClient == null || zkClient.getShutdownTrigger) {
      init(logType)
    } else {
      for (offset <- offsetRanges) {
        val zkPath = s"$offsetPath/${offset.partition}"
        ZkUtils.updatePersistentPath(zkClient, zkPath, offset.untilOffset.toString)
      }
    }
  }

  def showOffset(logType: String, offsetRanges: Array[OffsetRange]): Unit = {
    if (zkClient == null || zkClient.getShutdownTrigger) {
      init(logType)
    } else {
      for (offset <- offsetRanges) {
        log(s"--------- topic:${offset.topic}, partition:${offset.partition}, fromOffset:${offset.fromOffset}, untilOffset:${offset.untilOffset}")
      }
    }
  }
}
