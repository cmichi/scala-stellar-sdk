package stellar.sdk.op

import org.stellar.sdk.xdr.Operation.OperationBody
import org.stellar.sdk.xdr.OperationType.PAYMENT
import org.stellar.sdk.xdr._
import stellar.sdk
import stellar.sdk.{Amount, KeyPair, PublicKey, PublicKeyOps}

import scala.util.Try

/**
  * Represents <a href="https://www.stellar.org/developers/learn/concepts/list-of-operations.html#payment" target="_blank">Payment</a> operation.
  *
  * @see <a href="https://www.stellar.org/developers/learn/concepts/list-of-operations.html" target="_blank">List of Operations</a>
  */
case class PaymentOperation(destinationAccount: PublicKeyOps,
                            amount: Amount,
                            sourceAccount: Option[PublicKeyOps] = None) extends PayOperation {

  override def toOperationBody: OperationBody = {
    val op = new PaymentOp()
    val destination = new AccountID()
    destination.setAccountID(destinationAccount.getXDRPublicKey)
    op.setDestination(destination)
    op.setAsset(amount.asset.toXDR)
    val amnt = new Int64()
    amnt.setInt64(amount.units)
    op.setAmount(amnt)
    val body = new org.stellar.sdk.xdr.Operation.OperationBody()
    body.setDiscriminant(PAYMENT)
    body.setPaymentOp(op)
    body
  }

}

object PaymentOperation {
  def from(op: PaymentOp, source: Option[PublicKey]): Try[PaymentOperation] = for {
    asset <- sdk.Asset.fromXDR(op.getAsset)
    paymentOp <- Try {
      PaymentOperation(
        destinationAccount = KeyPair.fromPublicKey(op.getDestination.getAccountID.getEd25519.getUint256),
        amount = Amount(op.getAmount.getInt64.longValue, asset),
        sourceAccount = source
      )
    }
  } yield {
    paymentOp
  }
}
