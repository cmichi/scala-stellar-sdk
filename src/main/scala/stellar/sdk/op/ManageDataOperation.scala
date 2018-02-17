package stellar.sdk.op

import org.stellar.sdk.xdr.ManageDataOp
import org.stellar.sdk.xdr.Operation.OperationBody
import org.stellar.sdk.xdr.OperationType.MANAGE_DATA
import stellar.sdk.{KeyPair, PublicKeyOps}

import scala.util.Try

sealed trait ManageDataOperation extends Operation {
  val name: String
  override def toOperationBody: OperationBody = {
    val op = new ManageDataOp
    op.setDataName(str64(name))
    val body = new OperationBody
    body.setDiscriminant(MANAGE_DATA)
    body.setManageDataOp(op)
    body
  }
}

case class DeleteDataOperation(name: String, sourceAccount: Option[PublicKeyOps] = None) extends ManageDataOperation

case class WriteDataOperation(name: String, value: Array[Byte], sourceAccount: Option[PublicKeyOps] = None) extends ManageDataOperation {
  override def toOperationBody: OperationBody = {
    val body = super.toOperationBody
    body.getManageDataOp.setDataValue(dataValue(value))
    body
  }
}


object ManageDataOperation {
  def from(op: ManageDataOp): Try[ManageDataOperation] = Try {
    val name = op.getDataName.getString64
    Option(op.getDataValue) match {
      case Some(value) => WriteDataOperation(name, value.getDataValue)
      case _ => DeleteDataOperation(name)
    }
  }

}
