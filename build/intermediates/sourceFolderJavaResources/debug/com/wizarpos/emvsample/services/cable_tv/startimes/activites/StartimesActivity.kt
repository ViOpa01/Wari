package com.iisysgroup.payvice.startimes.activites

import android.app.Activity
import android.arch.lifecycle.Observer
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.cloudpos.card.Card
import com.iisysgroup.payvice.activities.BaseServiceActivity


import com.iisysgroup.payvice.startimes.viewmodel.StartimesViewModel
import com.iisysgroup.poslib.commons.emv.EmvCard
import com.itex.richard.payviceconnect.model.AbujaModel
import com.itex.richard.payviceconnect.model.DstvModel
import com.itex.richard.payviceconnect.model.StartimesModel
import com.jakewharton.rxbinding2.widget.textChanges

import com.wizarpos.emvsample.R
import com.wizarpos.emvsample.activity.FuncActivity
import com.wizarpos.emvsample.activity.FuncActivity.appState
import com.wizarpos.emvsample.activity.FuncActivity.funstan
import com.wizarpos.emvsample.activity.Sale
import com.wizarpos.emvsample.activity.login.Helper
import com.wizarpos.emvsample.activity.login.Helper.WALLET
import com.wizarpos.emvsample.activity.login.securestorage.SecureStorage
import com.wizarpos.emvsample.activity.login.securestorage.SecureStorageUtils
import com.wizarpos.emvsample.generators.PfmStateGenerator
import com.wizarpos.emvsample.models.PfmJournalGenerator
import com.wizarpos.emvsample.services.discos.activities.DiscosActivity.Companion.SERVICE
import com.wizarpos.emvsample.services.discos.activities.ElectricityPaymentActivity
import com.wizarpos.emvsample.services.helper.activity.util.Models
import com.wizarpos.emvsample.services.helper.activity.util.Models.GeneralElectricityDetails
import com.wizarpos.util.PinAlertUtils
import com.wizarpos.util.Service
import com.wizarpos.util.StringUtil
import com.wizarpos.util.VasServices
import kotlinx.android.synthetic.main.activity_login.*

import kotlinx.android.synthetic.main.content_startimes.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.toast
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

class StartimesActivity : BaseServiceActivity() {


//    private var planDialog: MultichoicePlanDialog? = null
    private var selectedPlan: DstvModel.Data? = null
    private var isValidated = false

    private lateinit var toolbar: Toolbar

    private val viewModel by lazy {
        StartimesViewModel(application)
    }


    private val terminalId by lazy {
        SecureStorage.retrieve(Helper.TERMINAL_ID, "")
    }

    private val alertDialog by lazy {
        AlertDialog.Builder(this)
                .setTitle("Validate Card")
                .setNegativeButton(android.R.string.cancel) { _, _ ->
                    viewModel.setSmartCardIsValidated(false)
                    smartcardnumber.requestFocus()
                }.setPositiveButton("Confirm Card") { _, _ ->
                    viewModel.setSmartCardIsValidated(true)
                    otherLayout.visibility = View.VISIBLE
                }.create()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_startimes)

        productText.text="Startimes"
//        toolbar = findViewById(R.id.startimes_toolbar)
//        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        intent?.let {
            it.getStringExtra(SERVICE)?.let {

                service = VasServices.SERVICES[it]!!
                viewModel.setService(service)
            }
        } ?: kotlin.run {
            finish()
        }
    }
    override fun initControls() {
        super.initControls()

        smartcardnumber.hint = VasServices.VAS_SERVICE_INPUT_TEXT_DESC[service.name]

        smartcardnumber.textChanges().delay(300L, TimeUnit.MILLISECONDS)
                .subscribe {
                    it?.let {
                        if (it.length >= 11) {
                            viewModel.validateSmartCardCode(it.toString())
                        }
                    }
                }

//        selectAmountLayout.setOnClickListener {
//            if (isValidated) {
//                viewModel.getPlans().observe(this, Observer<List<DstvModel.Data>> {
//                    it?.let {
//                        planDialog?.dismiss()
//                        planDialog = MultichoicePlanDialog.newInstance(it) {
//                            viewModel.setSelectedPlan(it)
//                        }
//
//                        planDialog?.show(supportFragmentManager, "Plan Dialog")
//                    }
//                })
//            } else {
//                beneficiaryEdit.error = "Smart card is not validated"
//                Helper.showErrorAnim(beneficiaryEdit)
//            }
//        }

        proceedBtn.setOnClickListener {
            if (isValidInput()) {

                val pinView = LayoutInflater.from(this).inflate(R.layout.activity_enter_pin, null, false)

                PinAlertUtils.getPin(this, pinView) {
            val encryptedPassword = SecureStorage.retrieve(Helper.STORED_PASSWORD, "")
            encryptedUserPin = SecureStorageUtils.hashIt(it, encryptedPassword)!!

                    alert {
                        title = "Transaction Type"
                        message = "Select the type of transaction you want to make"
//                        airtime_amount = airtime_amount
                        positiveButton(buttonText = "Card") {
                            //You can use hashmaps
                            _ ->
                            continuePayment(paymentOption =VasServices.CARD, lookupResponse = viewModel.lLookUpResponse.value!!, card = null)

                        }

                        negativeButton(buttonText = "Wallet") { _ ->
                            continuePayment(paymentOption =VasServices.CASH, lookupResponse = viewModel.lLookUpResponse.value!!, card = null)
                        }
                    }.show()

        }
            }
        }

        viewModel.errorWatcher.observe(this, Observer {
            it?.printStackTrace()
            showProgressDialog(false)
//            showError(it)
        })

        viewModel.progressDialogLiveData.observe(this, Observer {
            it?.let {
                val (show, message) = it
                showProgressDialog(show, message)
            }
        })

        viewModel.paymentResponseLiveData.observe(this, Observer { payResponse ->
//            it?.let(this::showResultScreen)
           Log.d("FAct","Print")
            val vasTerminalId=SecureStorage.retrieve(Helper.VAS_TERMINAL_ID,"")
            val vasMerchantName=SecureStorage.retrieve(Helper.VAS_MERCHANT_NAME,"")
            val smartCardNumber = payResponse!!.smartCardCode
            val meterNumber = ""
            val beneficiaryName = ""
            val beneficiaryAddress = ""
            val responsemessage = payResponse!!.message
            val amount = appState.starTimesAmount
            val token = ""
            val wallet = SecureStorage.retrieve(Helper.TERMINAL_ID, "")
            val product = VasServices.STARTIMES
            val transactionRef = payResponse.reference;
            val logo =R.drawable.startimes_print
            val stan  = payResponse.transactionID
            val error = payResponse.error
            val cabletvModel: Models.CableTvModel? = Models.CableTvModel(error=error,iuc =smartCardNumber)
            val isCardTransaction = true
            val transactionTID = ""
            val merchantID = FuncActivity.appState.nibssData.configData.getConfigData("03015").toString()
            val merchantName = FuncActivity.appState.nibssData.configData.getConfigData("52040").toString()
            val merchantTerminalId = SecureStorage.retrieve(Helper.TERMINAL, "")
            val date = SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().time)
            val vasmerchantID = SecureStorage.retrieve(Helper.VAS_TERMINAL_ID, "")
            val vasmerchantName = SecureStorage.retrieve(Helper.VAS_MERCHANT_NAME, "")
//				String vasTerminalId = SecureStorage.retrieve(Helper.,"");


            val vasDetails = Models.VasDetails(stan,amount, wallet, vasmerchantName, merchantID, merchantName, merchantTerminalId, product, responsemessage, vasmerchantID, transactionRef, VasServices.CASH, logo, date, error, Models.CABLE_TV, cabletvModel!!)
            Log.d("About to print", "onChanged() called with: VasDetails = [$vasDetails]")

            ElectricityPaymentActivity.print(this@StartimesActivity,vasDetails)
        })

        viewModel.productLiveData.observe(this, Observer {
            it?.let {
                productText.text = it.name.capitalize()
                selectProductBtn.isEnabled = false
            }
        })

        viewModel.isSmartcardValidated.observe(this, Observer {
            it?.let {
                isValidated = it
            }
        })

        viewModel.iucNameLiveData.observe(this, Observer {
            it?.let {
                if (!alertDialog.isShowing) {
                    alertDialog.setMessage("Customer Name: $it\nSmart Card Number: ${smartcardnumber.text}\n")
                    alertDialog.show()
                }

            }
        })

//        viewModel.selectPlanLiveData.observe(this, Observer {
//            it?.let {
//                selectedPlan = it
//                selectableAmountText.text = "${it.amount} - ${it.name}"
//                planDialog?.dismiss()
//            }
//        })


    }


//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//
//        if (requestCode == UserPinEntryActivity.PIN_REQUEST_CODE) {
//            if (resultCode == Activity.RESULT_OK) {
//                encryptedUserPin = data!!.getStringExtra(UserPinEntryActivity.PIN_RESPONSE_DATA)
//
//                val amount = txtAmount.text.toString().toInt() ?: 0
//                val beneficiary = smartcardnumber.text.toString()
//
//                showPaymentOption(PaymentOption.Mode.PAY, amount, beneficiary)
//            }
//        }
//
//        if (requestCode == PaymentOptionActivity.REQUEST_CODE) {
//            if (resultCode == Activity.RESULT_OK) {
//                val paymentOption = data?.getSerializableExtra(PaymentOptionActivity.RESULT_OPTION) as PaymentOption
//                val card = data.getSerializableExtra(PaymentOptionActivity.RESULT_CARD) as? Card
//                continuePayment(paymentOption, card,viewModel.lLookUpResponse.value!!)
//            }
//        }
//    }


    private fun continuePayment(paymentOption: String, card: Card?, lookupResponse: StartimesModel.lookupResponse) {
//        val smartCardNumber = smartcardnumber.text.toString()
        appState.starTimesAmount=txtAmount.text.toString()
        val amount =txtAmount.text.trim().toString().toInt()*100
        val password = SecureStorage.retrieve(Helper.STORED_PASSWORD,"")
        val customerName=lookupResponse.name
        val phone = EdtTxtBeneficiaryPhoneNo.text.toString()
        val productCode = lookupResponse.productCode
        val bouquet =lookupResponse.bouquet
        val smartCardCode = lookupResponse.smartCardCode
        val authPin = encryptedUserPin

        val pinInfo = EmvCard.PinInfo(FuncActivity.appState.trans.pinBlock, null, null)

        val emvCard = EmvCard(FuncActivity.appState.trans.cardHolderName, FuncActivity.appState.trans.track2Data, FuncActivity.appState.trans.iccData, pinInfo)



        val tid = SecureStorage.retrieve(Helper.TERMINAL_ENTERED_BY_USER, "")



        val pfm = com.itex.richard.payviceconnect.model.Pfm(PfmStateGenerator(this,tid).generateState(), PfmJournalGenerator(FuncActivity.appState.trans.transactionResult, FuncActivity.appState.nibssData.configData, false, amount.toString(), emvCard, "Startimes",bouquet , "").generateJournal())


        val clientReference = StringUtil.getClientRef(this@StartimesActivity, "")

        appState.isVas=true

        when (paymentOption) {


            VasServices.CASH -> {



                appState.isWallet =true
                viewModel.subscribe(amount =amount ,authPin = authPin,password = password,customerName = customerName,phone = phone,productCode = productCode,bouquet =bouquet,paymentMethod =paymentOption,smartCardCode = smartCardCode)
            }

            else -> {
                Log.d("Here ","Going online")
                FuncActivity.appState.cableTv = true
                FuncActivity.appState.needCard = true
                FuncActivity.appState.startimes=true
                appState.isWallet =false
                FuncActivity.appState.trans.transAmount = (Integer.parseInt( appState.starTimesAmount) )* 100

                FuncActivity.appState.startimesPayRequest = StartimesModel.payRequest(amount = amount, wallet = terminalId, username = userName, clientReference = clientReference, smartCardCode = smartCardCode.toLowerCase(), productCode = productCode,  password = password,paymentMethod = VasServices.CARD,type = "default",channel = "ANDROIDPOS",customerName =customerName ,phone =phone ,bouquet =bouquet ,pin =authPin ,pfm =pfm )
                val intent = Intent(this, Sale::class.java)
                startActivityForResult(intent, STARTIMES_REQUEST_CODE_CARD)
            }
        }
    }


    private fun isValidInput(): Boolean {
        if (smartcardnumber.text.length < 10 || !isValidated) {
            smartcardnumber.error = "Invalid Smart card number"
//            Helper.showErrorAnim(smartcardnumber)
            return false
        }

        if (!txtAmount.text.toString().isNullOrEmpty()) {
//
//            if(txtAmount.text.toString().toInt() > 50){
//               toast("Enter valid amount - amount must not be less than 50 Naira")
//
//
//            } else {
//                txtAmount.error = "Invalid Amount"
//                return false
//            }

        }else {
            txtAmount.error = "Invalid Amount"
            return false
        }

        return true
    }


    override fun getHistoryLayout(): View? {
        return historyLayout
    }

    override fun getHistoryListView(): RecyclerView? {
        return list
    }

    override fun getSubTitleView(): TextView? {
        return subTitleText
    }

    override fun getServiceImageView(): ImageView? {
        return serviceImage
    }

    override fun onSelectProduct(product: Service.Product) {

    }


    companion object {
        const val USER_PIN: String = "pin"
        const val STARTIMES_REQUEST_CODE_CARD = 3424
    }

}
