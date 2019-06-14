package com.wizarpos.emvsample.payments_menu.transfer

//import AmpEmvL2Android.AMPDevice
import android.app.Activity
import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Gravity
import android.view.View
import com.google.gson.Gson
//import com.wizarpos.emvsample.models.LookupSuccessModel
import com.wizarpos.emvsample.models.ReceiptModel
import com.wizarpos.emvsample.models.WithdrawalLookupSuccessModel
import com.wizarpos.emvsample.models.WithdrawalWalletResponse.WithdrawalWalletCreditModel
import com.wizarpos.emvsample.models.transfer.TransferSuccessModel
import com.wizarpos.emvsample.payments_menu.Services.TransferService
import com.wizarpos.emvsample.payments_menu.utils.HashUtils
import com.iisysgroup.poslib.deviceinterface.DeviceState
import com.wizarpos.emvsample.MainApp
import com.wizarpos.emvsample.R
import com.wizarpos.emvsample.activity.FuncActivity
import com.wizarpos.emvsample.activity.MainActivity
import com.wizarpos.emvsample.activity.Sale
import com.wizarpos.emvsample.activity.login.Helper
import com.wizarpos.emvsample.activity.login.securestorage.SecureStorage
import com.wizarpos.emvsample.activity.login.securestorage.SecureStorageUtils
import com.wizarpos.emvsample.payments_menu.models.*
import com.wizarpos.emvsample.printer.PrinterException
import com.wizarpos.emvsample.printer.PrinterHelper
import com.wizarpos.util.ClientReferenceKey
import com.wizarpos.util.PinAlertUtils
import com.wizarpos.util.SharedPreferenceUtils
import com.wizarpos.util.StringUtil.getClientRef
import com.wizarpos.util.TransactionModel
import kotlinx.android.synthetic.main.activity_transfer_amount_entry.*
import kotlinx.coroutines.*
import org.jetbrains.anko.*
import retrofit2.Call
import retrofit2.Callback
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

    var appState: MainApp? = null
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

//    private val progressDialog by lazy {
//        indeterminateProgressDialog(message = "Processing")
//    }

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

    private fun payWithCard(response: WithdrawalLookupSuccessModel) {

        val view = View.inflate(this, R.layout.activity_enter_pin, null)
        val encryptedPassword = SecureStorage.retrieve(Helper.STORED_PASSWORD, "")

        PinAlertUtils.getPin(this, view){
            mEncryptedPin = SecureStorageUtils.hashIt(it!!, encryptedPassword)!!

            val clientReferenceKey : ClientReferenceKey
            FuncActivity.appState.needCard = true
            FuncActivity.appState.withdrawal = true
//            val withdrawaldetails = WithdrawalDetails(wallet = mWalletId, username = mWalletUsername, password = mPlainPassword, pin = mEncryptedPin, type = "default", amount = amountToDebit, vendorBankCode = mBankCode, channel = "ANDROIDPOS", phone = "", paymentMethod = "card", productCode = mProductCode, reference = clientReferenceKey.generateReference(getBaseContext()), pfm = clientReferenceKey.generatePFM(transactionResult, getBaseContext()))
            Log.d("okh", "$amountToDebit init amount");
            //  intent.putExtra("withdrawaldetails", withdrawaldetails);
            SecureStorage.store("amount", 2.0)
            SecureStorage.store("channel", "ANDROIDPOS")
            SecureStorage.store("password", mPlainPassword)
            SecureStorage.store("paymentMethod", "card")
            SecureStorage.store("pin", mEncryptedPin)
            SecureStorage.store("productCode", mProductCode)
            SecureStorage.store("type", "default")
            SecureStorage.store("vendorBankCode", mBankCode)
            SecureStorage.store("wallet", mWalletId)
            SecureStorage.store("username", mWalletUsername)
            SecureStorage.store("phone", "")
            val vendorBankCode = SecureStorage.retrieve("vendorBankCode", "")
            Log.d("okh", "saved "+ vendorBankCode)
            val intent = Intent(this, Sale::class.java)

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
            Log.d("okh", "coming back withdrawal")
            if (resultCode == Activity.RESULT_OK) {

                val state = data?.getSerializableExtra("state") as DeviceState
                mRrn = data.getStringExtra("rrn")

                when (state){
                    DeviceState.DECLINED, DeviceState.FAILED -> {
                        alert {
                            title = "Transaction Result"
                            message = "Transaction declined. Please try again later"
                            positiveButton(buttonText = "Print"){

                            }
                        }.show()

                    }

                    DeviceState.APPROVED -> {
                        // completeCardPayment()
                        Log.d("okh", "credit wallet now")
                        creditWallet(this)
                        alert {
                            title = "Transaction Result"
                            message = "Transaction approved."
                            positiveButton(buttonText = "Print"){

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
//        progressDialog.show()

        mWalletId = SharedPreferenceUtils.getPayviceWalletId(this@TransferAmountEntry)


        try {

            val accountDetails = AccountLookUpDetailWithdrawal(wallet = mWalletId, username = mWalletUsername, type = "default", password = mWalletPassword, amount = txtAmount.text.toString().toFloat() * 100, channel = "POS")
            TransferService.create().lookUpAccountNumberWithdrawal(accountDetails).enqueue(object : Callback<WithdrawalLookupSuccessModel> {
                override fun onFailure(call: Call<WithdrawalLookupSuccessModel>, t: Throwable) {
                    Log.d("okh", t.message)
                }

                override fun onResponse(call: Call<WithdrawalLookupSuccessModel>, response: retrofit2.Response<WithdrawalLookupSuccessModel>) {

                    Log.d("okh", response.toString())
                    val amount = txtAmount.text.toString()
                    if (response.body()!!.status != 1) {
                        alert {
                            title = "Response"
                            message = response.body()!!.message
                            okButton { }
                        }.show()
                    } else {
                        mProductCode = response.body()!!.productCode
                        mAccountName = response.body()!!.beneficiaryName
                        mConvenienceFee = response.body()!!.convenienceFee.toString()
                        alert {
                            title = "${response.body()!!.message}"
                            message = "${response.body()!!.beneficiaryName}\nAmount - N$amount\nConvenience fee - N${response.body()!!.convenienceFee.toFloat() / 100}"
                            positiveButton("Continue") {
                                payWithCard(response.body()!!)
                            }
                        }.show()
                    }
                }

            })


//                try {
//                    GlobalScope.launch(Dispatchers.Main) {
//                        //progressDialog.dismiss()
//
//
//
//                    }
//
//                } catch (e : Exception){
//                    GlobalScope.launch(Dispatchers.Main) {
//                        progressDialog.dismiss()
//                        alert {
//                            title = "Response"
//                            message = withdrawalResponse.message
//                        }.show()
//                    }
//                }
//            }
//            catch (e : SocketTimeoutException){
//                GlobalScope.launch(Dispatchers.Main) {
//                    progressDialog.dismiss()
//                    alert {
//                        title = "Error"
//                        message = "Connection taking too long to be established. Please try again"
//                        okButton { onBackPressed() }
//                    }.show()
//                }
//
//            }
//            catch (e : ConnectException){
//                GlobalScope.launch(Dispatchers.Main) {
//                    progressDialog.dismiss()
//                    alert {
//                        title = "Error"
//                        message = "Connection not established. Please try again"
//                        okButton {  }
//                    }.show()
//                }
//
//            }
//            catch (e : retrofit2.HttpException){
//                GlobalScope.launch(Dispatchers.Main) {
//                    progressDialog.dismiss()
//                    alert {
//                        title = "Error"
//                        message = "Error from server. Please try again"
//                        okButton {  }
//                    }.show()
//                }
//            }



        }
        catch (e : Exception) {
        }
    }

    private fun verifyTransferAccountDetails(){
        longToast("Verification \n \n Now looking for account details").setGravity(Gravity.CENTER, 0, 0)

        lateinit var response : LookupSuccessModel
        mWalletId = SharedPreferenceUtils.getPayviceWalletId(this@TransferAmountEntry)

        val  accountDetails = AccountLookUpDetailTransfer(wallet = mWalletId, username = mWalletUsername, type = "default", password = mWalletPassword, amount = txtAmount.text.toString().toDouble() * 100, channel = "POS", beneficiary = mAccountNumber, vendorBankCode = mBankCode)

        // response = TransferService.create().lookUpAccountNumberTransfer(accountDetails).await()
        val amount = txtAmount.text.toString()

        try{
            val service = TransferService.create()
            service.lookUpAccountNumberTransfer(accountDetails).enqueue(object : Callback<LookupSuccessModel> {

                override fun onFailure(call: Call<LookupSuccessModel>, t: Throwable) {
                    Log.d("okh", "error " + t.message)
                }

                override fun onResponse(call: Call<LookupSuccessModel>, response: retrofit2.Response<LookupSuccessModel>) {
                    //  val responses = response as LookupSuccessModel

                    if (response.body() != null) {
                        Log.d("okh", "response " + response.body().toString())
                        if (response.body()!!.status != 1) {
                            alert {
                                title = "Response"
                                message = response.body()!!.message
                                okButton { }
                            }.show()
                        } else {
                            mProductCode = response.body()!!.productCode
                            mAccountName = response.body()!!.beneficiaryName
                            mConvenienceFee = response.body()!!.convenienceFee.toString()
                            alert {
                                title = "${response.body()!!.message}"
                                message = "${response.body()!!.message} - ${response.body()!!.beneficiaryName}\nAmount - N$amount\nConvenience fee - N${response.body()!!.convenienceFee.toFloat() / 100}"
                                positiveButton("Continue") {
                                    debitWallet()
                                }

                            }.show()

                        }
                    }

                }
            })
        }catch (e : Exception){

        }

    }

    public fun debitWallet(){
//        val progressDialog = ProgressDialog(this)
//        progressDialog.setCancelable(false)
//        progressDialog.setTitle("Wallet")
//        progressDialog.setMessage("Debitting wallet")
//        progressDialog.show()

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
            GlobalScope.launch(Dispatchers.Default) {
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

                    val amount = txtAmount.text.toString()

                    try{
                        TransferService.create().transfer(transferDetails, "application/json", signature, nonce).enqueue(object  : Callback<TransferSuccessModel>{
                            override fun onFailure(call: Call<TransferSuccessModel>, t: Throwable) {
                         //       Log.d("okh", t.message)
                            }

                            override fun onResponse(call: Call<TransferSuccessModel>, response: retrofit2.Response<TransferSuccessModel>) {
                                Log.d("okh", "result "+response.body()!!)
                                if (response.body()!!.status != 1) {
//                                val failResponse = response as TransferFailureModel
//                                Log.d("okh", failResponse.description + " result")
                                    alert {
                                        title = "Response"
                                        message = response.body()!!.message + "\n"+ response.body()!!.reason
                                        okButton {

                                            val userId = SecureStorage.retrieve(Helper.USER_ID, "")
                                            val emailid = SecureStorage.retrieve(Helper.USER_EMAIL, "")
                                            val currentTime = Calendar.getInstance().time.toString()
                                            val transactionModel = TransactionModel(userId, response.body()!!.transactionID.toString(), "", "", amount, "", "transfer", "", "Declined", response.body()!!.message, userId, emailid, "", "", "", "", "", "", currentTime, "", "", mBankName)

                                            val intent = Intent(baseContext, MainActivity::class.java)

                                            intent.putExtra("transactionModel", transactionModel)
                                            intent.putExtra("copy", "** CUSTOMER COPY **")
                                            startActivity(intent)

                                        }
                                    }.show()
                                } else {
                                    alert {
                                        title = "Response"
                                        message = "${response.body()!!.message}. Your wallet has been debitted \n " +
                                                "\n#${response.body()!!.amountDebited/100} \nBeneficiary : ${response.body()!!.beneficiaryName}"
                                        positiveButton(buttonText = "Print") {

                                            val map = hashMapOf<String, String>(
                                                    "Reference" to response.body()!!.reference,
                                                    "Message" to response.body()!!.message,
                                                    "Account Name" to response.body()!!.beneficiaryName,
                                                    "Bank Name" to mBankName,
                                                    "Account Number" to response.body()!!.beneficiary,
                                                    "Amount Debited" to (response.body()!!.amountDebited/100).toString(),
                                                    "Convenience Fee" to (response.body()!!.convenienceFee/100).toString()
                                            )
                                            val formattedDate = SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().time)

                                            val receiptModel = ReceiptModel(formattedDate, "Transfer", response.body()!!.message, map, (response.body()!!.amountDebited / 100).toString(), response.body()!!.message)

                                            Log.d("debit print",  response.body()!!.amountDebited.toString())
                                                appState!!.transfer = true;
                                                appState!!.trans.transactionType = "transfer"

                                            val userId = SecureStorage.retrieve(Helper.USER_ID, "")
                                            val emailid = SecureStorage.retrieve(Helper.USER_EMAIL, "")

                                            val transactionModel = TransactionModel(userId, "", "", "phone_number", (response.body()!!.amountDebited/100).toString(), "", "transfer", "00", "Approved", "", userId, emailid, "", "", "", "", "", "", "", "", "", mBankName)

                                            val intent = Intent(baseContext, MainActivity::class.java)

                                            intent.putExtra("transactionModel", transactionModel)
                                            intent.putExtra("copy", "** CUSTOMER COPY **")
                                            startActivity(intent)
                                            val alertDialog = AlertDialog.Builder(baseContext)
                                            alertDialog.setMessage("Print Merchant copy")
                                            alertDialog.setPositiveButton("OK") { dialogInterface, i ->
                                                val intent = Intent(baseContext, MainActivity::class.java)
                                                intent.putExtra("transactionModel", transactionModel)
                                                intent.putExtra("copy", "*** MERCHANT COPY ***")
                                                startActivity(intent)
                                            }
                                            alertDialog.show()
//                                                val intent = Intent(this@TransferAmountEntry, PrintActivity::class.java)
//                                                intent.putExtra(PrintActivity.KEYS.PRINT_RECEIPT_MODEL_KEY, receiptModel)
//                                                intent.putExtra(PrintActivity.KEYS.PRINT_RECEIPT_VAS_TYPE, PrintActivity.VasType.NOT_INCLUDED)
//                                                //finish()
//                                                startActivity(intent)

                                        }
                                    }.show()

                                }
                            }

                        })
                    }catch (e : Exception){

                    }

                }catch (E : java.lang.Exception){
                }
            }
        }
    }

    var amountToDebit : Double = 0.0

    public fun creditWallet(context : Context){

        val clientReference = getClientRef(context, "")
        lateinit var transferResponse : WithdrawalWalletCreditModel
        lateinit var transferDetails : WithdrawalDetails
        //amountToDebit = (txtAmount.text.toString().toDouble() * 100) + withdrawalResponse.convenienceFee*100
        amountToDebit = 2.0
        //  Log.d("credit amount", txtAmount.text.toString().toFloat().toString())
        // Log.d("credit amount to debit",  amount.toString())

        GlobalScope.launch(Dispatchers.Default) {
            try {

                val action = when (mTransactionType) {
                    TRANSACTION_TYPE.TRANSFER -> "transfer"
                    TRANSACTION_TYPE.DEPOSIT -> "deposit"
                    TRANSACTION_TYPE.WITHDRAWAL -> "withdrawal"
                }

//clientReference = clientReference,
//                    transferDetails = WithdrawalDetails(wallet = mWalletId, username = mWalletUsername, password = mPlainPassword, pin = mEncryptedPin, type = "default", amount = amountToDebit, vendorBankCode = mBankCode, channel = "ANDROIDPOS", phone = "", paymentMethod = "card", productCode = mProductCode)

//                    val clientReference = getClientRef(this@TransferAmountEntry, "")
                val clientReference = getClientRef(context, "")

                Log.d("transfer details", mWalletId + " " + mWalletUsername + " " + mWalletPassword + " " + mEncryptedPin + " " + " " + "" + txtAmount.text.toString() + " " + mAccountNumber + " " + mBankCode)
                val gson = Gson()
                val jsonPayload = gson.toJson(transferDetails)
                val base64encoded = String(org.apache.commons.codec.binary.Base64.encodeBase64(jsonPayload.toByteArray()))
                val encoded = URLEncoder.encode(base64encoded, "UTF-8")
                val nonce = clientReference
                Log.d("sign", base64encoded + " " + encoded)
                val encryptedStuff = "${nonce}IL0v3Th1sAp11111111UC4NDoV4SSWITHVICEBANKING$encoded"
                val signature = HashUtils.sha512(encryptedStuff)

                transferResponse = TransferService.create().withdraws(transferDetails, "application/json", signature, nonce).await();
                // val amount = txtAmount.text.toString()
                try {
                    GlobalScope.launch(Dispatchers.Main) {
                        //progressDialog.dismiss()

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
                                    printVasReceipt()
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
                    GlobalScope.launch(Dispatchers.Main) {
                        //progressDialog.dismiss()
                        alert {
                            title = "Response"
                            message = transferResponse.message
                        }.show()
                    }
                }
            } catch (e: SocketTimeoutException) {
                GlobalScope.launch(Dispatchers.Main) {
                    //progressDialog.dismiss()
                    alert {
                        title = "Error"
                        message = "Connection taking too long to be established. Please try again"
                        okButton { onBackPressed() }
                    }.show()
                }

            } catch (e: ConnectException) {
                GlobalScope.launch(Dispatchers.Main) {
                    //   progressDialog.dismiss()
                    alert {
                        title = "Error"
                        message = "Connection not established. Please try again"
                        okButton { }
                    }.show()
                }

            } catch (e: retrofit2.HttpException) {
                GlobalScope.launch(Dispatchers.Main) {
                    //progressDialog.dismiss()
                    alert {
                        title = "Error"
                        message = "Error from server. Please try again"
                        okButton { }
                    }.show()
                }
            }
        }
    }


    private fun printVasReceipt() {
        FuncActivity.appState.printVasReceipt++
        try {
            PrinterHelper.getInstance().printVasReceipt(FuncActivity.appState, 1)
            val alertDialog = AlertDialog.Builder(this)
            alertDialog.setMessage("Print Merchant copy")
            alertDialog.setPositiveButton("OK") { dialogInterface, i ->
                try {
                    PrinterHelper.getInstance().printVasReceipt(FuncActivity.appState, 0)
                } catch (e: PrinterException) {
                    e.printStackTrace()
                }
            }
            alertDialog.show()
        } catch (e: PrinterException) {
        }

    }
}
