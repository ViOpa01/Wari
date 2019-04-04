package com.wizarpos.emvsample.payments_menu.transfer

//import AmpEmvL2Android.AMPDevice
import android.app.Activity
import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import com.google.gson.Gson
import com.wizarpos.emvsample.models.LookupSuccessModel
import com.iisysgroup.androidlite.models.ReceiptModel
import com.wizarpos.emvsample.models.WithdrawalLookupSuccessModel
import com.iisysgroup.androidlite.models.WithdrawalWalletResponse.WithdrawalWalletCreditModel
import com.iisysgroup.androidlite.models.transfer.TransferSuccessModel
import com.wizarpos.emvsample.payments_menu.Services.TransferService
import com.iisysgroup.androidlite.payments_menu.utils.HashUtils
import com.iisysgroup.poslib.deviceinterface.DeviceState
import com.iisysgroup.poslib.utils.AccountType
import com.wizarpos.emvsample.MainApp
import com.wizarpos.emvsample.R
import com.wizarpos.emvsample.VasPurchaseProcessor
import com.wizarpos.emvsample.activity.login.Helper
import com.wizarpos.emvsample.activity.login.securestorage.SecureStorage
import com.wizarpos.emvsample.activity.login.securestorage.SecureStorageUtils
import com.wizarpos.emvsample.payments_menu.BasePaymentActivity
import com.wizarpos.emvsample.payments_menu.models.AccountLookUpDetailTransfer
import com.wizarpos.emvsample.payments_menu.models.AccountLookUpDetailWithdrawal
import com.wizarpos.emvsample.payments_menu.models.TransferDetails
import com.wizarpos.emvsample.payments_menu.models.WithdrawalDetails
import com.wizarpos.util.PinAlertUtils
import com.wizarpos.util.SharedPreferenceUtils
import com.wizarpos.util.StringUtil.getClientRef
import kotlinx.android.synthetic.main.activity_transfer_amount_entry.*
import kotlinx.android.synthetic.main.single_transaction.*
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch
import org.jetbrains.anko.alert
import org.jetbrains.anko.indeterminateProgressDialog
import org.jetbrains.anko.okButton
import org.jetbrains.anko.toast
import java.math.BigDecimal
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.URLEncoder
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.*

class TransferAmountEntry : AppCompatActivity(), View.OnClickListener  {

    enum class TRANSACTION_TYPE {TRANSFER, WITHDRAWAL, DEPOSIT}

    private lateinit var mConvenienceFee : String
    private lateinit var mRrn : String

    private lateinit var mEncryptedPin : String


    lateinit var withdrawalResponse : WithdrawalLookupSuccessModel

    private val mTerminalId by lazy {
        SharedPreferenceUtils.getTerminalId(this)
    }

//    private val printerInteractor by lazy {
//        PrinterInteractor.getInstance(AMPDevice(this))
//    }

    private val mTransactionType by lazy {
        intent.getSerializableExtra(TransferBankSelection.TRANSACTION_TYPE) as TRANSACTION_TYPE
    }

    private val progressDialog by lazy {
        indeterminateProgressDialog(message = "Processing")
    }

    private val mBankName by lazy {
        intent.getStringExtra(TransferBankSelection.BANK_NAME)
    }

    private val mBankCode by lazy {
        intent.getStringExtra(TransferBankSelection.BANK_CODE)
    }

    private val mAccountNumber by lazy {
        intent.getStringExtra(TransferBankSelection.ACCOUNT_NUMBER)
    }

    private val mWalletUsername by lazy {
        SharedPreferenceUtils.getPayviceUsername(this)
    }

    private val mPlainPassword by lazy {
        SharedPreferenceUtils.getPlainPassword(this)
    }
//  private val mUserPhone by lazy {
//        SharedPreferenceUtils.getUserPhone(this)
//    }

    lateinit var mWalletId: String

    private val mWalletPassword by lazy {
        SharedPreferenceUtils.getPayvicePassword(this)
    }


    private lateinit var mAccountName : String
    private lateinit var mProductCode : String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transfer_amount_entry)
        //Log.e("details", mPlainPassword )
        initializeAmountEntryElements()
    }

    override fun onClick(view: View) {

        val displayStr = StringBuilder(txtAmount.text.toString())
        when (view.id) {
            R.id.btn1 -> fixAppend(displayStr, "1")
            R.id.btn2 -> fixAppend(displayStr, "2")
            R.id.btn3 -> fixAppend(displayStr, "3")
            R.id.btn4 -> fixAppend(displayStr, "4")
            R.id.btn5 -> fixAppend(displayStr, "5")
            R.id.btn6 -> fixAppend(displayStr, "6")
            R.id.btn7 -> fixAppend(displayStr, "7")
            R.id.btn8 -> fixAppend(displayStr, "8")
            R.id.btn9 -> fixAppend(displayStr, "9")
            R.id.btn0 -> fixAppend(displayStr, "0")
            R.id.btn00 -> fixAppend(displayStr, "00")
            R.id.btnenter -> onEnterPressed()
            R.id.btnclr -> {
                if (displayStr.length == 1) {
                    displayStr.deleteCharAt(0)
                    fixDelete(displayStr)
                } else if (displayStr.length > 1) {
                    val index = displayStr.length - 1
                    displayStr.deleteCharAt(index)
                    fixDelete(displayStr)
                }
            }
            R.id.btncancel -> onBackPressed()

        }
    }

    private fun onEnterPressed() {
        if (txtAmount.text.toString().isNotEmpty()){
            if (mTransactionType.toString().equals("WITHDRAWAL")){
                verifyWithdrawalAccountDetails()
            }else if (mTransactionType.toString().equals("TRANSFER")){
                verifyTransferAccountDetails()
            }


        } else {
            txtAmount.error = "Enter valid amount"
        }

    }

//    private fun payWithWallet(response: LookupSuccessModel) {
//        progressDialog.show()
//        val amount = (txtAmount.text.toString().toDouble() * 100).toInt()
//        Log.d("amount ", amount.toString())
//
//        val action = when (mTransactionType){
//            TRANSACTION_TYPE.TRANSFER -> "transfer"
//            TRANSACTION_TYPE.DEPOSIT -> "deposit"
//            TRANSACTION_TYPE.WITHDRAWAL -> "withdrawal"
//        }
//
//
//        val view = View.inflate(this, R.layout.activity_enter_pin, null)
//        val encryptedPassword = SecureStorage.retrieve(Helper.STORED_PASSWORD, "")
//
//        PinAlertUtils.getPin(this, view){
//            val encryptedPin = SecureStorageUtils.hashIt(it!!, encryptedPassword)
//
//            launch(CommonPool){
//                val transferDetails = TransactionDetails(action = action, beneficiary = mAccountNumber, vendorBankCode =mBankCode, walletID = mWalletId, username = mWalletUsername, password = mWalletPassword, amount = amount.toString(), method = "cash", pin = encryptedPin!!)
//                val gson = Gson()
//                val jsonPayload = gson.toJson(transferDetails)
//
//
//                val base64encoded = String(org.apache.commons.codec.binary.Base64.encodeBase64(jsonPayload.toByteArray()))
//                val encoded = URLEncoder.encode(base64encoded, "UTF-8")
//                val nonce = "${Calendar.getInstance().timeInMillis}$mWalletId$encoded"
//
//                val encryptedStuff = "${nonce}BisiG03sToSk00lMak3sM0nEyAnDCanN0wS3ndMon3yViaTh1sAP1$encoded"
//                val signature = HashUtils.sha512(encryptedStuff)
//
//                try {
//                    val response =  TransferService.create().transfer(transferDetails, nonce = nonce, signature = signature).await()
//
//                    val isApproved = response.status == 1
//
//                    val status = if (isApproved) {
//                        "Approved"
//                    } else {
//                        "Declined"
//                    }
//
//                    val transferDetails = TransferDetails(mTransactionType, isApproved, mAccountName, transferDetails.amount, mConvenienceFee, mBankName, mTerminalId)
//
//                    val amounts = transferDetails.amount.toFloat()/100
//                    val fees = transferDetails.fee.toFloat()/100
//                    Log.d("amounts", amounts.toString())
//                        val map = hashMapOf<String, String>(
//                                "Transaction approved" to isApproved.toString().capitalize(),
//                                "Terminal ID" to transferDetails.terminalId,
//                                "Bank Name" to transferDetails.bankName,
//                                "Beneficiary" to transferDetails.beneficiary,
//
//                                //"Amount" to ( transferDetails.amount.toDouble().toInt() /100).toString(),
//                                "Amount" to ("₦"+(amounts).toString()),
//                                "Fee" to ("₦"+fees).toString()
//                        )
//
//                        val receiptModel = ReceiptModel("", "Transfer using wallet", status, map, (amounts).toString(), "")
//
//                        val intent = Intent(this@TransferAmountEntry, PrintActivity::class.java)
//                        intent.putExtra(PrintActivity.KEYS.PRINT_RECEIPT_MODEL_KEY, receiptModel)
//                        intent.putExtra(PrintActivity.KEYS.PRINT_RECEIPT_VAS_TYPE, PrintActivity.VasType.NOT_INCLUDED)
//                        startActivity(intent)
//
//
//
//                } catch (e : ConnectException){
//                    launch(UI){
//                        progressDialog.dismiss()
//                        alert {
//                            title = "Error"
//                            message = "Connection not established. Please try again"
//                            okButton {  }
//                        }.show()
//                    }
//                } catch (e : SocketTimeoutException){
//                    launch(UI){
//                        progressDialog.dismiss()
//                        alert {
//                            title = "Error"
//                            message = "Connection taking too long to be established. Please try again"
//                            okButton { onBackPressed() }
//                        }.show()
//                    }
//
//                } catch (e : retrofit2.HttpException){
//                    launch(UI){
//                        progressDialog.dismiss()
//                        alert {
//                            title = "Error"
//                            message = "Error from server. Please try again"
//                            okButton {  }
//                        }.show()
//                    }
//                } catch (e : com.google.gson.JsonSyntaxException){
//                    launch(UI){
//                        progressDialog.dismiss()
//                        alert {
//                            title = "Error"
//                            message = "Error from server. Please try again"
//                            okButton {  }
//                        }.show()
//                    }
//                }
//            }
//        }
//    }

//    private fun completeCardPayment(){
//        progressDialog.show()
//        val amount = txtAmount.text.toString().toDouble() * 100
//
//        val action = when (mTransactionType){
//            TRANSACTION_TYPE.TRANSFER -> "transfer"
//            TRANSACTION_TYPE.DEPOSIT -> "deposit"
//            TRANSACTION_TYPE.WITHDRAWAL -> "withdrawal"
//        }
//
//
//            val transferDetails = TransactionDetails(action = action, beneficiary = mAccountNumber, vendorBankCode =mBankCode, walletID = mWalletId, username = mWalletUsername, password = mWalletPassword, amount = amount.toString(), method = "card", pin = mEncryptedPin)
//
//
//
//            launch(CommonPool){
//                val gson = Gson()
//                val jsonPayload = gson.toJson(transferDetails)
//                val base64encoded = String(org.apache.commons.codec.binary.Base64.encodeBase64(jsonPayload.toByteArray()))
//                val encoded = URLEncoder.encode(base64encoded, "UTF-8")
//                val nonce = "${Calendar.getInstance().timeInMillis}$mWalletId$encoded"
//                val encryptedStuff = "${nonce}BisiG03sToSk00lMak3sM0nEyAnDCanN0wS3ndMon3yViaTh1sAP1$encoded"
//                val signature = HashUtils.sha512(encryptedStuff)
//
//
//                try {
//                    val response =  TransferService.create().transfer(transferDetails, nonce = nonce, signature = signature).await()
//
//                    Log.i("okh", response.toString())
//
//
//                    val isApproved = response.status == 1
//                    (application as App).db.transactionResultDao.get(mRrn).observe({lifecycle}){
//                        val transferDetails = TransferDetails(mTransactionType, isApproved, mAccountName, transferDetails.amount, mConvenienceFee, mBankName, mTerminalId)
//
//                        it?.let {
//                            transactionResult ->
//
//                            val map = hashMapOf<String, String>(
//                                    "MID" to transactionResult.merchantID,
//                                    "RRN" to transactionResult.RRN,
//                                    "Transaction approved" to isApproved.toString(),
//                                    "Terminal ID" to transferDetails.terminalId,
//                                    "Card Holder" to transactionResult.cardHolderName,
//                                    "Card Expiry" to transactionResult.cardExpiry,
//                                    "PAN" to transactionResult.PAN,
//                                    "STAN" to transactionResult.STAN,
//                                    "Auth ID" to transactionResult.authID,
//                                    "Bank Name" to transferDetails.bankName,
//                                    "Beneficiary" to transferDetails.beneficiary,
//                                    "Amount" to transferDetails.amount,
//                                    "Fee" to transferDetails.fee
//                            )
//
//                            val date = TimeUtils.convertLongToString(transactionResult.longDateTime)
//                            val receiptModel = ReceiptModel(date, "Transfer", transactionResult.transactionStatus, map, (transferDetails.amount.toFloat()/100).toString(), transactionResult.transactionStatusReason)
//
//                            val intent = Intent(this@TransferAmountEntry, PrintActivity::class.java)
//                            intent.putExtra(PrintActivity.KEYS.PRINT_RECEIPT_MODEL_KEY, receiptModel)
//                            intent.putExtra(PrintActivity.KEYS.PRINT_RECEIPT_VAS_TYPE, PrintActivity.VasType.NOT_INCLUDED)
//                            //finish()
//                            startActivity(intent)
//                        }
//
//                    }
//
//                }
//                catch (e : SocketTimeoutException){
//                    launch(UI){
//                        progressDialog.dismiss()
//                        alert {
//                            title = "Error"
//                            message = "Connection taking too long to be established. Please try again"
//                            okButton { onBackPressed() }
//                        }.show()
//                    }
//
//                }
//                catch (e : ConnectException){
//                    launch(UI){
//                        progressDialog.dismiss()
//                        alert {
//                            title = "Error"
//                            message = "Connection not established. Please try again"
//                            okButton {  }
//                        }.show()
//                    }
//
//                }
//                catch (e : retrofit2.HttpException) {
//                    launch(UI) {
//                        launch(UI) {
//                            progressDialog.dismiss()
//                            alert {
//                                title = "Error"
//                                message = "Error from server. Please try again"
//                                okButton { }
//                            }.show()
//                        }
//                    }
//                }
//                catch (e : com.google.gson.JsonSyntaxException){
//                    launch(UI){
//                        progressDialog.dismiss()
//                        alert {
//                            title = "Error"
//                            message = "Error from server. Please try again"
//                            okButton {  }
//                        }.show()
//                    }
//                } catch (e : Exception){
//                launch(UI){
//                    progressDialog.dismiss()
//                    alert {
//                        title = "Error"
//                        message = "Error from server. Please try again"
//                        okButton {  }
//                    }.show()
//                }
//            }
//
//            }
//        }

    private fun payWithCard(response: WithdrawalLookupSuccessModel) {
        val view = View.inflate(this, R.layout.activity_enter_pin, null)
        val encryptedPassword = SecureStorage.retrieve(Helper.STORED_PASSWORD, "")

        PinAlertUtils.getPin(this, view){
            mEncryptedPin = SecureStorageUtils.hashIt(it!!, encryptedPassword)!!

            val intent = Intent(this, VasPurchaseProcessor::class.java)
            intent.putExtra(BasePaymentActivity.TRANSACTION_ACCOUNT_TYPE, AccountType.DEFAULT_UNSPECIFIED)


            //times 100 because of the conversion to kobo
            val amount = (txtAmount.text.toString().toFloat() * 100) + response.convenienceFee
            Log.e("amount", amount.toString())
            intent.putExtra(BasePaymentActivity.TRANSACTION_AMOUNT,  amount.toLong())
            intent.putExtra(BasePaymentActivity.TRANSACTION_ADDITIONAL_AMOUNT, 0L)
            startActivityForResult(intent, 9090)
        }

    }

    private fun initializeAmountEntryElements() {
        findViewById<View>(R.id.btn1).setOnClickListener(this)
        findViewById<View>(R.id.btn2).setOnClickListener(this)
        findViewById<View>(R.id.btn3).setOnClickListener(this)
        findViewById<View>(R.id.btn4).setOnClickListener(this)
        findViewById<View>(R.id.btn5).setOnClickListener(this)
        findViewById<View>(R.id.btn6).setOnClickListener(this)
        findViewById<View>(R.id.btn7).setOnClickListener(this)
        findViewById<View>(R.id.btn8).setOnClickListener(this)
        findViewById<View>(R.id.btn9).setOnClickListener(this)
        findViewById<View>(R.id.btn0).setOnClickListener(this)
        findViewById<View>(R.id.btn00).setOnClickListener(this)
        findViewById<View>(R.id.btnclr).setOnClickListener(this)
        findViewById<View>(R.id.btnenter).setOnClickListener(this)
        findViewById<View>(R.id.btncancel).setOnClickListener(this)
    }

    private fun fixAppend(displayStr : StringBuilder, digit : String) {
        if(displayStr.length <= 11)
        {
            displayStr.append(digit)
            var newAmount = displayStr.toString().toDouble()
            // fix new input
            newAmount *= 10.00
            if("00" == digit) newAmount *= 10.00


            val updatedAmount = DecimalFormat("0.00").format(newAmount)
            txtAmount.text = updatedAmount.toString()
        }
    }

    private fun fixDelete(displayStr: StringBuilder) {
        var bd = BigDecimal(displayStr.toString())
        bd = bd.movePointLeft(1)

        txtAmount.text = bd.toString()
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)


        if (requestCode == 9090){
            if (resultCode == Activity.RESULT_OK) {

                    val state = data?.getSerializableExtra("state") as DeviceState
                    mRrn = data.getStringExtra("rrn")

                    when (state){
                        DeviceState.DECLINED, DeviceState.FAILED -> {
                          alert {
                                title = "Transaction Result"
                                message = "Transaction declined. Please try again later"
                                positiveButton(buttonText = "Print"){
//                                    (application as App).db.transactionResultDao.get(mRrn).observe({lifecycle}){
//
//                                        it?.let {
//                                            transactionResult ->
//
//                                            val map = hashMapOf<String, String>(
//                                                    "MID" to transactionResult.merchantID,
//                                                    "RRN" to transactionResult.RRN,
//                                                    "Transaction approved" to "False",
//                                                    "Terminal ID" to mTerminalId,
//                                                    "Card Holder" to transactionResult.cardHolderName,
//                                                    "Card Expiry" to transactionResult.cardExpiry,
//                                                    "PAN" to transactionResult.PAN,
//                                                    "STAN" to transactionResult.STAN,
//                                                    "Auth ID" to transactionResult.authID,
//                                                    "Bank Name" to mBankName,
//                                                    "Beneficiary" to mAccountName,
//                                                    "Fee" to (mConvenienceFee.toDouble()/100).toString()
//                                            )
//                                             val date = TimeUtils.convertLongToString(transactionResult.longDateTime)
//                                            val receiptModel = ReceiptModel(date, "Transfer", transactionResult.transactionStatus, map,  amountToDebit.toLong().toString(), transactionResult.transactionStatusReason)
//
//                                            val intent = Intent(this@TransferAmountEntry, PrintActivity::class.java)
//                                            intent.putExtra(PrintActivity.KEYS.PRINT_RECEIPT_MODEL_KEY, receiptModel)
//                                            intent.putExtra(PrintActivity.KEYS.PRINT_RECEIPT_VAS_TYPE, PrintActivity.VasType.NOT_INCLUDED)
//                                            //finish()
//                                            startActivity(intent)
//                                        }
//
//                                    }
                                }
                            }.show()

                        }

                        DeviceState.APPROVED -> {
                           // completeCardPayment()

                            creditWallet()
                            alert {
                                title = "Transaction Result"
                                message = "Transaction approved."
                                positiveButton(buttonText = "Print"){
//                                    (application as MainApp).db.transactionResultDao.get(mRrn).observe({lifecycle}){
//
//                                        it?.let {
//                                            transactionResult ->
//
//                                            val map = hashMapOf<String, String>(
//                                                    "MID" to transactionResult.merchantID,
//                                                    "RRN" to transactionResult.RRN,
//                                                    "Transaction approved" to "True",
//                                                    "Terminal ID" to mTerminalId,
//                                                    "Card Holder" to transactionResult.cardHolderName,
//                                                    "Card Expiry" to transactionResult.cardExpiry,
//                                                    "PAN" to transactionResult.PAN,
//                                                    "STAN" to transactionResult.STAN,
//                                                    "Amount" to transactionResult.amount.toString(),
//                                                    "Auth ID" to transactionResult.authID,
//                                                    "Bank Name" to mBankName,
//                                                    "Beneficiary" to mAccountName,
//                                                    "Fee" to (mConvenienceFee.toInt()/100).toString()
//                                            )
//                                            val date = TimeUtils.convertLongToString(transactionResult.longDateTime)
//                                            val receiptModel = ReceiptModel(date, "Transfer", transactionResult.transactionStatus, map,  (amountToDebit.toLong()/100).toString(), transactionResult.transactionStatusReason)
//
//                                            val intent = Intent(this@TransferAmountEntry, PrintActivity::class.java)
//                                            intent.putExtra(PrintActivity.KEYS.PRINT_RECEIPT_MODEL_KEY, receiptModel)
//                                            intent.putExtra(PrintActivity.KEYS.PRINT_RECEIPT_VAS_TYPE, PrintActivity.VasType.NOT_INCLUDED)
//                                            startActivity(intent)
//                                        }
//
//                                    }
                                }
                            }.show()
                        } else -> {
                        toast("No data!")
                    }
                    }
            }
        }
    }

    private fun verifyWithdrawalAccountDetails(){
        val progressDialog = ProgressDialog(this)
        progressDialog.setCancelable(false)
        progressDialog.setTitle("Verification")
        progressDialog.setMessage("Now looking for account details")
        progressDialog.show()

        mWalletId = SharedPreferenceUtils.getPayviceWalletId(this@TransferAmountEntry)

        launch(CommonPool){
            try {

                    val accountDetails = AccountLookUpDetailWithdrawal(wallet = mWalletId, username = mWalletUsername, type = "default", password = mWalletPassword, amount = txtAmount.text.toString().toFloat() * 100, channel = "POS")
                withdrawalResponse = TransferService.create().lookUpAccountNumberWithdrawal(accountDetails).await()

                val amount = txtAmount.text.toString()

                try {
                    launch(UI){
                        progressDialog.dismiss()

                            if (withdrawalResponse.status != 1) {
                                alert {
                                    title = "Response"
                                    message = "${withdrawalResponse.message}"
                                    okButton { }
                                }.show()
                            } else {
                                mProductCode = withdrawalResponse.productCode
                                mAccountName = withdrawalResponse.beneficiaryName
                                mConvenienceFee = withdrawalResponse.convenienceFee.toString()
                                alert {
                                    title = "Account number"
                                    message = "${withdrawalResponse.message} - ${withdrawalResponse.beneficiaryName}\nAmount - N$amount\nConvenience fee - N${withdrawalResponse.convenienceFee.toFloat() / 100}"

                                    okButton {
                                        payWithCard(withdrawalResponse)
                                    }
                                }.show()
                            }

                    }

                } catch (e : Exception){
                    launch(UI){
                        progressDialog.dismiss()
                        alert {
                            title = "Response"
                            message = withdrawalResponse.message
                        }.show()
                    }
                }
            }
            catch (e : SocketTimeoutException){
                launch(UI){
                    progressDialog.dismiss()
                    alert {
                        title = "Error"
                        message = "Connection taking too long to be established. Please try again"
                        okButton { onBackPressed() }
                    }.show()
                }

            }
            catch (e : ConnectException){
                launch(UI){
                    progressDialog.dismiss()
                    alert {
                        title = "Error"
                        message = "Connection not established. Please try again"
                        okButton {  }
                    }.show()
                }

            }
            catch (e : retrofit2.HttpException){
                launch(UI){
                    progressDialog.dismiss()
                    alert {
                        title = "Error"
                        message = "Error from server. Please try again"
                        okButton {  }
                    }.show()
                }
            }

        }

    }

    private fun verifyTransferAccountDetails(){
        val progressDialog = ProgressDialog(this)
        progressDialog.setCancelable(false)
        progressDialog.setTitle("Verification")
        progressDialog.setMessage("Now looking for account details")
        progressDialog.show()

        lateinit var response : LookupSuccessModel
        mWalletId = SharedPreferenceUtils.getPayviceWalletId(this@TransferAmountEntry)
        launch(CommonPool){
            try {


                    val  accountDetails = AccountLookUpDetailTransfer(wallet = mWalletId, username = mWalletUsername, type = "default", password = mWalletPassword, amount = txtAmount.text.toString().toDouble() * 100, channel = "POS", beneficiary = mAccountNumber, vendorBankCode = mBankCode)

                    response = TransferService.create().lookUpAccountNumberTransfer(accountDetails).await()

                val amount = txtAmount.text.toString()

                try {
                    launch(UI){
                        progressDialog.dismiss()

                            if (response.status != 1) {
                                alert {
                                    title = "Response"
                                    message = "${response.message}"
                                    okButton { }
                                }.show()
                            } else {
                                mProductCode = response.productCode
                                mAccountName = response.beneficiaryName
                                mConvenienceFee = response.convenienceFee.toString()
                                alert {
                                    title = "Account number"
                                    message = "${response.message} - ${response.beneficiaryName}\nAmount - N$amount\nConvenience fee - N${response.convenienceFee.toFloat() / 100}"

                                    okButton {
                                        debitWallet()
                                    }

                            }.show()


                        }
                    }

                } catch (e : Exception){
                    launch(UI){
                        progressDialog.dismiss()
                        alert {
                            title = "Response"
                            message = response.message
                        }.show()
                    }
                }
            }
            catch (e : SocketTimeoutException){
                launch(UI){
                    progressDialog.dismiss()
                    alert {
                        title = "Error"
                        message = "Connection taking too long to be established. Please try again"
                        okButton { onBackPressed() }
                    }.show()
                }

            }
            catch (e : ConnectException){
                launch(UI){
                    progressDialog.dismiss()
                    alert {
                        title = "Error"
                        message = "Connection not established. Please try again"
                        okButton {  }
                    }.show()
                }

            }
            catch (e : retrofit2.HttpException){
                launch(UI){
                    progressDialog.dismiss()
                    alert {
                        title = "Error"
                        message = "Error from server. Please try again"
                        okButton {  }
                    }.show()
                }
            }

        }

    }

    private fun debitWallet(){
        val progressDialog = ProgressDialog(this)
        progressDialog.setCancelable(false)
        progressDialog.setTitle("Wallet")
        progressDialog.setMessage("Debitting wallet")
        progressDialog.show()

        val clientReference = getClientRef(this@TransferAmountEntry, "")
        lateinit var transferResponse : TransferSuccessModel
        lateinit var transferDetails : TransferDetails
        val view = View.inflate(this, R.layout.activity_enter_pin, null)
        val encryptedPassword = SecureStorage.retrieve(Helper.STORED_PASSWORD, "")
        var amount =  txtAmount.text.toString().toFloat()

        Log.d("debit amount", txtAmount.text.toString().toFloat().toString())
        //Log.d("debit mConvenienceFee",  (mConvenienceFee.toFloat() / 100).toString())
        Log.d("debit amount to debit",  amount.toString())
        PinAlertUtils.getPin(this, view) {
            mEncryptedPin = SecureStorageUtils.hashIt(it!!, encryptedPassword)!!
            launch(CommonPool) {
                try {

                    val action = when (mTransactionType) {
                        TRANSACTION_TYPE.TRANSFER -> "transfer"
                        TRANSACTION_TYPE.DEPOSIT -> "deposit"
                        TRANSACTION_TYPE.WITHDRAWAL -> "withdrawal"
                    }

//clientReference = clientReference,
                    transferDetails = TransferDetails(wallet = mWalletId, username = mWalletUsername, password = mPlainPassword, pin = mEncryptedPin, type = "default", amount = amount.toInt().toFloat()*100, beneficiary = mAccountNumber, vendorBankCode = mBankCode, channel = "ANDROIDPOS",  phone = "", paymentMethod = "cash", productCode = mProductCode)

                    val clientReference = getClientRef(this@TransferAmountEntry, "")

                    Log.e("transfer details", mWalletId + " " + mWalletUsername + " " + mWalletPassword + " " + mPlainPassword + " " + mEncryptedPin + " " + " " + txtAmount.text.toString() + " " + mAccountNumber + " " + mBankCode )
                    val gson = Gson()
                    val jsonPayload = gson.toJson(transferDetails)
                    val base64encoded = String(org.apache.commons.codec.binary.Base64.encodeBase64(jsonPayload.toByteArray()))
                    val encoded = URLEncoder.encode(base64encoded, "UTF-8")
                    val nonce = clientReference
                    Log.e("sign", base64encoded + " " + encoded)
                    val encryptedStuff = "${nonce}IL0v3Th1sAp11111111UC4NDoV4SSWITHVICEBANKING$encoded"
                    val signature = HashUtils.sha512(encryptedStuff)


                    transferResponse = TransferService.create().transfer(transferDetails, "application/json", signature, nonce).await();
                    val amount = txtAmount.text.toString()
                    try {
                        launch(UI) {
                            progressDialog.dismiss()

                            if (transferResponse.status != 1) {
                                alert {
                                    title = "Response"
                                    message = "${transferResponse.message}"
                                    okButton { }
                                }.show()
                            } else {
                                alert {
                                    title = "Response"
                                    message = "${transferResponse.message}. Your wallet has been debitted \n " +
                                            "\n#${transferResponse.amountDebited} \nBeneficiary : ${transferResponse.beneficiaryName}"
                                    positiveButton(buttonText = "Print") {

                                                val map = hashMapOf<String, String>(
                                                        "Reference" to transferResponse.reference,
                                                        "Message" to transferResponse.message,
                                                        "Account Name" to transferResponse.beneficiaryName,
                                                        "Bank Name" to mBankName,
                                                        "Account Number" to transferResponse.beneficiary,
                                                        "Amount Debited" to (transferResponse.amountDebited/100).toString(),
                                                        "Convenience Fee" to (transferResponse.convenienceFee/100).toString()
                                                )
                                                val formattedDate = SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().time)

                                                val receiptModel = ReceiptModel(formattedDate, "Transfer", transferResponse.message, map, (transferResponse.amountDebited / 100).toString(), transferResponse.message)

                                        Log.d("debit print",  transferResponse.amountDebited.toString())

//                                                val intent = Intent(this@TransferAmountEntry, PrintActivity::class.java)
//                                                intent.putExtra(PrintActivity.KEYS.PRINT_RECEIPT_MODEL_KEY, receiptModel)
//                                                intent.putExtra(PrintActivity.KEYS.PRINT_RECEIPT_VAS_TYPE, PrintActivity.VasType.NOT_INCLUDED)
//                                                //finish()
//                                                startActivity(intent)

                                    }
                                    }.show()

                            }

                        }

                    } catch (e: Exception) {
                        launch(UI) {
                            progressDialog.dismiss()
                            alert {
                                title = "Response"
                                message = transferResponse.message
                            }.show()
                        }
                    }
                } catch (e: SocketTimeoutException) {
                    launch(UI) {
                        progressDialog.dismiss()
                        alert {
                            title = "Error"
                            message = "Connection taking too long to be established. Please try again"
                            okButton { onBackPressed() }
                        }.show()
                    }

                } catch (e: ConnectException) {
                    launch(UI) {
                        progressDialog.dismiss()
                        alert {
                            title = "Error"
                            message = "Connection not established. Please try again"
                            okButton { }
                        }.show()
                    }

                } catch (e: retrofit2.HttpException) {
                    launch(UI) {
                        progressDialog.dismiss()
                        alert {
                            title = "Error"
                            message = "Error from server. Please try again"
                            okButton { }
                        }.show()
                    }
                }

            }
        }
    }

    var amountToDebit : Double = 0.0

    private fun creditWallet(){
        val progressDialog = ProgressDialog(this)
        progressDialog.setCancelable(false)
        progressDialog.setTitle("Wallet")
        progressDialog.setMessage("Crediting wallet")
        progressDialog.show()

        val clientReference = getClientRef(this@TransferAmountEntry, "")
        lateinit var transferResponse : WithdrawalWalletCreditModel
        lateinit var transferDetails : WithdrawalDetails
        amountToDebit = (txtAmount.text.toString().toDouble() * 100) + withdrawalResponse.convenienceFee*100
        Log.d("credit amount", txtAmount.text.toString().toFloat().toString())
        Log.d("credit amount to debit",  amount.toString())

        launch(CommonPool) {
                try {

                    val action = when (mTransactionType) {
                        TRANSACTION_TYPE.TRANSFER -> "transfer"
                        TRANSACTION_TYPE.DEPOSIT -> "deposit"
                        TRANSACTION_TYPE.WITHDRAWAL -> "withdrawal"
                    }

//clientReference = clientReference,
                    transferDetails = WithdrawalDetails(wallet = mWalletId, username = mWalletUsername, password = mPlainPassword, pin = mEncryptedPin, type = "default", amount = amountToDebit, vendorBankCode = mBankCode, channel = "ANDROIDPOS", phone = "", paymentMethod = "card", productCode = mProductCode)

                    val clientReference = getClientRef(this@TransferAmountEntry, "")

                    Log.e("transfer details", mWalletId + " " + mWalletUsername + " " + mWalletPassword + " " + mEncryptedPin + " " + " " + "" + txtAmount.text.toString() + " " + mAccountNumber + " " + mBankCode)
                    val gson = Gson()
                    val jsonPayload = gson.toJson(transferDetails)
                    val base64encoded = String(org.apache.commons.codec.binary.Base64.encodeBase64(jsonPayload.toByteArray()))
                    val encoded = URLEncoder.encode(base64encoded, "UTF-8")
                    val nonce = clientReference
                    Log.e("sign", base64encoded + " " + encoded)
                    val encryptedStuff = "${nonce}IL0v3Th1sAp11111111UC4NDoV4SSWITHVICEBANKING$encoded"
                    val signature = HashUtils.sha512(encryptedStuff)


                    transferResponse = TransferService.create().withdraw(transferDetails, "application/json", signature, nonce).await();
                    val amount = txtAmount.text.toString()
                    try {
                        launch(UI) {
                            progressDialog.dismiss()

                            if (transferResponse.status != 1) {
                                alert {
                                    title = "Response"
                                    message = "${transferResponse.message}"
                                    okButton { }
                                }.show()
                            } else {
                                alert {
                                    title = "Response"
                                    message = "${transferResponse.message}. You been debited from your wallet \n " +
                                            "\n#${transferResponse.amountSettled} \nBeneficiary : ${transferResponse.beneficiaryName}"
                                    positiveButton(buttonText = "Print") {

                                        val map = hashMapOf<String, String>(
                                                "Reference" to transferResponse.reference,
                                                "Message" to transferResponse.message,
                                                "Account Name" to transferResponse.beneficiaryName,
                                                "Bank Name" to mBankName,
                                                "Account Number" to transferResponse.beneficiaryName,
                                                "Amount Settled" to transferResponse.amountSettled.toString(),
                                                "Convenience Fee" to transferResponse.convenienceFee.toString()
                                        )
                                        val formattedDate = SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().time)
                                        val receiptModel = ReceiptModel(formattedDate, "Transfer", transferResponse.message, map, (txtAmount.text.toString().toFloat() * 100).toString(), transferResponse.message)

                                        Log.d("debit print amountSet",  transferResponse.amountSettled.toString())
                                        Log.d("debit print fee",  transferResponse.convenienceFee.toString())

//                                        val intent = Intent(this@TransferAmountEntry, PrintActivity::class.java)
//                                        intent.putExtra(PrintActivity.KEYS.PRINT_RECEIPT_MODEL_KEY, receiptModel)
//                                        intent.putExtra(PrintActivity.KEYS.PRINT_RECEIPT_VAS_TYPE, PrintActivity.VasType.NOT_INCLUDED)
//                                        //finish()
//                                        startActivity(intent)

                                    }
                                }

                            }

                        }

                    } catch (e: Exception) {
                        launch(UI) {
                            progressDialog.dismiss()
                            alert {
                                title = "Response"
                                message = transferResponse.message
                            }.show()
                        }
                    }
                } catch (e: SocketTimeoutException) {
                    launch(UI) {
                        progressDialog.dismiss()
                        alert {
                            title = "Error"
                            message = "Connection taking too long to be established. Please try again"
                            okButton { onBackPressed() }
                        }.show()
                    }

                } catch (e: ConnectException) {
                    launch(UI) {
                        progressDialog.dismiss()
                        alert {
                            title = "Error"
                            message = "Connection not established. Please try again"
                            okButton { }
                        }.show()
                    }

                } catch (e: retrofit2.HttpException) {
                    launch(UI) {
                        progressDialog.dismiss()
                        alert {
                            title = "Error"
                            message = "Error from server. Please try again"
                            okButton { }
                        }.show()
                    }
                }
            }
    }

//    private fun paymentSelection(response: LookupSuccessModel) {
//        alert {
//            title = "Transaction Type"
//            message = "Select the type of transaction you want to make"
//            positiveButton(buttonText = "Card") { _ -> payWithCard(response) }
//            negativeButton(buttonText = "Wallet") { _ -> payWithWallet(response) }
//        }.show()
//    }

}
