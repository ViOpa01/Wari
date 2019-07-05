package com.wizarpos.emvsample.services.discos.activities

import android.app.Activity
import android.app.AlertDialog
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import com.iisysgroup.poslib.commons.emv.EmvCard
import com.iisysgroup.poslib.deviceinterface.DeviceState
import com.iisysgroup.poslib.host.entities.TransactionResult
import com.itex.richard.payviceconnect.model.*
import com.wizarpos.emvsample.MainApp
import com.wizarpos.emvsample.R
import com.wizarpos.emvsample.activity.*
import com.wizarpos.emvsample.activity.AirtimeVasActivity.TAGS.AIRTIME_REQUEST_CODE_CARD
import com.wizarpos.emvsample.activity.FuncActivity.appState
import com.wizarpos.emvsample.activity.login.Helper
import com.wizarpos.emvsample.activity.login.securestorage.SecureStorage
import com.wizarpos.emvsample.activity.login.securestorage.SecureStorageUtils
import com.wizarpos.emvsample.generators.PfmStateGenerator
import com.wizarpos.emvsample.models.PfmJournalGenerator
import com.wizarpos.emvsample.services.discos.activities.MeterValidationActivity.Companion.CLIENT_REFERENCE
import com.wizarpos.emvsample.services.discos.activities.MeterValidationActivity.Companion.ELECTRIC_METER_TYPE
import com.wizarpos.emvsample.services.discos.activities.MeterValidationActivity.Companion.METER_NAME
import com.wizarpos.emvsample.services.discos.activities.MeterValidationActivity.Companion.METER_NUMBER
import com.wizarpos.emvsample.services.discos.activities.MeterValidationActivity.Companion.METER_TYPE
import com.wizarpos.emvsample.services.discos.activities.MeterValidationActivity.Companion.PRODUCT_CODE
import com.wizarpos.emvsample.services.discos.activities.MeterValidationActivity.Companion.REQUEST_TYPE
import com.wizarpos.emvsample.services.discos.viewmodels.EleectricityPaymentVM
import com.wizarpos.emvsample.services.helper.activity.util.GeneralElectricityDetails
import com.wizarpos.util.PinAlertUtils
import com.wizarpos.util.TransactionModel
import com.wizarpos.util.VasServices
import kotlinx.android.synthetic.main.enter_amount.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.longToast
import org.jetbrains.anko.toast
import org.jetbrains.anko.yesButton
import java.text.SimpleDateFormat
import java.util.*

open class ElectricityPaymentActivity : BaseVasActivity() {


    lateinit var enter: Button
    lateinit var cancel: Button
    private var ref : String = ""
    lateinit var constraintLayout: ConstraintLayout



    var airtime_amount: String = ""
    var phone_number: String = ""
    var airtime_provider = ""


    private lateinit var transactionResult: TransactionResult

//    private val beneficiariesDao by lazy {
//        (application as MainApp).beneficiariesDatabase.getAirtimeBeneficiariesDao()
//    }

    private val hostInteractor by lazy {
        (application as MainApp).hostInteractor
    }


    val password by lazy {
        intent.getStringExtra(Helper.PASSWORD)
    }


    val userName by lazy {
        intent.getStringExtra(Helper.USERNAME)
    }

    val wallet by lazy {
        intent.getStringExtra(Helper.WALLET)
    }

    val channel by lazy {
        intent.getStringExtra(Helper.CHANNEL)

    }

    val requestType by lazy {
        intent.getStringExtra(REQUEST_TYPE)

    }



    val meterNumber by lazy {
        intent.getStringExtra(METER_NUMBER)
    }

    val meterType by lazy {
        intent.getStringExtra(METER_TYPE)
    }

    val meterName by lazy {
        intent.getStringExtra(METER_NAME)
    }

    val productCode by lazy {
        intent.getStringExtra(PRODUCT_CODE)
    }

    val electricMeterType by lazy {
        intent.getStringExtra(ELECTRIC_METER_TYPE)
    }

    val terminalId by lazy {
        intent.getStringExtra(MeterValidationActivity.TERMINAL_ID)
    }

    val clientReference by lazy {
        intent.getStringExtra(CLIENT_REFERENCE)
    }

    private var isBeneficiary: Boolean= false


//    clientReference = ,terminalId = ,electricMeterType =

    private var mEleectricityPaymentVM:EleectricityPaymentVM?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        setContentView(R.layout.activity_amount_phone__no)
        super.onCreate(savedInstanceState)


        enter = findViewById(R.id.btnenter)
        cancel = findViewById(R.id.btncancel)


        constraintLayout = findViewById(R.id.constraint_layout)
        constraintLayout.tag = 0

        amount.text = ""

        mEleectricityPaymentVM=ViewModelProviders.of(this).get(EleectricityPaymentVM::class.java)
        enter.setOnClickListener {
            moveToNextPage()
        }

        cancel.setOnClickListener {
            moveToPreviousPage()
        }


        showPhoneNumberScreen()






    }



    private fun print(amount: String, error: Boolean, responsemessage: String, transactionTID: String, isCardTransaction: Boolean, vasTid: String, cardExpiary: String, smartCardNumber: String, meterNumber: String, beneficiaryName: String, beneficiaryAddress: String,cardHolderName:String){
        val terminalID =  SecureStorage.retrieve(Helper.TERMINAL, "")
        var status = "Declined"
//        if (!response!!.error)
        if (!error)

        {
            status = "Approved"
        }

        Log.i("Here","Here from wallet")
        if (isBeneficiary) {
            alert {
                title = "Response"
                message =responsemessage
                positiveButton(buttonText = "Print") {
                    var transactionModel: TransactionModel? = null
                    val merchantID = FuncActivity.appState.nibssData.configData.getConfigData("03015").toString()
                    val merchantName = FuncActivity.appState.nibssData.configData.getConfigData("52040").toString()

                    try{
                        val date = SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().time)
                        var bankLogoName = ""
                        try {
                            bankLogoName = "bank" + terminalID.substring(0, 4)
                            Log.d("bank  >>> ",bankLogoName)
                        } catch (e: Exception) {

                        }

//                        transactionModel = TransactionModel(terminalID, "", "", "", amount,"", "airtime", "", status, "", merchantID, merchantName, "", "", "", "", "", "", date, "", "", bankLogoName, "Pkdnudhd",isCardTransaction,vasTid,cardExpiary,beneficiaryName,beneficiaryAddress,meterNumber,smartCardNumber);
                        transactionModel = TransactionModel(terminalID, "", "", "", amount,"", "airtime", "", status, "", merchantID, merchantName, "", "", "", "", "", "", date, "", "", bankLogoName, "Pkdnudhd");

                        val intent = Intent(baseContext, MainActivity::class.java)

                        intent.putExtra("transactionModel", transactionModel)
                        intent.putExtra("copy", "** CUSTOMER COPY **")
                        startActivity(intent)
//                        val alertDialog = AlertDialog.Builder(baseContext)
//                        alertDialog.setMessage("Print Merchant copy")
//                        val finalTransactionModel = transactionModel
//                        alertDialog.setPositiveButton("OK") { dialogInterface, i ->
//                            val intent = Intent(baseContext, MainActivity::class.java)
//                            intent.putExtra("transactionModel", finalTransactionModel)
//                            intent.putExtra("copy", "*** MERCHANT COPY ***")
//                            startActivity(intent)
//                        }
//                        alertDialog.show()
                        // PrinterHelper.getInstance().airtimeReceipt(FuncActivity.appState, 1,  model)
                    }catch (e : Exception){

                    }

                }
            }.show()
        } else {
            alert {
                title = "Beneficiary"
                message = "${responsemessage}. This number is not currently saved. Would you want to save this number for future transactions"
//                        ref = abujaPurchaseResponse.ref
                yesButton {
                    //addBeneficiary(model)
                }
                negativeButton(buttonText = "No") {
                    //                                                 generateReceipt(model)
                }

            }.show()

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
                transactionModel = TransactionModel(terminalID, ref, "", "", amount, "", "airtime", "", status, "", merchantID, merchantName, "", "", "", "", "", "", date, "", "", bankLogoName, "yrryrry");

                val intent = Intent(baseContext, MainActivity::class.java)

                intent.putExtra("transactionModel", transactionModel)
                intent.putExtra("copy", "** CUSTOMER COPY **")
                startActivity(intent)
//                val alertDialog = AlertDialog.Builder(baseContext)
//                alertDialog.setMessage("Print Merchant copy")
//                val finalTransactionModel = transactionModel
//                alertDialog.setPositiveButton("OK") { dialogInterface, i ->
//                    val intent = Intent(baseContext, MainActivity::class.java)
//                    intent.putExtra("transactionModel", finalTransactionModel)
//                    intent.putExtra("copy", "*** MERCHANT COPY ***")
//                    startActivity(intent)
//                }
//                alertDialog.show()
            }catch (e : Exception){

            }
        }
    }


    override fun getTextLayoutId(): Int {
        return R.id.txtAmount
    }

    override fun getMaxCount(): Int {
        return 13
    }


    private fun resetAirtimeValues() {
        amount.text = ""
        phone_number = ""
        airtime_amount = ""
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


    private fun moveToNextPage() {
        Log.i("constraintLayout.tag",(constraintLayout.tag).toString())
        when (constraintLayout.tag as Int) {
            1 -> {
                showAmountScreen()
            }
            2 -> {
                if (amount.text.toString().isNotEmpty() || amount.text.toString().toInt() < 50) {
                    airtime_amount = amount.text.toString().replace(" ", "")
                    SecureStorage.store("amountrecharge", airtime_amount.toString())
                    performTransaction()
                } else {
                    toast("Enter valid amount - amount must not be less than 50 Naira")
                }
            }
        }
    }

    private fun moveToPreviousPage() {
        when (constraintLayout.tag as Int) {
            0 -> {
                startActivity(Intent(this@ElectricityPaymentActivity, MeterValidationActivity::class.java))
                finish()
            }
            1 -> {
                amount.text = phone_number
//                showAirtimeProviderScreen()
                showAmountScreen()
            }
            2 -> {
                amount.text = airtime_amount
                showPhoneNumberScreen()
            }
        }
    }

    private fun performTransaction() {

        if (airtime_amount.isEmpty()) {
            toast("Enter valid amount")
            return
        }

        alert {
            title = "Transaction Type"
            message = "Select the type of transaction you want to make"
            airtime_amount =airtime_amount
            positiveButton(buttonText = "Card") {
                //You can use hashmaps
                _-> payWithCard(phone_number, "")
            }

            negativeButton(buttonText = "Wallet") { _ ->
                payWithWallet(activity = this@ElectricityPaymentActivity,amount = airtime_amount,wallet = wallet,userName = userName,requestType = requestType,meterType =meterType ,meterNumber = meterNumber,channel = channel,phone_number = phone_number,productCode = productCode,paymentMetod = VasServices.CASH) }
        }.show()


    }

    override fun onBackPressed() {
        moveToPreviousPage()
    }







    private fun payWithWallet(phone_number: String, activity: Activity, amount: String, wallet: String, userName: String, requestType: String, meterType: String, meterNumber: String, channel: String, productCode: String, paymentMetod: String) {
//        isCard = false
//        SecureStorage.store("airtimeType", "wallet")

        val pinInfo = EmvCard.PinInfo(FuncActivity.appState.trans.pinBlock, null, null)

        val emvCard = EmvCard(appState.trans.cardHolderName, appState.trans.track2Data, appState.trans.iccData, pinInfo)

        val pfm = com.itex.richard.payviceconnect.model.Pfm(PfmStateGenerator(this).generateState(), PfmJournalGenerator(appState.trans.transactionResult, appState.nibssData.configData, false, airtime_amount, emvCard, electricMeterType, electricMeterType, "").generateJournal())


        val pinView = LayoutInflater.from(this).inflate(R.layout.activity_enter_pin, null, false)
        PinAlertUtils.getPin(this, pinView) {
            val encryptedPassword = SecureStorage.retrieve(Helper.STORED_PASSWORD, "")
            val pin = SecureStorageUtils.hashIt(it, encryptedPassword)
            mEleectricityPaymentVM!!.payElectricBill(activity = activity,amount = amount,wallet = wallet,userName = userName,requestType = requestType,meterType = meterType.toLowerCase(),meterNumber = meterNumber,channel = channel,phone = phone_number,productCode = productCode,pin = pin!!,paymentMetod = paymentMetod,password = password,customerName = meterName,clientReference = clientReference,terminalId = terminalId,electricMeterType =electricMeterType,pfm = pfm)

        }
    }


//    fun processPayment(amount: String, wallet: String, userName: String, requestType: String, meterType: String, meterNumber: String, channel: String, phone_number: String, productCode: String, paymentMetod: String){
//       fun processPayment(generalElectricityDetails: GeneralElectricityDetails) {
//        val pin = SecureStorage.retrieve(USER_PIN, "")
//        mEleectricityPaymentVM!!.payElectricBill(activity = this ,amount = generalElectricityDetails.amount,wallet = generalElectricityDetails.wallet,userName = generalElectricityDetails.userName,requestType = generalElectricityDetails.requestType,meterType = generalElectricityDetails.meterType.toLowerCase(),meterNumber = generalElectricityDetails.meterNumber,channel = generalElectricityDetails.channel,phone = generalElectricityDetails.phone_number,productCode = generalElectricityDetails.productCode,pin = pin!!,paymentMetod = VasServices.CARD,clientReference = generalElectricityDetails.clientReference,terminalId = generalElectricityDetails.terminalId,electricMeterType =generalElectricityDetails.electricMeterType,password = generalElectricityDetails.password,customerName = generalElectricityDetails.meterName)
////        SecureStorage.store("pinentered", "")
//    }


    private fun payWithCard(phone_number: String, airtimeProvider: String) {
        val view = View.inflate(this, R.layout.activity_enter_pin, null)
        PinAlertUtils.getPin(this, view) {
           val password = SecureStorage.retrieve(Helper.STORED_PASSWORD, "")
           val mPin = SecureStorageUtils.hashIt(it, password)!!

            appState.generalElectricityDetails = GeneralElectricityDetails(amount = airtime_amount, wallet = wallet, userName = userName, requestType = requestType, meterType = meterType.toLowerCase(), meterNumber = meterNumber, channel = channel, phone_number = phone_number, productCode = productCode, paymentMetod = VasServices.CARD, clientReference = clientReference, terminalId = terminalId, electricMeterType = electricMeterType, password = password, meterName = meterName)

            SecureStorage.store("phonerecharge", phone_number)
            SecureStorage.store("airtimeprovider", airtimeProvider)
            SecureStorage.store(USER_PIN, mPin)
            FuncActivity.appState.electricityBills = true

            val intent = Intent(this, Sale::class.java)
            startActivityForResult(intent, AIRTIME_REQUEST_CODE_CARD)


        }


    }

    companion object{
          const val  USER_PIN: String ="pin"
          const val AIRTIME_REQUEST_CODE_CARD = 3424
//          const val  USER_PASSWORD: String ="password"
//        const val  USER_PASSWORD: String ="password"
    }

  override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        //super.onActivityResult(requestCode, resultCode, data)


        when (requestCode) {



            AirtimeVasActivity.TAGS.AIRTIME_REQUEST_CODE_CARD -> {
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

                    }
                }
            }
        }

    }

}


