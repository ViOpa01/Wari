package com.wizarpos.emvsample.services.cable_tv.multichoice.activities

import android.app.Activity
import android.arch.lifecycle.Observer
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.cloudpos.card.Card
import com.iisysgroup.payvice.activities.BaseServiceActivity
import com.iisysgroup.payvice.baseimpl.viewmodel.MultichoiceViewModel
import com.iisysgroup.payvice.dialogs.MultichoicePlanDialog
import com.iisysgroup.payvice.startimes.activites.StartimesActivity
import com.iisysgroup.poslib.commons.emv.EmvCard
import com.itex.richard.payviceconnect.model.DstvModel
import com.itex.richard.payviceconnect.model.StartimesModel
import com.jakewharton.rxbinding2.widget.textChanges
import com.wizarpos.emvsample.R
import com.wizarpos.emvsample.activity.FuncActivity
import com.wizarpos.emvsample.activity.Sale
import com.wizarpos.emvsample.activity.login.Helper
import com.wizarpos.emvsample.activity.login.securestorage.SecureStorage
import com.wizarpos.emvsample.activity.login.securestorage.SecureStorageUtils
import com.wizarpos.emvsample.generators.PfmStateGenerator
import com.wizarpos.emvsample.models.PfmJournalGenerator
import com.wizarpos.emvsample.services.discos.activities.DiscosActivity.Companion.SERVICE
import com.wizarpos.util.PinAlertUtils
import com.wizarpos.util.Service
import com.wizarpos.util.StringUtil
import com.wizarpos.util.VasServices
import kotlinx.android.synthetic.main.activity_multichoice.*
import kotlinx.android.synthetic.main.content_multichoice.*
import kotlinx.android.synthetic.main.content_multichoice.historyLayout
import kotlinx.android.synthetic.main.content_multichoice.list
import kotlinx.android.synthetic.main.content_multichoice.proceedBtn
import kotlinx.android.synthetic.main.content_multichoice.productText
import kotlinx.android.synthetic.main.content_multichoice.selectAmountLayout
import kotlinx.android.synthetic.main.content_multichoice.selectProductBtn
import kotlinx.android.synthetic.main.content_multichoice.serviceImage
import kotlinx.android.synthetic.main.content_multichoice.subTitleText
import kotlinx.android.synthetic.main.content_startimes.*
import org.jetbrains.anko.alert
import java.util.concurrent.TimeUnit

class MultichoiceActivity : BaseServiceActivity() {


    private var planDialog: MultichoicePlanDialog? = null
    private var selectedPlan: DstvModel.Data? = null
    private var isValidated = false

    private val viewModel by lazy {
        MultichoiceViewModel(application)
    }

    private val terminalId by lazy {
        SecureStorage.retrieve(Helper.TERMINAL_ID, "")
    }

    private val alertDialog by lazy {
        AlertDialog.Builder(this)
                .setTitle("Validate Card")
                .setNegativeButton(android.R.string.cancel) { _, _ ->
                    viewModel.setSmartCardIsValidated(false)
                    beneficiaryEdit.requestFocus()
                }.setPositiveButton("Confirm Card") { _, _ ->
                    viewModel.setSmartCardIsValidated(true)
                }.create()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_multichoice)
        setSupportActionBar(toolbar)

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

        beneficiaryEdit.hint = VasServices.VAS_SERVICE_INPUT_TEXT_DESC[service.name]

        beneficiaryEdit.textChanges().delay(300L, TimeUnit.MILLISECONDS)
                .subscribe {
                    it?.let {
                        if (it.length >= 10) {
                            viewModel.validateIuc(it.toString())
                        }
                    }
                }

        selectAmountLayout.setOnClickListener {
            if (isValidated) {
                viewModel.getPlans().observe(this, Observer<List<DstvModel.Data>> {
                    it?.let {
                        planDialog?.dismiss()
                        planDialog = MultichoicePlanDialog.newInstance(it) {
                            viewModel.setSelectedPlan(it)
                        }

                        planDialog?.show(supportFragmentManager, "Plan Dialog")
                    }
                })
            } else {
                beneficiaryEdit.error = "Smart card is not validated"
//                Helper.showErrorAnim(beneficiaryEdit)
            }
        }

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
                            continuePayment(paymentOption =VasServices.CARD, lookupResponse = viewModel.lValidationResponse.value!!, card = null)

                        }

                        negativeButton(buttonText = "Wallet") { _ ->
                            continuePayment(paymentOption =VasServices.CASH, lookupResponse = viewModel.lValidationResponse.value!!, card = null)
                        }
                    }.show()

                }
            }
        }

        viewModel.errorWatcher.observe(this, Observer {
            it?.printStackTrace()
            showProgressDialog(false)
            showError(it)
        })

        viewModel.progressDialogLiveData.observe(this, Observer {
            it?.let {
                val (show, message) = it
                showProgressDialog(show, message)
            }
        })

        viewModel.paymentResponseLiveData.observe(this, Observer {
//            it?.let(this::showResultScreen)
        })

        viewModel.productLiveData.observe(this, Observer {
            it?.let {
                productText.text = it.name.capitalize()
                selectProductBtn.isEnabled = false
            }
        })

        viewModel.isIucValidated.observe(this, Observer {
            it?.let {
                isValidated = it
            }
        })

        viewModel.iucNameLiveData.observe(this, Observer {
            it?.let {
                if (!alertDialog.isShowing) {
                    alertDialog.setMessage("Customer Name: $it\nIUC: ${beneficiaryEdit.text}")
                    alertDialog.show()
                }

            }
        })

        viewModel.selectPlanLiveData.observe(this, Observer {
            it?.let {
                selectedPlan = it
                selectableAmountText.text = "${it.amount} - ${it.name}"
                planDialog?.dismiss()
            }
        })


    }


//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//
//        if (requestCode == UserPinEntryActivity.PIN_REQUEST_CODE) {
//            if (resultCode == Activity.RESULT_OK) {
//                encryptedUserPin = data!!.getStringExtra(UserPinEntryActivity.PIN_RESPONSE_DATA)
//
//                val amount = selectedPlan?.amount?.toDouble()?.toInt() ?: 0
//                val beneficiary = beneficiaryEdit.text.toString()
//
//                showPaymentOption(PaymentOption.Mode.PAY, amount, beneficiary)
//            }
//        }
//
//        if (requestCode == PaymentOptionActivity.REQUEST_CODE) {
//            if (resultCode == Activity.RESULT_OK) {
//                val paymentOption = data?.getSerializableExtra(PaymentOptionActivity.RESULT_OPTION) as PaymentOption
//                val card = data.getSerializableExtra(PaymentOptionActivity.RESULT_CARD) as? Card
//                continuePayment(paymentOption, card)
//            }
//        }
//    }


    private fun continuePayment(paymentOption: String, card: Card?,lookupResponse: DstvModel.DstvResponse) {
        val iucNumber = beneficiaryEdit.text.toString()

        val amount =viewModel.selectPlanLiveData.value?.amount?.toDouble()?.toInt() ?: 0
        val password = SecureStorage.retrieve(Helper.STORED_PASSWORD,"")
//        val customerName=lookupResponse.fullname
//        val phone = EdtTxtBeneficiaryPhoneNo.text.toString()
        val productCode = viewModel.selectPlanLiveData.value!!.product_code
//        val smartCardCode = lookupResponse.
        val authPin = encryptedUserPin


        val pinInfo = EmvCard.PinInfo(FuncActivity.appState.trans.pinBlock, null, null)

        val emvCard = EmvCard(FuncActivity.appState.trans.cardHolderName, FuncActivity.appState.trans.track2Data, FuncActivity.appState.trans.iccData, pinInfo)

        val pfm = com.itex.richard.payviceconnect.model.Pfm(PfmStateGenerator(this).generateState(), PfmJournalGenerator(FuncActivity.appState.trans.transactionResult, FuncActivity.appState.nibssData.configData, false, amount.toString(), emvCard,productCode ,viewModel.productLiveData.value!!.toString() , "").generateJournal())


        val clientReference = StringUtil.getClientRef(this@MultichoiceActivity, "")
        when (paymentOption) {
            VasServices.CASH -> {
                viewModel.subscribe(iucNumber, encryptedUserPin)
            }

            else -> {
//                val cardProcessor = DebitCardProcessor(this, encryptedUserPin)
//                viewModel.subscribe(iucNumber, encryptedUserPin)

                FuncActivity.appState.cableTv = true
                FuncActivity.appState.needCard = true
                FuncActivity.appState.dstvPayRequest = DstvModel.PayDetails(clientReference = clientReference,password = password,iuc = iucNumber,product_code = productCode,user_id = userId,terminal_id = terminalId,pin = authPin,unit =product.toString(),pfm = pfm )
                val intent = Intent(this, Sale::class.java)
                startActivityForResult(intent, StartimesActivity.STARTIMES_REQUEST_CODE_CARD)
            }
        }
    }


    private fun isValidInput(): Boolean {
        if (beneficiaryEdit.text.length < 10 || !isValidated) {
            beneficiaryEdit.error = "Invalid Smart card"
//            Helper.showErrorAnim(beneficiaryEdit)
            return false
        }

        if (selectedPlan == null) {
            selectableAmountText.error = "Select plan"
//            Helper.showErrorAnim(selectAmountLayout)
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

    fun Activity.showError(throwable: Throwable?) {
        throwable?.let {
            val message = if (it.localizedMessage.isNullOrEmpty()) {
                it.cause?.localizedMessage ?: "Error occurred. Please try again"
            } else {
                it.localizedMessage
            }
            Helper.showInfoDialog(this, "Error", message)
        }
    }
}