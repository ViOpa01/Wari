package com.wizarpos.emvsample.activity

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.iisysgroup.poslib.deviceinterface.DeviceState
import com.iisysgroup.poslib.host.entities.TransactionResult
import com.wizarpos.emvsample.MainApp
import com.wizarpos.emvsample.R
import com.wizarpos.emvsample.activity.FuncActivity.appState
import com.wizarpos.emvsample.activity.login.Helper
import com.wizarpos.emvsample.activity.login.securestorage.SecureStorage
import com.wizarpos.emvsample.activity.login.securestorage.SecureStorageUtils
import com.wizarpos.emvsample.services.discos.activities.ElectricityPaymentActivity
import com.wizarpos.emvsample.services.helper.activity.util.Models
import com.wizarpos.util.PinAlertUtils
import com.wizarpos.util.SharedPreferenceUtils
import com.wizarpos.util.TransactionModel
import com.wizarpos.util.VasServices
import kotlinx.android.synthetic.main.activity_data_phone_entry.*
import kotlinx.android.synthetic.main.single_transaction.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.jetbrains.anko.alert
import org.jetbrains.anko.indeterminateProgressDialog
import org.jetbrains.anko.okButton
import org.jetbrains.anko.toast
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.text.SimpleDateFormat
import java.util.*

class DataPhoneEntry : AppCompatActivity(), View.OnClickListener {
    private val isBeneficiary by lazy {
        intent.hasExtra(AllData.KEYS.PHONE_NUMBER)
    }

    private lateinit var transactionResult: TransactionResult

    private val phoneNumber by lazy {
        intent.getStringExtra(AllData.KEYS.PHONE_NUMBER)
    }

    private val mProgressDialog by lazy {
        indeterminateProgressDialog("Loading data services")
    }

    private val mAirtimeProcessDialog by lazy {
        indeterminateProgressDialog("Processing")
    }

    private lateinit var mPayvicePin: String

    private lateinit var mPhoneNumber: String

    private val mWalletUsername by lazy {
        SharedPreferenceUtils.getPayviceUsername(this)
    }

    private val mWalletId by lazy {
        SharedPreferenceUtils.getPayviceWalletId(this)
    }

    private val mWalletPassword by lazy {
        SharedPreferenceUtils.getPayvicePassword(this)
    }

    private val dataItem by lazy {
        intent.getParcelableExtra(AllData.KEYS.DATA_VALUE) as DataModel.DataResponseElements

    }

    private fun payWithWallet() {
        val view = View.inflate(this, R.layout.activity_enter_pin, null)
        PinAlertUtils.getPin(this, view) {
            mProgressDialog.dismiss()
            mAirtimeProcessDialog.show()
            GlobalScope.launch(Dispatchers.Main){
                val encryptedPassword = SecureStorage.retrieve(Helper.STORED_PASSWORD, "")
                val encryptedPin = SecureStorageUtils.hashIt(it!!, encryptedPassword)

                mPayvicePin = encryptedPin!!
                appState.product=dataItem.type
                appState.dataAmount =dataItem.amount;
                val payDetails = DataModel.DataSubscriptionDetails(phone = mPhoneNumber.replace(" ", ""), service = dataItem.type, amount = dataItem.amount, description = dataItem.description, code = dataItem.code, password = mWalletPassword, user_id = mWalletUsername, terminal_id = mWalletId, pin = encryptedPin)

                try {
                    val response = DataService.create().dataSubscribe(payDetails).await()
                    val jsonResponse = Gson().toJsonTree(response).asJsonObject

                    Log.d("OkHData", jsonResponse.toString())

                    if (jsonResponse.toString().contains("\"error\":false")) {

                        val gson = GsonBuilder().excludeFieldsWithoutExposeAnnotation().create()

                        val formattedResponse = gson.fromJson(jsonResponse, DataModel.DataSubscriptionSuccessResponse::class.java)

                        GlobalScope.launch(Dispatchers.Main) {
                            mAirtimeProcessDialog.dismiss()
                            val terminalID = SecureStorage.retrieve(Helper.TERMINAL, "")
                            val amount = formattedResponse.amount
                            val ref = formattedResponse.ref
                            alert {
                                title = "Response"
                                message = formattedResponse.message
                                positiveButton(buttonText = "Print") {
                                    var transactionModel: TransactionModel? = null
                                    val merchantID = FuncActivity.appState.nibssData.configData.getConfigData("03015").toString()
                                    val merchantName = FuncActivity.appState.nibssData.configData.getConfigData("52040").toString()
                                    try {
                                        val date = SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().time)
                                        var bankLogoName = ""
                                        try {
                                            bankLogoName = "bank" + terminalID.substring(0, 4)
                                        } catch (e: Exception) {

                                        }

//                                        transactionModel = TransactionModel(terminalID, ref, "", "", amount, "", "data", "", "Approved", "", merchantID, merchantName, "", "", "", "", "", "", date, "", "", bankLogoName, mPhoneNumber);
//
//                                        val intent = Intent(baseContext, MainActivity::class.java)
//
//                                        intent.putExtra("transactionModel", transactionModel)
//                                        intent.putExtra("copy", "** CUSTOMER COPY **")
//                                        startActivity(intent)
//                                        val alertDialog = AlertDialog.Builder(baseContext)
//                                        alertDialog.setMessage("Print Merchant copy")
//                                        val finalTransactionModel = transactionModel
//                                        alertDialog.setPositiveButton("OK") { dialogInterface, i ->
//                                            val intent = Intent(baseContext, MainActivity::class.java)
//                                            intent.putExtra("transactionModel", finalTransactionModel)
//                                            intent.putExtra("copy", "*** MERCHANT COPY ***")
//                                            startActivity(intent)
//                                        }
//                                        alertDialog.show()


                                        val smartCardNumber = ""
                                        val meterNumber = ""
                                        val beneficiaryName = ""
                                        val beneficiaryAddress = ""
                                        val responsemessage = formattedResponse.message
                                        val amount = formattedResponse.amount
                                        val token = ""
                                        val wallet = SecureStorage.retrieve(Helper.TERMINAL_ID, "")
                                        val product = appState.product
                                        val transactionRef = ""
                                        val stan= formattedResponse.transactionID
                                        val logo = FuncActivity.appState.logo
                                        val error :Boolean= formattedResponse.error
                                        val airtimeModel = Models.AirtimeModel(error, mPhoneNumber)

                                        val isCardTransaction = true
                                        val transactionTID = ""
                                        val merchantID = FuncActivity.appState.nibssData.configData.getConfigData("03015").toString()
                                        val merchantName = FuncActivity.appState.nibssData.configData.getConfigData("52040").toString()
                                        val merchantTerminalId = SecureStorage.retrieve(Helper.TERMINAL, "")
//			                          	String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
                                        val vasmerchantID = SecureStorage.retrieve(Helper.VAS_TERMINAL_ID, "")
                                        val vasmerchantName = SecureStorage.retrieve(Helper.VAS_MERCHANT_NAME, "")
//				                        String vasTerminalId = SecureStorage.retrieve(Helper.,"");


                                        var vasDetails: Models.VasDetails? = null


//                                        if (airtimetype.equals("wallet", ignoreCase = true)) {
                                            vasDetails = Models.VasDetails(stan,amount, wallet, vasmerchantName, merchantID, merchantName, merchantTerminalId, product, responsemessage, vasmerchantID, transactionRef, VasServices.CASH, logo, date, error, Models.AIRTIME, airtimeModel)
//                                        } else if (airtimetype.equals("card", ignoreCase = true)) {
//                                            vasDetails = Models.VasDetails(amount, wallet, vasmerchantName, merchantID, merchantName, merchantTerminalId, product, responsemessage, vasmerchantID, transactionRef, VasServices.CARD, logo, date, error, Models.AIRTIME, airtimeModel)
//                                        }

                                        ElectricityPaymentActivity.print(this@DataPhoneEntry,vasDetails)
                                    } catch (e: Exception) {
                                    }
                                }
                            }.show()
                        }
                    } else {
                        val gson = GsonBuilder().excludeFieldsWithoutExposeAnnotation().create()

                        val formattedResponse = gson.fromJson(jsonResponse, DataModel.DataSubscriptionFailedResponse::class.java)
                        //todo handle a rollback
                        GlobalScope.launch(Dispatchers.Main) {
                            mAirtimeProcessDialog.dismiss()
                            val terminalID = SecureStorage.retrieve(Helper.TERMINAL, "")
                            val ref = formattedResponse.ref
                            alert {
                                title = "Response"
                                message = formattedResponse.message
                                positiveButton(buttonText = "Print") {
                                    var transactionModel: TransactionModel? = null
                                    val merchantID = FuncActivity.appState.nibssData.configData.getConfigData("03015").toString()
                                    val merchantName = FuncActivity.appState.nibssData.configData.getConfigData("52040").toString()
                                    try {
                                        val date = SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().time)
                                        var bankLogoName = ""
                                        try {
                                            bankLogoName = "bank" + terminalID.substring(0, 4)
                                        } catch (e: Exception) {

                                        }

//                                        transactionModel = TransactionModel(terminalID, ref, "", "", dataItem.amount, "", "data", "", "Declined", "", merchantID, merchantName, "", "", "", "", "", "", date, "", "", bankLogoName, mPhoneNumber);
//
//                                        val intent = Intent(baseContext, MainActivity::class.java)
//
//                                        intent.putExtra("transactionModel", transactionModel)
//                                        intent.putExtra("copy", "** CUSTOMER COPY **")
//                                        startActivity(intent)
//                                        val alertDialog = AlertDialog.Builder(baseContext)
//                                        alertDialog.setMessage("Print Merchant copy")
//                                        val finalTransactionModel = transactionModel
//                                        alertDialog.setPositiveButton("OK") { dialogInterface, i ->
//                                            val intent = Intent(baseContext, MainActivity::class.java)
//                                            intent.putExtra("transactionModel", finalTransactionModel)
//                                            intent.putExtra("copy", "*** MERCHANT COPY ***")
//                                            startActivity(intent)
//                                        }
//                                        alertDialog.show()

                                        val smartCardNumber = ""
                                        val meterNumber = ""
                                        val beneficiaryName = ""
                                        val beneficiaryAddress = ""
                                        val responsemessage = formattedResponse.message
                                        val amount = dataItem.amount
                                        val token = ""
                                        val wallet = SecureStorage.retrieve(Helper.TERMINAL_ID, "")
                                        val product = appState.product
                                        val transactionRef = ""
                                        val logo = FuncActivity.appState.logo
                                        val stan  = "";
                                        val error = formattedResponse.error
                                        val airtimeModel = Models.AirtimeModel(error, mPhoneNumber)

                                        val isCardTransaction = true
                                        val transactionTID = ""
                                        val merchantID = FuncActivity.appState.nibssData.configData.getConfigData("03015").toString()
                                        val merchantName = FuncActivity.appState.nibssData.configData.getConfigData("52040").toString()
                                        val merchantTerminalId = SecureStorage.retrieve(Helper.TERMINAL, "")
//			                          	String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
                                        val vasmerchantID = SecureStorage.retrieve(Helper.VAS_TERMINAL_ID, "")
                                        val vasmerchantName = SecureStorage.retrieve(Helper.VAS_MERCHANT_NAME, "")
//				                        String vasTerminalId = SecureStorage.retrieve(Helper.,"");


//                                     null


//                                        if (airtimetype.equals("wallet", ignoreCase = true)) {
                                        var vasDetails: Models.VasDetails? =Models.VasDetails(stan,amount, wallet, vasmerchantName, merchantID, merchantName, merchantTerminalId, product, responsemessage, vasmerchantID, transactionRef, VasServices.CASH, logo, date, error, Models.DATA, airtimeModel)
//                                        } else if (airtimetype.equals("card", ignoreCase = true)) {
//                                            vasDetails = Models.VasDetails(amount, wallet, vasmerchantName, merchantID, merchantName, merchantTerminalId, product, responsemessage, vasmerchantID, transactionRef, VasServices.CARD, logo, date, error, Models.AIRTIME, airtimeModel)
//                                        }

                                        ElectricityPaymentActivity.print(this@DataPhoneEntry,vasDetails!!)
                                        // PrinterHelper.getInstance().airtimeReceipt(FuncActivity.appState, 1,  model)
                                    } catch (e: Exception) {
                                    }
                                }
                            }.show()
                        }
                    }
                }
                catch (exception: ConnectException) {
                    GlobalScope.launch(Dispatchers.Main){
                        mAirtimeProcessDialog.dismiss()
                        alert {
                            title = "Response"
                            message = "Error in connection. Please check your internet connection"
                            okButton { }
                        }.show()
                    }

                }
                catch (exception: SocketTimeoutException) {
                    GlobalScope.launch(Dispatchers.Main) {
                        mAirtimeProcessDialog.dismiss()
                        alert {
                            title = "Response"
                            message = "This connection is taking too long. Please try again"
                        }.show()
                    }
                }
                catch (e: retrofit2.HttpException) {
                    GlobalScope.launch(Dispatchers.Main) {
                        mAirtimeProcessDialog.dismiss()
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

    private fun payWithCard() {

        val view = View.inflate(this, R.layout.activity_enter_pin, null)
        PinAlertUtils.getPin(this, view){
            mPayvicePin = SecureStorageUtils.hashIt(it!!, mWalletPassword)!!


            FuncActivity.appState.needCard = true
            FuncActivity.appState.withdrawal = true
            val intent = Intent(this, Sale::class.java)
            startActivity(intent)

//            val intent = Intent(this, VasPurchaseProcessor::class.java)
//            intent.putExtra(BasePaymentActivity.TRANSACTION_ACCOUNT_TYPE, AccountType.DEFAULT_UNSPECIFIED)
//
//
//            //times 100 because of the conversion to kobo
//            val amount = dataItem.amount.toLong() * 100
//
//            intent.putExtra(BasePaymentActivity.TRANSACTION_AMOUNT, amount)
//            intent.putExtra(BasePaymentActivity.TRANSACTION_ADDITIONAL_AMOUNT, 0L)
//            startActivityForResult(intent, KEYS.KEY_INTENT_RESULT_CODE)
        }
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

        mPhoneNumber = txtAmount.text.toString()
        if (mPhoneNumber.length != 13) {
            toast("Enter valid number")
            return
        }

        appState.isVas=true
        alert {
            title = "Transaction Type"
            message = "Select the type of transaction you want to make"
            positiveButton(buttonText = "Card") { _ ->
               appState.isWallet=false
                payWithCard()
            }
            negativeButton(buttonText = "Wallet") { _ ->
                appState.isWallet=true
                payWithWallet() }
        }.show()

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

    private fun fixAppend(displayStr: StringBuilder, digit: String) {
        if (displayStr.length < 13) {
            displayStr.append(digit)
            if (displayStr.length == 4)
                displayStr.append(" ")

            if (displayStr.length == 8)
                displayStr.append(" ")

            // fix new input
            txtAmount.text = displayStr.toString()
        }
    }

    private fun fixDelete(displayStr: StringBuilder) {
        txtAmount.text = displayStr.toString()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data_phone_entry)
        initializeAmountEntryElements()

        if (isBeneficiary) {
            txtAmount.text = phoneNumber
            onEnterPressed()
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {

            KEYS.KEY_INTENT_RESULT_CODE -> when (resultCode) {
                Activity.RESULT_OK -> {
                    val state = data?.getSerializableExtra("state") as DeviceState

                    val rrn = data.getStringExtra("rrn")

                    (application as MainApp).poslibdb.transactionResultDao
                            .get(rrn)
                            .observe({ lifecycle }) {
                                this.transactionResult = it!!

                                when (state) {
                                    DeviceState.APPROVED -> {
                                        mProgressDialog.dismiss()
                                        mAirtimeProcessDialog.show()


                                            GlobalScope.launch(Dispatchers.Default){
                                                val payDetails = DataModel.DataSubscriptionDetails(phone = mPhoneNumber.replace(" ", ""), service = dataItem.type, amount = dataItem.amount, description = dataItem.description, code = dataItem.code, user_id = mWalletUsername, terminal_id = mWalletId, password = mWalletPassword, pin = mPayvicePin)

                                                val response = DataService.create().dataSubscribeWithCard(payDetails).await()
                                                val jsonResponse = Gson().toJsonTree(response).asJsonObject
                                                if (jsonResponse.toString().contains("\"error\":false")) {

                                                    val gson = GsonBuilder().excludeFieldsWithoutExposeAnnotation().create()

                                                    val formattedResponse = gson.fromJson(jsonResponse, DataModel.DataSubscriptionSuccessResponse::class.java)

                                                    GlobalScope.launch(Dispatchers.Main){
                                                        mAirtimeProcessDialog.dismiss()
                                                        val terminalID = SecureStorage.retrieve(Helper.TERMINAL, "")
                                                        val amount = formattedResponse.amount
                                                        val ref = formattedResponse.ref
                                                        alert {
                                                            title = "Response"
                                                            message = formattedResponse.message
                                                            positiveButton(buttonText = "Print") {
                                                                var transactionModel: TransactionModel? = null
                                                                val merchantID = FuncActivity.appState.nibssData.configData.getConfigData("03015").toString()
                                                                val merchantName = FuncActivity.appState.nibssData.configData.getConfigData("52040").toString()
                                                                try {
                                                                    val date = SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().time)
                                                                    var bankLogoName = ""
                                                                    try {
                                                                        bankLogoName = "bank" + terminalID.substring(0, 4)
                                                                    } catch (e: Exception) {

                                                                    }
//
//                                                                    transactionModel = TransactionModel(terminalID, ref, "", "", amount, "", "data", "", "Approved", "", merchantID, merchantName, "", "", "", "", "", "", date, "", "", bankLogoName, mPhoneNumber);
//
//                                                                    val intent = Intent(baseContext, MainActivity::class.java)
//
//                                                                    intent.putExtra("transactionModel", transactionModel)
//                                                                    intent.putExtra("copy", "** CUSTOMER COPY **")
//                                                                    startActivity(intent)
//                                                                    val alertDialog = AlertDialog.Builder(baseContext)
//                                                                    alertDialog.setMessage("Print Merchant copy")
//                                                                    val finalTransactionModel = transactionModel
//                                                                    alertDialog.setPositiveButton("OK") { dialogInterface, i ->
//                                                                        val intent = Intent(baseContext, MainActivity::class.java)
//                                                                        intent.putExtra("transactionModel", finalTransactionModel)
//                                                                        intent.putExtra("copy", "*** MERCHANT COPY ***")
//                                                                        startActivity(intent)
//                                                                    }
//                                                                    alertDialog.show()



                                                                    val smartCardNumber = ""
                                                                    val meterNumber = ""
                                                                    val beneficiaryName = ""
                                                                    val beneficiaryAddress = ""
                                                                    val responsemessage = formattedResponse.message
                                                                    val amount = formattedResponse.amount
                                                                    val token = ""
                                                                    val wallet = SecureStorage.retrieve(Helper.TERMINAL_ID, "")
                                                                    val product = "Data"
                                                                    val transactionRef = ""
                                                                    val logo = FuncActivity.appState.logo
                                                                    val stan  = ""
                                                                    val error = false
                                                                    val airtimeModel = Models.AirtimeModel(error, mPhoneNumber)

                                                                    val isCardTransaction = false
                                                                    val transactionTID = ""
                                                                    val merchantID = FuncActivity.appState.nibssData.configData.getConfigData("03015").toString()
                                                                    val merchantName = FuncActivity.appState.nibssData.configData.getConfigData("52040").toString()
                                                                    val merchantTerminalId = SecureStorage.retrieve(Helper.TERMINAL, "")
//			                          	String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
                                                                    val vasmerchantID = SecureStorage.retrieve(Helper.VAS_TERMINAL_ID, "")
                                                                    val vasmerchantName = SecureStorage.retrieve(Helper.VAS_MERCHANT_NAME, "")
//				                        String vasTerminalId = SecureStorage.retrieve(Helper.,"");


                                                                    var vasDetails: Models.VasDetails? = null


//                                        if (airtimetype.equals("wallet", ignoreCase = true)) {
                                                                    vasDetails = Models.VasDetails(stan,amount, wallet, vasmerchantName, merchantID, merchantName, merchantTerminalId, product, responsemessage, vasmerchantID, transactionRef, VasServices.CARD, logo, date, error, Models.DATA, airtimeModel)
//                                        } else if (airtimetype.equals("card", ignoreCase = true)) {
//                                            vasDetails = Models.VasDetails(amount, wallet, vasmerchantName, merchantID, merchantName, merchantTerminalId, product, responsemessage, vasmerchantID, transactionRef, VasServices.CARD, logo, date, error, Models.AIRTIME, airtimeModel)
//                                        }

                                                                    ElectricityPaymentActivity.print(this@DataPhoneEntry,vasDetails)
                                                                    // PrinterHelper.getInstance().airtimeReceipt(FuncActivity.appState, 1,  model)
                                                                } catch (e: Exception) {
                                                                }
                                                            }
                                                        }.show()
                                                    }
                                                } else {
                                                    val jsonResponse = Gson().toJsonTree(response).asJsonObject
                                                    val gson = GsonBuilder().excludeFieldsWithoutExposeAnnotation().create()

                                                    val formattedResponse = gson.fromJson(jsonResponse, DataModel.DataSubscriptionFailedResponse::class.java)
                                                    //todo handle a rollback

                                                    /*(application as App).hostInteractor.rollBackTransaction().observeOn(Schedulers.io())
                                                            .observeOn(AndroidSchedulers.mainThread())
                                                            .subscribe { result, throwable ->
                                                                Log.d("OkH", result.toString())

                                                                throwable?.let {
                                                                    Log.d("OkH", it.toString())
                                                                }
                                                            }*/
                                                    GlobalScope.launch(Dispatchers.Main){
                                                        mAirtimeProcessDialog.dismiss()
                                                        val terminalID = SecureStorage.retrieve(Helper.TERMINAL, "")
                                                        val ref = formattedResponse.ref
                                                        alert {
                                                            title = "Response"
                                                            message = formattedResponse.message
                                                            positiveButton(buttonText = "Print") {
                                                                var transactionModel: TransactionModel? = null
                                                                val merchantID = FuncActivity.appState.nibssData.configData.getConfigData("03015").toString()
                                                                val merchantName = FuncActivity.appState.nibssData.configData.getConfigData("52040").toString()
                                                                try {
                                                                    val date = SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().time)
                                                                    var bankLogoName = ""
                                                                    try {
                                                                        bankLogoName = "bank" + terminalID.substring(0, 4)
                                                                    } catch (e: Exception) {

                                                                    }
//
//                                                                    transactionModel = TransactionModel(terminalID, ref, "", "", dataItem.amount, "", "data", "", "Declined", "", merchantID, merchantName, "", "", "", "", "", "", date, "", "", bankLogoName, mPhoneNumber);
//
//                                                                    val intent = Intent(baseContext, MainActivity::class.java)
//
//                                                                    intent.putExtra("transactionModel", transactionModel)
//                                                                    intent.putExtra("copy", "** CUSTOMER COPY **")
//                                                                    startActivity(intent)
//                                                                    val alertDialog = AlertDialog.Builder(baseContext)
//                                                                    alertDialog.setMessage("Print Merchant copy")
//                                                                    val finalTransactionModel = transactionModel
//                                                                    alertDialog.setPositiveButton("OK") { dialogInterface, i ->
//                                                                        val intent = Intent(baseContext, MainActivity::class.java)
//                                                                        intent.putExtra("transactionModel", finalTransactionModel)
//                                                                        intent.putExtra("copy", "*** MERCHANT COPY ***")
//                                                                        startActivity(intent)
//                                                                    }
//                                                                    alertDialog.show()
                                                                    // PrinterHelper.getInstance().airtimeReceipt(FuncActivity.appState, 1,  model)



                                                                    val smartCardNumber = ""
                                                                    val meterNumber = ""
                                                                    val beneficiaryName = ""
                                                                    val beneficiaryAddress = ""
                                                                    val responsemessage = formattedResponse.message
                                                                    val amount = dataItem.amount
                                                                    val token = ""
                                                                    val wallet = SecureStorage.retrieve(Helper.TERMINAL_ID, "")
                                                                    val product = "Data"
                                                                    val transactionRef = ""
                                                                    val logo = FuncActivity.appState.logo
                                                                    val stan  = ""
                                                                    val error = false
                                                                    val airtimeModel = Models.AirtimeModel(error, mPhoneNumber)

                                                                    val isCardTransaction = true
                                                                    val transactionTID = ""
                                                                    val merchantID = FuncActivity.appState.nibssData.configData.getConfigData("03015").toString()
                                                                    val merchantName = FuncActivity.appState.nibssData.configData.getConfigData("52040").toString()
                                                                    val merchantTerminalId = SecureStorage.retrieve(Helper.TERMINAL, "")
//			                          	String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
                                                                    val vasmerchantID = SecureStorage.retrieve(Helper.VAS_TERMINAL_ID, "")
                                                                    val vasmerchantName = SecureStorage.retrieve(Helper.VAS_MERCHANT_NAME, "")
//				                        String vasTerminalId = SecureStorage.retrieve(Helper.,"");


                                                                    var vasDetails: Models.VasDetails? = null


//                                        if (airtimetype.equals("wallet", ignoreCase = true)) {
                                                                    vasDetails = Models.VasDetails(stan,amount, wallet, vasmerchantName, merchantID, merchantName, merchantTerminalId, product, responsemessage, vasmerchantID, transactionRef, VasServices.CARD, logo, date, error, Models.DATA, airtimeModel)
//                                        } else if (airtimetype.equals("card", ignoreCase = true)) {
//                                            vasDetails = Models.VasDetails(amount, wallet, vasmerchantName, merchantID, merchantName, merchantTerminalId, product, responsemessage, vasmerchantID, transactionRef, VasServices.CARD, logo, date, error, Models.AIRTIME, airtimeModel)
//                                        }

                                                                    ElectricityPaymentActivity.print(this@DataPhoneEntry,vasDetails!!)

                                                                } catch (e: Exception) {
                                                                }
                                                            }
                                                        }.show()
                                                    }
                                                }

                                            }

                                    }

                                    DeviceState.DECLINED, DeviceState.FAILED -> {
                                        //gotoHome()
                                        toast("Card Transaction declined")
                                        //printWalletTransaction(null, transactionResult, transactionResult.transactionStatus)
                                    }
                                }
                            }


                }
            }
        }
    }

//    private fun gotoHome() {
//        val intent = Intent(this@DataPhoneEntry, VasActivity::class.java)
//        finish()
//        startActivity(intent)
//    }

    private fun gotoDataPage() {
        val intent = Intent(this@DataPhoneEntry, DataActivity::class.java)
        finish()
        startActivity(intent)
    }

    object KEYS {
        const val TYPE_OF_DATA_KEY = "type_of_data_key"

        enum class DATA_TYPE {
            GLO, ETISALAT, MTN
        }

        const val KEY_PHONE_NUMBER = "key_phone_number"
        const val KEY_INTENT_RESULT_CODE = 2114
    }


}
