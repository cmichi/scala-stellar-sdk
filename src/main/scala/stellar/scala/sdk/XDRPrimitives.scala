package stellar.scala.sdk

import org.stellar.sdk.xdr._

trait XDRPrimitives {

  def str32(s: String) = {
    val s32 = new String32
    s32.setString32(s)
    s32
  }

  def int32(i: Int) = {
    val i32 = new Int32
    i32.setInt32(i)
    i32
  }

  def uint32(i: Int) = {
    val i32 = new Uint32
    i32.setUint32(i)
    i32
  }

  def int64(l: Long) = {
    val i64 = new Int64
    i64.setInt64(l)
    i64
  }

  def uint64(l: Long) = {
    val i64 = new Uint64
    i64.setUint64(l)
    i64
  }

}
