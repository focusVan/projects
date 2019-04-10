package com.madhouse.madmax.utils

import java.time.{Instant, LocalDateTime, ZoneId}
import com.madhouse.madmax.utils.Constant.DAY

/**
  *
  * focus Create in 17:13 2019/4/9
  */
object Methods extends Serializable {
  def log(s: String): Unit = {
    println(s"[${timestamp2Date()}] : $s")
  }

  def timestamp2Date(zone: String = "GMT+8"): String = {
    val now = System.currentTimeMillis();
    val ts = now - now % 1000;
    val triggerTime: LocalDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(ts), ZoneId.of(zone))
    triggerTime.toString
  }

  def timeProcess(time: Long, cell: Long): Long = {
    time.toString.length match {
      case 10 => time - time % cell
      case 13 => val second = time / 1000
        second - second % cell
    }
  }

  def requestStatus(status: Int): Int = {
    status match {
      case 200 => 0
      case 204 => 1
      case 400 | 500 => 2
      case _ => 1
    }
  }

  def mkPath(ts: Long): String = {
    val date = timeProcess(ts, DAY)
    s"$date/$ts"
  }

  def getHost(servers: String): String = {
    servers.split(",")(0).split(":")(0)
  }

  def getPort(servers: String): Int = {
    servers.split(",")(0).split(":")(1).toInt
  }
}
