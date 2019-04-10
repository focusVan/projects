package com.madhouse.madmax.utils

import com.google.common.base.Charsets
import org.I0Itec.zkclient.serialize.ZkSerializer

/**
  *
  * focus Create in 18:01 2019/4/9
  */
object MyZkSerializer extends ZkSerializer{
  override def serialize(data: Any): Array[Byte] = {
    data.toString.getBytes(Charsets.UTF_8)
  }

  override def deserialize(bytes: Array[Byte]): AnyRef = {
    new String(bytes, Charsets.UTF_8)
  }
}
