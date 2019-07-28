package com.wizarpos.emvsample.services.discos.activities

import android.app.Activity
import android.app.AlertDialog
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.v4.content.ContextCompat.startActivity
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
import com.wizarpos.emvsample.services.discos.activities.MeterValidationActivity.Companion.ADDRESS
import com.wizarpos.emvsample.services.discos.activities.MeterValidationActivity.Companion.CLIENT_REFERENCE
import com.wizarpos.emvsample.services.discos.activities.MeterValidationActivity.Companion.ELECTRIC_METER_TYPE
import com.wizarpos.emvsample.services.discos.activities.MeterValidationActivity.Companion.METER_NAME
import com.wizarpos.emvsample.services.discos.activities.MeterValidationActivity.Companion.METER_NUMBER
import com.wizarpos.emvsample.services.discos.activities.MeterValidationActivity.Companion.METER_TYPE
import com.wizarpos.emvsample.services.discos.activities.MeterValidationActivity.Companion.PRODUCT_CODE
import com.wizarpos.emvsample.services.discos.activities.MeterValidationActivity.Companion.REQUEST_TYPE
import com.wizarpos.emvsample.services.discos.viewmodels.EleectricityPaymentVM
import com.wizarpos.emvsample.services.helper.activity.util.Models
import com.wizarpos.util.PinAlertUtils
import com.wizarpos.util.TransactionModel
import com.wizarpos.util.VasServices
import com.wizarpos.util.VasServices.ENUGU_ELECTRICITY_POSTPAID
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

    val address by lazy {
        intent.getStringExtra(ADDRESS)

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
//        Log.i("electricMeterType Electric payment intent  >>", electricMeterType)


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

        mEleectricityPaymentVM!!.lPaymentRes.observe(this, object : Observer<Any> {
            override fun onChanged(paymentResponse: Any?) {

                Log.d("yellow >>>", "Now")

//                					boolean  error=transactionResult.isApproved ?:true
//                					String vasTid =transactionResult.terminalID?:" "
//                					String cardExpiary =transactionResult.cardExpiry?:" "
//                					String cardHolderName=transactionResult.cardHolderName?:" "
                val vasTerminalId=SecureStorage.retrieve(Helper.VAS_TERMINAL_ID,"")
                val vasMerchantName=SecureStorage.retrieve(Helper.VAS_MERCHANT_NAME,"")
                var meterType:String  = meterType
                var meterNumber: String? = ""
                var beneficiaryName: String? = ""
                var beneficiaryAddress: String? = ""
                var responsemessage: String? = ""
                var amount: String? = ""
                var token: String? = ""
                val wallet = SecureStorage.retrieve(Helper.TERMINAL_ID, "")
                var product: String? = ""
                var transactionRef: String? = ""
                var logo = 0
                var stan  =""
                var error = true
                var discosModel: Models.DiscosModel? = null

                val isCardTransaction = true
                val transactionTID = ""

                Log.i("FuncActivity.appState.generalElectricityDetails.toString()  >>", FuncActivity.appState.generalElectricityDetails.toString())

                when (appState.generalElectricityDetails.electricMeterType) {

                    VasServices.ABUJA_ELECTRICITY_POSTPAID, VasServices.ABUJA_ELECTRICITY_PREPAID -> {
                        val response = paymentResponse as AbujaModel.PurchaseResponse?

                        Log.d("appState.generalElectricityDetails.amount >>>", appState.generalElectricityDetails.amount)
                        meterNumber = if(response!!.account.isNullOrEmpty() ) appState.generalElectricityDetails.meterNumber!! else response.account
                        beneficiaryName = if(response.name.isNullOrEmpty() ) appState.generalElectricityDetails.meterName!! else response.name
                        beneficiaryAddress = appState.generalElectricityDetails.address
                        amount =  appState.generalElectricityDetails.amount
                        product = VasServices.ABUJA_ELECTRIC
                        meterType =if (appState.generalElectricityDetails.meterType =="0") VasServices.PREPAID else VasServices.POSTPAID
                        responsemessage = response.message
                        token = if (response.token.isEmpty()) "" else response.token
                        transactionRef = response.reference
                        logo = R.drawable.aedc
                        stan=response.transactionID
                        error = response.error
                        discosModel = Models.DiscosModel(error, beneficiaryName, meterType, stan, "", response.unit_value, response.vat, meterNumber, token, beneficiaryAddress, "", "")

                        //                    responseModel = ResponseModel(response!!.amount.toString(),response.error,response.message)

                    }
                     ENUGU_ELECTRICITY_POSTPAID, VasServices.ENUGU_ELECTRICITY_PREPAID -> {

                        Log.d("yellow >>>", "Now")
                        val response = paymentResponse as EnuguModel.PayResponse
                        meterNumber = appState.generalElectricityDetails.meterNumber!!
                        beneficiaryName = appState.generalElectricityDetails.meterName!!
                        beneficiaryAddress = appState.generalElectricityDetails.address
                        amount =  appState.generalElectricityDetails.amount
//                        meterType =if (appState.generalElectricityDetails.meterType.equals(ENUGU_ELECTRICITY_POSTPAID)) VasServices.POSTPAID else VasServices.PREPAID
                         meterType =meterType
                        product = VasServices.ENUGU_ELECTRIC
                        responsemessage = response.message
                        token = if (response.token!!.isEmpty()) "" else response.token
                        transactionRef =  response.reference!!
                        logo = R.drawable.eedc
                        stan=response.transactionID!!
                        error = response.error!!
                        discosModel = Models.DiscosModel(error, beneficiaryName!!,meterType,stan, "",response.unit_value!!, response.vat!!, meterNumber!!, token!!, beneficiaryAddress!!, response.arrears!!, response.tariff!!)
                        //                    responseModel = ResponseModel(response!!.value.toString(),response!!.error!!,response!!.message!!)

                    }

                    VasServices.EKO_ELECTRICITY_POSTPAID, VasServices.EKO_ELECTRICITY_PREPAID -> {


                        val response = paymentResponse as EkoModel.EkoPayResponse?

                        meterNumber = if(response!!.customerMeterNumber.isNullOrEmpty() ) appState.generalElectricityDetails.meterNumber else response.customerMeterNumber
                        beneficiaryName = appState.generalElectricityDetails.meterName
                        beneficiaryAddress = appState.generalElectricityDetails.address
                        amount =  appState.generalElectricityDetails.amount
//                        meterType =  meterType if (appState.generalElectricityDetails.meterType.equals(ENUGU_ELECTRICITY_POSTPAID)) VasServices.POSTPAID else VasServices.PREPAID
                        product = VasServices.EKO_ELECTRIC
                        token = if (response.token.isEmpty()) "" else response.token
                        transactionRef = response.ref
                        responsemessage = response.message
                        logo = R.drawable.ekedc
                        error = response.error
                        stan=response.transactionID!!
                        discosModel = Models.DiscosModel(error, beneficiaryName,meterType,stan, "", "","", meterNumber!!, token!!, beneficiaryAddress!!, "", "")


                        //                    responseModel = ResponseModel(response!!.amount.toString(),response!!.error!!,response!!.message!!)

                    }
                    VasServices.IBADAN_ELECTRICITY_POSTPAID, VasServices.IBADAN_ELECTRICITY_PREPAID -> {
                        run {
                            val response = paymentResponse as IbadanModel.IbPayResponse?

                            meterNumber = if(response!!.account.isNullOrEmpty() ) appState.generalElectricityDetails.meterNumber!! else response.account
                            beneficiaryName = appState.generalElectricityDetails.meterName!!
                            beneficiaryAddress = appState.generalElectricityDetails.address
                            amount =  appState.generalElectricityDetails.amount
//                            meterType =if (appState.generalElectricityDetails.meterType.equals(ENUGU_ELECTRICITY_POSTPAID)) VasServices.POSTPAID else VasServices.PREPAID
                            token = if (response.token.isEmpty()) "" else response.token
                            transactionRef = response.reference
                            responsemessage = response.message
                            product = VasServices.IBADAN_ELECTRIC
                            logo = R.drawable.ibedc
                            error = response!!.error
                            discosModel = Models.DiscosModel(error, beneficiaryName!!,meterType,stan, "",response.unit_value!!,response.vat!!, meterNumber!!, token!!, beneficiaryAddress!!, "","")

                            //                    responseModel = ResponseModel(response!!.amount.toString(),response!!.error!!,response!!.message!!)
                        }
                    }

                    VasServices.IKEJA_ELECTRICITY_POSTPAID, VasServices.IKEJA_ELECTRICITY_PREPAID -> {
                        run {
                            val response = paymentResponse as IkejaModel.IkejaPayResponse?

                            meterNumber = appState.generalElectricityDetails.meterNumber!!
                            beneficiaryName = appState.generalElectricityDetails.meterName!!
                            beneficiaryAddress = appState.generalElectricityDetails.address
                            amount =  appState.generalElectricityDetails.amount
//                            meterType =if (appState.generalElectricityDetails.meterType.equals(ENUGU_ELECTRICITY_POSTPAID)) VasServices.POSTPAID else VasServices.PREPAID
                            stan=response!!.transactionID!!
                            token = if (response!!.token!!.isEmpty()) "" else response.token
                            transactionRef = response.ref
                            responsemessage = response.message
                            logo = R.drawable.ikedc
                            error = response.error

                            discosModel = Models.DiscosModel(error, beneficiaryName!!,meterType,stan,"", response.unit_value!!, response.vat!!, meterNumber!!, token!!, beneficiaryAddress!!, "", "")

                        }

                    }

                    VasServices.PORTHARCOURT_ELECTRICITY_POSTPAID, VasServices.PORTHARCOURT_ELECTRICITY_PREPAID -> run {

                        val response = paymentResponse as PortharcourtModel.purchaseResponse?

                        meterNumber = appState.generalElectricityDetails.meterNumber!!
                        beneficiaryName = appState.generalElectricityDetails.meterName!!
                        beneficiaryAddress = appState.generalElectricityDetails.address
                        amount =  appState.generalElectricityDetails.amount
//                        meterType =if (appState.generalElectricityDetails.meterType.equals(ENUGU_ELECTRICITY_POSTPAID)) VasServices.POSTPAID else VasServices.PREPAID
                        stan=response!!.transactionID!!
                        token = if (response.token.isEmpty()) "" else response.token
                        transactionRef = response.receiptNumber
                        logo = R.drawable.phdc
                        error = response.error
                        discosModel = Models.DiscosModel(error, beneficiaryName!!,meterType,stan, "", "","", meterNumber!!, token!!, beneficiaryAddress!!, "", "")

                    }
                }

                //              if(isCardTransaction){


                val merchantID = FuncActivity.appState.nibssData.configData.getConfigData("03015").toString()
                val merchantName = FuncActivity.appState.nibssData.configData.getConfigData("52040").toString()
                val merchantTerminalId = SecureStorage.retrieve(Helper.TERMINAL, "")
                val date = SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().time)
                val vasmerchantID = SecureStorage.retrieve(Helper.VAS_TERMINAL_ID, "")
                val vasmerchantName = SecureStorage.retrieve(Helper.VAS_MERCHANT_NAME, "")
//				String vasTerminalId = SecureStorage.retrieve(Helper.,"");


                val vasDetails = Models.VasDetails(stan,amount!!, wallet, vasmerchantName, merchantID, merchantName, merchantTerminalId, product!!, responsemessage!!, vasmerchantID, transactionRef!!, VasServices.CASH, logo, date, error, Models.DISCO, discosModel!!)
                print(this@ElectricityPaymentActivity,vasDetails)


                //              print(responseModel!!.amount,responseModel.error,responseModel!!.response,transactionTID)
                //              }else{
                //                  print(responseModel!!.amount,responseModel.error,responseModel!!.response,)

                //                  transactionResult.
                //              }


            }
        }
        )




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
        constraintLayout.tag = 1
    }


    fun showPhoneNumberScreen() {
        showVisibility(findViewById(R.id.enter_phone_number_or_amount))
        airtime_amount = txtAmount.text.toString()
        naira_sign.visibility = View.GONE

        if (phone_number.isNotEmpty()) {
            amount.text = phone_number
        } else amount.text = ""
        dashboard_title.setText(R.string.phone_number)
        constraintLayout.tag = 0
    }


    private fun moveToNextPage() {
        Log.i("constraintLayout.tag",(constraintLayout.tag).toString())
        when (constraintLayout.tag as Int) {
            0 -> {
                showAmountScreen()
            }
            1 -> {
                if (amount.text.isNotEmpty() ) {
//                    if(amount.text.toString().toInt() > 50){
                    airtime_amount = amount.text.toString().replace(" ", "")
                    SecureStorage.store("amountrecharge", airtime_amount.toString())
                    performTransaction()
//                    } else {
//                        toast("Enter valid amount - amount must not be less than 50 Naira")
//                    }
                } else {
                    toast("amount cannot be Empty")
                }
            }
        }
    }

    private fun moveToPreviousPage() {
        Log.d("constraintLayout.tag as Int",(constraintLayout.tag as Int).toString())
        when (constraintLayout.tag as Int) {

            0 -> {
//                startActivity(Intent(this@ElectricityPaymentActivity, MeterValidationActivity::class.java))
                finish()
            }
//            1 -> {
//                amount.text = phone_number
////                showAirtimeProviderScreen()
//                showAmountScreen()
//            }
            1-> {
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
        FuncActivity.appState.isVas=true

        alert {
            title = "Transaction Type"
            message = "Select the type of transaction you want to make"
            airtime_amount =airtime_amount

            FuncActivity.appState.generalElectricityDetails = Models.GeneralElectricityDetails(amount = airtime_amount, wallet = wallet, userName = userName, requestType = requestType, meterType = meterType.toLowerCase(), meterNumber = meterNumber, channel = channel, phone_number = phone_number, productCode = productCode, paymentMetod = VasServices.CARD, clientReference = clientReference, terminalId = terminalId, electricMeterType = electricMeterType, password = password, meterName = meterName,address =address )

            positiveButton(buttonText = "Card") {
                //You can use hashmaps
                _-> payWithCard(phone_number, "")
                appState.isWallet=false
                FuncActivity.appState.trans.transAmount = (Integer.parseInt(airtime_amount) )* 100

                Log.d("requestType>>>",requestType)
            }

            negativeButton(buttonText = "Wallet") { _ ->
                appState.isWallet=true
                payWithWallet(activity = this@ElectricityPaymentActivity,amount = airtime_amount,wallet = wallet,userName = userName,requestType = requestType,meterType =meterType ,meterNumber = meterNumber,channel = channel,phone_number = phone_number,productCode = productCode,paymentMetod = VasServices.CASH) }
        }.show()


    }

    override fun onBackPressed() {
        moveToPreviousPage()
    }







    private fun payWithWallet(phone_number: String, activity: Activity, amount: String, wallet: String, userName: String, requestType: String, meterType: String, meterNumber: String, channel: String, productCode: String, paymentMetod: String) {
//        isCard = false
//        SecureStorage.store("airtimeType", "wallet")
        appState.isWallet=true
        val pinInfo = EmvCard.PinInfo(FuncActivity.appState.trans.pinBlock, null, null)

        val emvCard = EmvCard(appState.trans.cardHolderName, appState.trans.track2Data, appState.trans.iccData, pinInfo)

        val pfm = Pfm(PfmStateGenerator(this).generateState(), PfmJournalGenerator(FuncActivity.appState.trans.transactionResult, FuncActivity.appState.nibssData.configData, false, airtime_amount, emvCard, electricMeterType, electricMeterType, "").generateJournal())


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



            SecureStorage.store("phonerecharge", phone_number)
            SecureStorage.store("airtimeprovider", airtimeProvider)
            SecureStorage.store(USER_PIN, mPin)
            FuncActivity.appState.electricityBills = true
            FuncActivity.appState.needCard = true
            val intent = Intent(this, Sale::class.java)
            startActivityForResult(intent, ELECTRICITY_REQUEST_CODE_CARD)



        }


    }





    companion object{
          const val  USER_PIN: String ="pin"
          const val ELECTRICITY_REQUEST_CODE_CARD = 3424
//          const val  USER_PASSWORD: String ="password"
//        const val  USER_PASSWORD: String ="password"



         fun print(context:Context,vasDetails:Models.VasDetails ) {
            val terminalID = SecureStorage.retrieve(Helper.TERMINAL, "")
            var status = ""
//        if (!response!!.error)
            if (!vasDetails.error) {
                status = "Approved"
//                appState.
            }else{
                "Declined"
            }

            Log.i("Here", "Here from wallet")

                var transactionModel: TransactionModel? = TransactionModel(cardDetails = Models.CardDetails(),VasDetails = vasDetails)

                try {
                    var bankLogoName = ""
                    try {
                        bankLogoName = "bank" + terminalID.substring(0, 4)
                    } catch (e: Exception) {

                    }

                    val intent = Intent(context, MainActivity::class.java)
                    Log.d("About to print", "onChanged() called with: transactionModel = [$transactionModel]")

                    intent.putExtra("transactionModel", transactionModel)
                    intent.putExtra("copy", "** CUSTOMER COPY **")
                    context.startActivity(intent)

                } catch (e: Exception) {

                }
//          context.applicationContext.
        }

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


