package com.wizarpos.emvsample.activity

import android.app.Activity
import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.widget.Button
import com.iisysgroup.poslib.deviceinterface.DeviceState
import com.iisysgroup.poslib.host.Host
import com.iisysgroup.poslib.host.entities.TransactionResult
import com.iisysgroup.poslib.utils.AccountType
import com.wizarpos.emvsample.MainApp
import com.wizarpos.emvsample.R
import com.wizarpos.emvsample.VasPurchaseProcessor
import com.wizarpos.emvsample.activity.AirtimeActivity.TAGS.AIRTIME_REQUEST_CODE_CARD
import com.wizarpos.emvsample.activity.login.Helper
import com.wizarpos.emvsample.activity.login.securestorage.SecureStorage
import com.wizarpos.emvsample.activity.login.securestorage.SecureStorageUtils
import com.wizarpos.emvsample.generators.PfmStateGenerator
import com.wizarpos.emvsample.payments_menu.BasePaymentActivity
import com.wizarpos.emvsample.printer.PrinterHelper
import com.wizarpos.util.PinAlertUtils
import com.wizarpos.util.SharedPreferenceUtils
import com.wizarpos.util.TransactionModel
import kotlinx.android.synthetic.main.airtime_provider_select.*
import kotlinx.android.synthetic.main.enter_amount.*
import kotlinx.android.synthetic.main.single_transaction.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.jetbrains.anko.*
import org.jetbrains.anko.custom.async
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.text.SimpleDateFormat
import java.util.*


class AirtimeActivity : AirTimeBaseActivity(), AirtimeProcessor.onAirtimeTransactionResultListener {

    private var isCard = false
    private lateinit var transactionResult: TransactionResult

//    private val beneficiariesDao by lazy {
//        (application as MainApp).beneficiariesDatabase.getAirtimeBeneficiariesDao()
//    }

    private val hostInteractor by lazy {
        (application as MainApp).hostInteractor
    }

    private val mTerminalId by lazy {
        SharedPreferenceUtils.getTerminalId(this)

    }

    private lateinit var mPin : String
    private lateinit var mPhoneNumber : String
    private lateinit var mAirtimeProvider : String


    //Boolean value to indicate if current top-up recipient is a beneficiary saved
    private var isBeneficiary = false

    lateinit var constraintLayout: ConstraintLayout


    private val progressDialog by lazy {
        indeterminateProgressDialog(message = "Processing request", title = "Status") {}
    }

    var airtime_amount: String = ""
    var phone_number: String = ""
    var airtime_provider = ""

    lateinit var enter: Button
    lateinit var cancel: Button
    private var ref : String = ""

    var isFromVasPage = false

    override fun onResponse(model: AirtimeSuccessResponse) {

       val terminalID =  SecureStorage.retrieve(Helper.TERMINAL_ID, "")
        if (isBeneficiary) {
            alert {
                title = "Response"
                message = model.message
                positiveButton(buttonText = "Print") {
                    var transactionModel: TransactionModel? = null
                    val merchantID = FuncActivity.appState.nibssData.configData.getConfigData("03015").toString()
                    val merchantName = FuncActivity.appState.nibssData.configData.getConfigData("52040").toString()
                  try{
                      val date = SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().time)
                      var bankLogoName = ""
                      try {
                          bankLogoName = "bank" + terminalID.substring(0, 4)
                      } catch (e: Exception) {

                      }

                      transactionModel = TransactionModel(terminalID, "", "", "", airtime_amount, "", "airtime", "", "Approved", "", merchantID, merchantName, "", "", "", "", "", "", date, "", "", bankLogoName, phone_number);

                      val intent = Intent(baseContext, MainActivity::class.java)

                      intent.putExtra("transactionModel", transactionModel)
                      intent.putExtra("copy", "** CUSTOMER COPY **")
                      startActivity(intent)
                      val alertDialog = AlertDialog.Builder(baseContext)
                      alertDialog.setMessage("Print Merchant copy")
                      val finalTransactionModel = transactionModel
                      alertDialog.setPositiveButton("OK") { dialogInterface, i ->
                          val intent = Intent(baseContext, MainActivity::class.java)
                          intent.putExtra("transactionModel", finalTransactionModel)
                          intent.putExtra("copy", "*** MERCHANT COPY ***")
                          startActivity(intent)
                      }
                      alertDialog.show()
                     // PrinterHelper.getInstance().airtimeReceipt(FuncActivity.appState, 1,  model)
                  }catch (e : Exception){

                  }

                }
            }.show()
        } else {
            alert {
                title = "Beneficiary"
                message = "${model.message}. This number is not currently saved. Would you want to save this number for future transactions"
                ref = model.ref
                yesButton {
                    //addBeneficiary(model)
                }
                negativeButton(buttonText = "No") {
                   // generateReceipt(model)
                }

            }.show()

            var transactionModel: TransactionModel? = null
            val merchantID = FuncActivity.appState.nibssData.configData.getConfigData("03015").toString()
            val merchantName = FuncActivity.appState.nibssData.configData.getConfigData("52040").toString()
            try {
                val date = PfmStateGenerator(this).getCurrentTime()
                var bankLogoName = ""
                try {
                    bankLogoName = "bank" + terminalID.substring(0, 4)
                } catch (e: Exception) {

                }
                transactionModel = TransactionModel(terminalID, ref, "", "", airtime_amount, "", "airtime", "00", "Approved", "", merchantID, merchantName, "", "", "", "", "", "", date, "", "", bankLogoName, phone_number);

                val intent = Intent(baseContext, MainActivity::class.java)

                intent.putExtra("transactionModel", transactionModel)
                intent.putExtra("copy", "** CUSTOMER COPY **")
                startActivity(intent)
                val alertDialog = AlertDialog.Builder(baseContext)
                alertDialog.setMessage("Print Merchant copy")
                val finalTransactionModel = transactionModel
                alertDialog.setPositiveButton("OK") { dialogInterface, i ->
                    val intent = Intent(baseContext, MainActivity::class.java)
                    intent.putExtra("transactionModel", finalTransactionModel)
                    intent.putExtra("copy", "*** MERCHANT COPY ***")
                    startActivity(intent)
                }
                alertDialog.show()
            }catch (e : Exception){

            }
        }

        progressDialog.hide()

    }

    override fun onError(errorMessage: String, isCard: Boolean) {
        val terminalID =  SecureStorage.retrieve(Helper.TERMINAL_ID, "")
        if (isCard) {
            hostInteractor.rollBackTransaction()
        }
        alert {
            title = "Response"
            message = errorMessage
            okButton {
                //finish()
                var transactionModel: TransactionModel? = null
                val merchantID = FuncActivity.appState.nibssData.configData.getConfigData("03015").toString()
                val merchantName = FuncActivity.appState.nibssData.configData.getConfigData("52040").toString()
                try{
                    var bankLogoName = ""
                    try {
                        bankLogoName = "bank" + terminalID.substring(0, 4)
                    } catch (e: Exception) {

                    }
                    val date = SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().time)
                    transactionModel = TransactionModel(terminalID, "", "", "", airtime_amount, "", "airtime", "", "Declined", "", merchantID, merchantName, "", "", "", "", "", "", date, "", "", bankLogoName, phone_number);

                    val intent = Intent(baseContext, MainActivity::class.java)

                    intent.putExtra("transactionModel", transactionModel)
                    intent.putExtra("copy", "** CUSTOMER COPY **")
                    startActivity(intent)
                    val alertDialog = AlertDialog.Builder(baseContext)
                    alertDialog.setMessage("Print Merchant copy")
                    val finalTransactionModel = transactionModel
                    alertDialog.setPositiveButton("OK") { dialogInterface, i ->
                        val intent = Intent(baseContext, MainActivity::class.java)
                        intent.putExtra("transactionModel", finalTransactionModel)
                        intent.putExtra("copy", "*** MERCHANT COPY ***")
                        startActivity(intent)
                    }
                    alertDialog.show()
                    // PrinterHelper.getInstance().airtimeReceipt(FuncActivity.appState, 1,  model)
                }catch (e : Exception){

                }

            }
        }.show()
        progressDialog.hide()
    }


    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return false
    }

    internal override fun getTextLayoutId(): Int {
        return R.id.txtAmount
    }

    internal override fun getMaxCount(): Int {
        return 13
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        setContentView(R.layout.activity_airtime)
        super.onCreate(savedInstanceState)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        enter = findViewById(R.id.btnenter)
        cancel = findViewById(R.id.btncancel)


        constraintLayout = findViewById(R.id.constraint_layout)
        constraintLayout.tag = 0

        amount.text = ""
        enter.setOnClickListener { moveToNextPage() }

        cancel.setOnClickListener { moveToPreviousPage() }

        mtn.setOnClickListener {
            resetAirtimeValues()
            showPhoneNumberScreen()
            airtime_provider = "MTNVTU"
        }
        glo.setOnClickListener {
            resetAirtimeValues()
            showPhoneNumberScreen()
            airtime_provider = "GLOVTU"
        }
        nine_mobile.setOnClickListener {
            resetAirtimeValues()
            showPhoneNumberScreen()
            airtime_provider = "ETISALATVTU"
        }
        airtel.setOnClickListener {
            resetAirtimeValues()
            showPhoneNumberScreen()
            airtime_provider = "AIRTELVTU"
        }


        if (intent.hasExtra(TAGS.AIRTIME_PURCHASE_KEY)) {
            isFromVasPage = intent.getBooleanExtra(TAGS.AIRTIME_PURCHASE_KEY, false)
            airtime_provider = intent.getStringExtra(TAGS.AIRTIME_PURCHASE_PROVIDER_TYPE)
            showPhoneNumberScreen()
        } else if (intent.hasExtra("airtimeModel")) {
//            nine_mobile.performClick()
//            val airtimeModel = intent.getParcelableExtra("airtimeModel") as AirtimeModel
//            phone_number = airtimeModel.phone_number
//            txtAmount.text = phone_number
//
//            enter.performClick()
        }

    }

    private fun resetAirtimeValues() {
        amount.text = ""
        phone_number = ""
        airtime_amount = ""
    }

    private fun payWithWallet(phone_number: String, airtimeProvider: String) {
        isCard = false
        SecureStorage.store("airtimeType", "wallet")
        val pinView = LayoutInflater.from(this).inflate(R.layout.activity_enter_pin, null, false)
        PinAlertUtils.getPin(this, pinView) {
            val encryptedPassword = SecureStorage.retrieve(Helper.STORED_PASSWORD, "")
            val pin = SecureStorageUtils.hashIt(it, encryptedPassword)
            val airtimeProcessor = AirtimeProcessor(this, this, airtimeProvider, phone_number.replace(" ", ""), airtime_amount)
            progressDialog.show()
            try {
                airtimeProcessor.performTransaction(pin = pin!!)
            } catch (error: ConnectException) {
                progressDialog.dismiss()
                toast("Connection error, Check your internet connection")
            } catch (error: SocketTimeoutException) {
                toast("Connection taking too long. Please try again")
            }
        }
    }

    private fun payWithCard(phone_number: String, airtimeProvider: String) {
        val view = View.inflate(this, R.layout.activity_enter_pin, null)
        SecureStorage.store("airtimeType", "card")
        PinAlertUtils.getPin(this, view) {
            //todo validate pin
            val password = SecureStorage.retrieve(Helper.STORED_PASSWORD, "")
            mPin = SecureStorageUtils.hashIt(it, password)!!

            isCard = true
            mAirtimeProvider = airtimeProvider

            SecureStorage.store("phonerecharge", phone_number)
            SecureStorage.store("airtimeprovider", airtimeProvider)
            SecureStorage.store("pinentered", mPin)
            FuncActivity.appState.needCard = true
            FuncActivity.appState.airtime = true
            val intent = Intent(this, Sale::class.java)
            startActivityForResult(intent, AirtimeActivity.TAGS.AIRTIME_REQUEST_CODE_CARD)
        }

//        PinAlertUtils.getPin(this, view) {
//            val encryptedPassword = SecureStorage.retrieve(Helper.STORED_PASSWORD, "")
//            val pin = SecureStorageUtils.hashIt(it, encryptedPassword)
//            val airtimeProcessor = AirtimeProcessor(this, this, airtimeProvider, phone_number.replace(" ", ""), airtime_amount)
//            progressDialog.show()
//            try {
//                airtimeProcessor.performTransaction(pin = pin!!)
//            } catch (error: ConnectException) {
//                progressDialog.dismiss()
//                toast("Connection error, Check your internet connection")
//            } catch (error: SocketTimeoutException) {
//                toast("Connection taking too long. Please try again")
//            }
//            isCard = true
//
//            FuncActivity.appState.needCard = true
//            FuncActivity.appState.airtime = true
//            val intent = Intent(this, Sale::class.java)
//            startActivityForResult(intent, AirtimeActivity.TAGS.AIRTIME_REQUEST_CODE)
//        }


    }

    private fun performTransaction(airtimeProvider: String) {

        if (airtime_amount.isEmpty()) {
            toast("Enter valid amount")
            return
        }

        alert {
            title = "Transaction Type"
            message = "Select the type of transaction you want to make"
            positiveButton(buttonText = "Card") { _ -> payWithCard(phone_number, airtimeProvider) }
            negativeButton(buttonText = "Wallet") { _ -> payWithWallet(phone_number, airtimeProvider) }
        }.show()


    }

    private fun moveToNextPage() {
        when (constraintLayout.tag as Int) {
            1 -> {
                showAmountScreen()
            }
            2 -> {
                if (amount.text.toString().isNotEmpty() || amount.text.toString().toInt() < 50) {
                    airtime_amount = amount.text.toString().replace(" ", "")
                    SecureStorage.store("amountrecharge", airtime_amount.toString())
                    performTransaction(airtime_provider)
                } else {
                    toast("Enter valid amount - amount must not be less than 50 Naira")
                }
            }
        }
    }

    private fun moveToPreviousPage() {
        when (constraintLayout.tag as Int) {
            0 -> {
                startActivity(Intent(this@AirtimeActivity, SelectionActivity::class.java))
                finish()
            }
            1 -> {
                amount.text = phone_number
                showAirtimeProviderScreen()
            }
            2 -> {
                amount.text = airtime_amount
                showPhoneNumberScreen()
            }
        }
    }

    private fun processCardTransaction() {

        val airtimeProcessor = AirtimeProcessor(this, this, airtime_provider, phone_number.replace(" ", ""), airtime_amount)
        progressDialog.show()
        try {
            airtimeProcessor.performTransaction(true, pin = mPin)
        } catch (error: ConnectException) {
            progressDialog.dismiss()
            toast("Connection error, Check your internet connection")
        } catch (error: SocketTimeoutException) {
            toast("Connection taking too long. Please try again")
        } catch (e: retrofit2.HttpException) {
                GlobalScope.launch(Dispatchers.Main){
                progressDialog.dismiss()
                alert {
                    title = "Error"
                    message = "Error from server. Please try again"
                    okButton { }
                }.show()
            }
        }
    }


    private fun showAmountScreen() {
        phone_number = amount.text.toString()
        if (phone_number.length != maxCount) {
            toast("Enter valid phone number")
            return
        }

        naira_sign.visibility = View.VISIBLE
        showVisibility(findViewById(R.id.enter_phone_number_or_amount))

        if (airtime_amount.isNotEmpty()) {
            amount.text = airtime_amount
        } else
            amount.text = ""
        dashboard_title.setText(R.string.amount)
        constraintLayout.tag = 2
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        //super.onActivityResult(requestCode, resultCode, data)

       // if (requestCode !=null && data != null) {
            when (requestCode) {
                TAGS.AIRTIME_REQUEST_CODE -> {
                    when (resultCode) {
                        Activity.RESULT_OK -> {
                            val state = data!!.getSerializableExtra("state") as DeviceState
                            val rrn = data.getStringExtra("rrn")

                            when (state) {
                                DeviceState.FAILED -> {
                                    toast("Transaction failed")

                                }
                                DeviceState.APPROVED -> {
                                    (application as MainApp).poslibdb.transactionResultDao.get(rrn).observe({ lifecycle }) {
                                        transactionResult = it!!
                                        processCardTransaction()
                                    }
                                }
                                DeviceState.DECLINED -> {
                                    toast("Transaction declined")

                                }
                                else -> {
                                }
                            }


                        }
                        Activity.RESULT_CANCELED -> {
                           // toast("Request unsuccessful")
                        }
                    }
                }

                TAGS.AIRTIME_REQUEST_CODE_CARD -> {
                    when (resultCode) {
                        Activity.RESULT_OK -> {
                            val state = data!!.getSerializableExtra("state") as DeviceState
                            val rrn = data.getStringExtra("rrn")

                            when (state) {
                                DeviceState.FAILED -> {
                                    toast("Transaction failed")

                                }
                                DeviceState.APPROVED -> {
                                    (application as MainApp).poslibdb.transactionResultDao.get(rrn).observe({ lifecycle }) {
                                        transactionResult = it!!
                                        Log.d("okh", "card debited doing airtime")
                                            processCardTransaction()
                                    }
                                }
                                DeviceState.DECLINED -> {
                                    toast("Transaction declined")

                                }
                                else -> {
                                }
                            }


                        }
                        Activity.RESULT_CANCELED -> {
                           // toast("Request unsuccessful")
                        }
                    }
                }
            }
       // }
    }

    fun showPhoneNumberScreen() {
        showVisibility(findViewById(R.id.enter_phone_number_or_amount))
        airtime_amount = txtAmount.text.toString()
        naira_sign.visibility = View.GONE

        if (phone_number.isNotEmpty()) {
            amount.text = phone_number
        } else amount.text = ""
        dashboard_title.setText(R.string.phone_number)
        constraintLayout.tag = 1
    }

    fun showAirtimeProviderScreen() {
        phone_number = txtAmount.text.toString()
        showVisibility(findViewById(R.id.airtime_provider_select))
        dashboard_title.setText(R.string.action_select_provider)
        constraintLayout.tag = 0
    }

    override fun onBackPressed() {
        moveToPreviousPage()
    }

    object TAGS {
        const val AIRTIME_REQUEST_CODE = 3423
        const val AIRTIME_REQUEST_CODE_CARD = 3424
        const val AIRTIME_PURCHASE_KEY = "airtime_purchase_key"
        const val AIRTIME_PURCHASE_PROVIDER_TYPE = "airtime_purchase_provider_type"
    }
}
