package com.madhouse.madmax.utils

import org.I0Itec.zkclient.ZkClient
import com.madhouse.madmax.utils.ConfigReader._
import com.madhouse.madmax.utils.Constant._
import com.madhouse.madmax.utils.Methods._
import kafka.common.TopicAndPartition

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

    fromOffset
  }
}
