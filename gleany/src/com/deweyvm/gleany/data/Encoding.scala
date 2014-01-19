package com.deweyvm.gleany.data

object Encoding {
  private val charset = "CP437"
  def toBytes(string:String) = string.getBytes(charset)
  def fromBytes(bytes:Array[Byte], len:Int) = new String(bytes, 0, len, charset)
}
