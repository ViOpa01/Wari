//package com.wizarpos.emvsample.payments_menu.transfer
//
//import android.content.Context
//import android.support.v7.app.AppCompatActivity
//import android.util.Log
//import com.google.gson.Gson
//import com.wizarpos.emvsample.models.ReceiptModel
//import com.wizarpos.emvsample.models.WithdrawalWalletResponse.WithdrawalWalletCreditModel
//import com.wizarpos.emvsample.payments_menu.utils.HashUtils
//import com.wizarpos.emvsample.R.id.txtAmount
//import com.wizarpos.emvsample.payments_menu.Services.TransferService
//import com.wizarpos.emvsample.payments_menu.models.WithdrawalDetails
//import com.wizarpos.util.StringUtil
//import com.wizarpos.util.StringUtil.getClientRef
//import kotlinx.android.synthetic.main.activity_transfer_amount_entry.*
//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.GlobalScope
//import kotlinx.coroutines.launch
//import org.jetbrains.anko.alert
//import org.jetbrains.anko.okButton
//import java.net.ConnectException
//import java.net.SocketTimeoutException
//import java.net.URLEncoder
//import java.text.SimpleDateFormat
//import java.util.*
//
//class Withdrawal : AppCompatActivity() {
//
//    public fun creditWallet(amount: Double) {
////        val progressDialog = ProgressDialog(this)
////        progressDialog.setCancelable(false)
////        progressDialog.setTitle("Wallet")
////        progressDialog.setMessage("Crediting wallet")
////        progressDialog.show()
//
//        val amountToDebit: Double
//        // toast("Wallet \n\n Crediting wallet")
//
////        val clientReference = getClientRef(this@TransferAmountEntry, "")
//        val clientReference = getClientRef(this, "")
//        lateinit var transferResponse: WithdrawalWalletCreditModel
//        lateinit var transferDetails: WithdrawalDetails
//        //amountToDebit = (txtAmount.text.toString().toDouble() * 100) + withdrawalResponse.convenienceFee*100
//        amountToDebit = amount
//        //  Log.d("credit amount", txtAmount.text.toString().toFloat().toString())
//        // Log.d("credit amount to debit",  amount.toString())
//
//        GlobalScope.launch(Dispatchers.Default) {
//            try {
//
//                val trans : TransferAmountEntry
//                trans.mWalletId
////                val action = when (mTransactionType) {
////                    TransferAmountEntry.TRANSACTION_TYPE.TRANSFER -> "transfer"
////                    TransferAmountEntry.TRANSACTION_TYPE.DEPOSIT -> "deposit"
////                    TransferAmountEntry.TRANSACTION_TYPE.WITHDRAWAL -> "withdrawal"
////                    else ->
////                }
//
////clientReference = clientReference,
//                transferDetails = WithdrawalDetails(wallet = mWalletId, username = mWalletUsername, password = mPlainPassword, pin = mEncryptedPin, type = "default", amount = amountToDebit, vendorBankCode = mBankCode, channel = "ANDROIDPOS", phone = "", paymentMethod = "card", productCode = mProductCode)
//
////                    val clientReference = getClientRef(this@TransferAmountEntry, "")
//                val clientReference = StringUtil.getClientRef(context, "")
//
//                Log.d("transfer details", mWalletId + " " + mWalletUsername + " " + mWalletPassword + " " + mEncryptedPin + " " + " " + "" + txtAmount.text.toString() + " " + mAccountNumber + " " + mBankCode)
//                val gson = Gson()
//                val jsonPayload = gson.toJson(transferDetails)
//                val base64encoded = String(org.apache.commons.codec.binary.Base64.encodeBase64(jsonPayload.toByteArray()))
//                val encoded = URLEncoder.encode(base64encoded, "UTF-8")
//                val nonce = clientReference
//                Log.d("sign", base64encoded + " " + encoded)
//                val encryptedStuff = "${nonce}IL0v3Th1sAp11111111UC4NDoV4SSWITHVICEBANKING$encoded"
//                val signature = HashUtils.sha512(encryptedStuff)
//
//
//                transferResponse = TransferService.create().withdraw(transferDetails, "application/json", signature, nonce).await();
//                // val amount = txtAmount.text.toString()
//                try {
//                    GlobalScope.launch(Dispatchers.Main) {
//                        //progressDialog.dismiss()
//
//                        if (transferResponse.status != 1) {
//                            alert {
//                                title = "Response"
//                                message = "${transferResponse.message}"
//                                okButton { }
//                            }.show()
//                        } else {
//                            alert {
//                                title = "Response"
//                                message = "${transferResponse.message}. You been debited from your wallet \n " +
//                                        "\n#${transferResponse.amountSettled} \nBeneficiary : ${transferResponse.beneficiaryName}"
//                                positiveButton(buttonText = "Print") {
//
//                                    val map = hashMapOf<String, String>(
//                                            "Reference" to transferResponse.reference,
//                                            "Message" to transferResponse.message,
//                                            "Account Name" to transferResponse.beneficiaryName,
//                                            "Bank Name" to mBankName,
//                                            "Account Number" to transferResponse.beneficiaryName,
//                                            "Amount Settled" to transferResponse.amountSettled.toString(),
//                                            "Convenience Fee" to transferResponse.convenienceFee.toString()
//                                    )
//                                    val formattedDate = SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().time)
//                                    val receiptModel = ReceiptModel(formattedDate, "Transfer", transferResponse.message, map, (txtAmount.text.toString().toFloat() * 100).toString(), transferResponse.message)
//
//                                    Log.d("debit print amountSet", transferResponse.amountSettled.toString())
//                                    Log.d("debit print fee", transferResponse.convenienceFee.toString())
//
////                                        val intent = Intent(this@TransferAmountEntry, PrintActivity::class.java)
////                                        intent.putExtra(PrintActivity.KEYS.PRINT_RECEIPT_MODEL_KEY, receiptModel)
////                                        intent.putExtra(PrintActivity.KEYS.PRINT_RECEIPT_VAS_TYPE, PrintActivity.VasType.NOT_INCLUDED)
////                                        //finish()
////                                        startActivity(intent)
//
//                                }
//                            }
//
//                        }
//
//                    }
//
//                } catch (e: Exception) {
//                    GlobalScope.launch(Dispatchers.Main) {
//                        //progressDialog.dismiss()
//                        alert {
//                            title = "Response"
//                            message = transferResponse.message
//                        }.show()
//                    }
//                }
//            } catch (e: SocketTimeoutException) {
//                GlobalScope.launch(Dispatchers.Main) {
//                    //progressDialog.dismiss()
//                    alert {
//                        title = "Error"
//                        message = "Connection taking too long to be established. Please try again"
//                        okButton { onBackPressed() }
//                    }.show()
//                }
//
//            } catch (e: ConnectException) {
//                GlobalScope.launch(Dispatchers.Main) {
//                    //   progressDialog.dismiss()
//                    alert {
//                        title = "Error"
//                        message = "Connection not established. Please try again"
//                        okButton { }
//                    }.show()
//                }
//
//            } catch (e: retrofit2.HttpException) {
//                GlobalScope.launch(Dispatchers.Main) {
//                    //progressDialog.dismiss()
//                    alert {
//                        title = "Error"
//                        message = "Error from server. Please try again"
//                        okButton { }
//                    }.show()
//                }
//            }
//        }
//    }
//
//}
