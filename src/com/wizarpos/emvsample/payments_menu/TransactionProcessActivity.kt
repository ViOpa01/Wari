package com.wizarpos.emvsample.payments_menu

import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.preference.PreferenceManager
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import com.iisysgroup.poslib.deviceinterface.EmvCardReader
import com.iisysgroup.poslib.host.Host
import com.iisysgroup.poslib.host.entities.ConnectionData
import com.iisysgroup.poslib.host.entities.TransactionResult
import com.iisysgroup.poslib.utils.AccountType
import com.iisysgroup.poslib.utils.InputData
import com.iisysgroup.poslib.utils.Utilities.parseLongIntoNairaKoboString
import com.wizarpos.emvsample.MainApp
import com.wizarpos.emvsample.R
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.insert_card.*
import kotlinx.android.synthetic.main.transaction_status.*
import kotlinx.coroutines.experimental.runBlocking
import org.jetbrains.anko.alert
import org.jetbrains.anko.cancelButton
import org.jetbrains.anko.doAsync
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@SuppressLint("MissingPermission")
class TransactionProcessActivity : AppCompatActivity(), Callback<Any>{

    override fun onFailure(call: Call<Any>?, t: Throwable?) {
        Log.d("Payvice lookup Error", t.toString())
    }

    override fun onResponse(call: Call<Any>?, response: Response<Any>?) {
        Log.d("Payvice lookup Response", response?.body().toString())
    }

//    private val printerInteractor by lazy {
//        PrinterInteractor.getInstance(VerifoneDevice(this))
//    }

    private val TAG = "TransactionProcess"

    private val isCard by lazy {
        intent.getBooleanExtra(BasePaymentActivity.PROCESSING_CARD_OR_WALLET, false)
    }



    private var currentTransactionResult: TransactionResult? = null

    private fun SharedPreferences.isSSL(): Boolean{
        return when (this.getString(getString(R.string.key_pref_port_type), "").toLowerCase()){
            "open" -> false
            else -> true
        }
    }

    private fun displayInfo(message: String, title: String? = null) {
        alert.setTitle(title)
        alert.setMessage(message)
        alert.show()
    }


    private val additionalTransactionType by lazy {
        intent.getSerializableExtra(BasePaymentActivity.ADDITIONAL_TRANSACTION_TYPE) as Host.TransactionType
    }

    private val alert by lazy {
        AlertDialog.Builder(this)
                .setTitle(null)
                .setMessage(null)
                .create()
    }

    private val terminalId by lazy {
        PreferenceManager.getDefaultSharedPreferences(this).getString(getString(R.string.key_terminal_id), "")
    }

    private val payviceUserNameAlertDialog by lazy {
        val v = LayoutInflater.from(this).inflate(R.layout.view_wallet_enter, null, false)
        AlertDialog.Builder(this).setPositiveButton("Enter", {_, _ ->  processPassword()} ).setView(v).setTitle("Enter the password for $payviceUsername").create() as AlertDialog
    }

//    private val device by lazy {
//        EmvInteractor.getInstance(VerifoneDevice(this))
  //  }

    private val hostInteractor by lazy {
        (application as MainApp).hostInteractor
    }

    private val sharedPreferences by lazy {
        PreferenceManager.getDefaultSharedPreferences(this)
    }
    private val poslibdb by lazy {
        (application as MainApp).poslibdb
    }
    private val progressDialog by lazy {
        ProgressDialog(this).apply {
            setCancelable(false)
        }
    }
    private val connectionData by lazy {
        val terminal_id = sharedPreferences.getString(getString(R.string.key_terminal_id), null)
        val ip_address = sharedPreferences.getString(getString(R.string.key_ip_address), null)
        val ip_port = Integer.parseInt(sharedPreferences.getString(getString(R.string.key_pref_port), null))
        val isSSL = sharedPreferences.isSSL()

        ConnectionData(terminal_id, ip_address, ip_port, isSSL)
    }

    private val transactionType: Host.TransactionType? by lazy {
        if (!intent.hasExtra(BasePaymentActivity.TRANSACTION_TYPE)) {
            Toast.makeText(this, "Transaction type is not specified", Toast.LENGTH_LONG).show()
        }

        intent.getSerializableExtra(BasePaymentActivity.TRANSACTION_TYPE) as Host.TransactionType
    }

    private val accountNumber by lazy {
       intent.getStringExtra(BasePaymentActivity.TRANSACTION_ACCOUNT_NUMBER)
    }

    private val bankCode by lazy {
        //todo sort this out well
        intent.getSerializableExtra(BasePaymentActivity.TRANSACTION_BANK_NAME)
    }

    private val accountType by lazy {
        intent.getSerializableExtra(BasePaymentActivity.TRANSACTION_ACCOUNT_TYPE) as AccountType
    }

    private val amount by lazy {
        intent.getLongExtra(BasePaymentActivity.TRANSACTION_AMOUNT, 0)
    }

    private val additionalAmount by lazy {
        intent.getLongExtra(BasePaymentActivity.TRANSACTION_ADDITIONAL_AMOUNT, 0)
    }

    private val inputData by lazy {
        InputData(amount, additionalAmount, accountType)
    }

    private val keyHolder by lazy {
        poslibdb.keyHolderDao.get()
    }

    private val configData by lazy {
        poslibdb.configDataDao.get()
    }

        private val payviceUsername by lazy {
        PreferenceManager.getDefaultSharedPreferences(this).getString(getString(R.string.key_payvice_username), "")
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transaction_process)
        //Printer.service(this);
        val emvCardReader : EmvCardReader
        transactionType?.let {

            when (it) {
                Host.TransactionType.PURCHASE -> {
                    if (intent.getSerializableExtra(BasePaymentActivity.ADDITIONAL_TRANSACTION_TYPE) == Host.TransactionType.FUND_TRANSFER){
                       performTransfer()
                    }
                }
                else -> {}
            }
        } ?: kotlin.run {
//            finishButton.setOnClickListener {
////                startActivity(Intent(this, MainActivity::class.java))
////                finish()
//            }
        }

//        device.observeStatus()
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribeOn(Schedulers.io())
//                .subscribe({status ->
//                    when (status.state) {
//                        DeviceState.INSERT_CARD -> {
//                            handleInsertCard()
//                        }
//
//                        DeviceState.FAILED -> {
//                            handleStatusDisplay(false)
//                            transactionStatusText.text = "Transaction Canceled"
//                            print_transaction.visibility = View.GONE
//
//                            currentTransactionResult?.let {
//                                //If device fails transaction offline after the transaction had been sent to the remote host
//                                //then the transaction needs to be rolled back.
//                                handleRollBack(it)
//                            }
//                        }
//
//                        DeviceState.DECLINED -> {
//                            handleStatusDisplay(false)
//                            currentTransactionResult?.let {
//                                if(it.isApproved){
//                                    //If device declines an host-approved transaction,
//                                    // then the transaction needs a roll back
//                                    handleRollBack(it)
//                                }else{
//                                    it.let(this::handlePrinting)
//                                }
//                            }
//                        }
//
//                        DeviceState.APPROVED -> {
//                            handleStatusDisplay(true)
//                            if (intent.hasExtra(BasePaymentActivity.ADDITIONAL_TRANSACTION_TYPE) && intent.getSerializableExtra(BasePaymentActivity.ADDITIONAL_TRANSACTION_TYPE) == Host.TransactionType.FUND_TRANSFER){
//                                //todo handle the push to the new APIs
//                            }
//                            currentTransactionResult?.let(this::handlePrinting)
//                        }
//
//                        DeviceState.PROCESSING, DeviceState.AWAITING_ONLINE_RESPONSE -> {
//                            alert.dismiss()
//                            progressDialog.setMessage(status.message)
//                            progressDialog.show()
//                        }
//
//                        else -> {
//                            alert.dismiss()
//                            displayInfo(status.message)
//                            progressDialog.dismiss()
//                        }
//                    }
//
//                },{onError -> Log.d("Error", onError.message)} )

    }

//    private fun performBillPayment() {
//        performPurchase()
//    }

    private fun showVisibility(view: View) {
        val layout_ids = intArrayOf(R.id.enter_amount)

        if (view.visibility == View.VISIBLE) {
            return
        }

        for (ids in layout_ids) {
            if ((findViewById<View>(ids)) != null && ids != view.id)
                findViewById<View>(ids).visibility = View.GONE
        }

        view.visibility = View.VISIBLE
    }

    private fun initializeApproveDeclinedState() {
        progressDialog.dismiss()
        alert.dismiss()
        doAsync {
            poslibdb.transactionResultDao.save(currentTransactionResult)
        }

    }

    private fun handleInsertCard(){
        showVisibility(findViewById(R.id.insert_card))

        if(transactionType != Host.TransactionType.BALANCE_INQUIRY){
            transactionAmountText.text = "${parseLongIntoNairaKoboString(amount)}"
        }else {
            transactionAmountText.visibility = View.GONE
        }

        if (intent.hasExtra(BasePaymentActivity.ADDITIONAL_TRANSACTION_TYPE) && intent.getSerializableExtra(BasePaymentActivity.ADDITIONAL_TRANSACTION_TYPE)== Host.TransactionType.FUND_TRANSFER){
            transactionTypeText.text = "Funds Transfer"
        } else {
            transactionTypeText.text =  transactionType?.name?.replace("_"," ")
        }
        alert.dismiss()
    }

    private fun handleStatusDisplay(isApproved: Boolean){
        initializeApproveDeclinedState()
        showVisibility(findViewById(R.id.transaction_status_layout))

        if(isApproved){
            transactionStatusText.text = getString(R.string.state_transaction_approved)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                transactionStatusImage.setImageDrawable(getDrawable(R.drawable.transaction_approved))
            }
        }else{
            transactionStatusText.text = getString(R.string.state_transaction_declined)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                transactionStatusImage.setImageDrawable(getDrawable(R.drawable.transaction_declined))
            }
        }

//        finishButton.setOnClickListener {
//            finish()
//        }
    }

    fun performTransfer(){
        showVisibility(findViewById(R.id.insert_card))

        if(transactionType != Host.TransactionType.BALANCE_INQUIRY){
            transactionAmountText.text = "${parseLongIntoNairaKoboString(amount)}"
        } else {
            transactionAmountText.visibility = View.GONE
        }

        //todo show dialogBox asking user to enter password

        if (isCard)
            handleCardTransfer()
        else
            handleWalletTransfer()

    }

    private fun lookup(accountNumber : String, bank_code : String){
        val bankCode = bank_code.substring(0, 3)
        /*TransferService.create().lookUpAccountNumber(terminalID = terminalId, toAccount = accountNumber, bankCode = bankCode).enqueue(this)*/
    }

    private fun handleCardTransfer() = runBlocking{
      //  performPurchase()
    }

    fun processPassword(){
       // val password = payviceUserNameAlertDialog.et_payvice_password.text.toString()

        //todo show processing and show the user's details for client to confirm.
        //todo if user confirms, debit user's wallets and then debit our position with GT and credit client
        if (terminalId.isNullOrEmpty())
        {
            return
        }

        lookup(accountNumber = accountNumber, bank_code = accountNumber)
    }

    private fun handleWalletTransfer() {
        progressDialog.setMessage("Processing wallet transfer")
        progressDialog.show()
    }

//    private fun handlePrinting(result: TransactionResult){
//        tranStatusReasonText.text = result.transactionStatusReason
//        PrintUtils.generatePrintableForCustomer(result, printerInteractor, this@TransactionProcessActivity)
//
//        alert {
//            title = "Print Merchant Copy?"
//            isCancelable = false
//            message = "Press OK to print merchant's copy"
//            okButton {  PrintUtils.generatePrintableForMerchant(result, printerInteractor, this@TransactionProcessActivity) }
//            cancelButton {  }
//        }.show()
//    }

    private fun handleRollBack(lastTransactionResult: TransactionResult){

        when(transactionType){
            null,
            Host.TransactionType.BALANCE_INQUIRY,
            Host.TransactionType.REVERSAL,
            Host.TransactionType.REFUND -> return
            else -> ""
        };
        progressDialog.setMessage("Rolling back transaction")
        progressDialog.show()

        Log.d(TAG,"Rolling back")
        hostInteractor.rollBackTransaction().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { transactionResult , throwable ->
                    transactionResult?.let {
                        Log.d(TAG, "Offline Failed transaction roll back status:  " + it.transactionStatus)
                        Log.d(TAG, it.toString())

                        progressDialog.dismiss()

                        if(lastTransactionResult.isApproved && !it.isRolledBack){
                            alert {
                                title = "Rollback Failed"
                                message = "Could not roll back transaction"
                                isCancelable = false
                                positiveButton("Retry"){
                                    handleRollBack(lastTransactionResult)
                                }
                                cancelButton {  }
                            }.show()
                        }else{
                            doAsync {
                                poslibdb.transactionResultDao.save(it)
                            }
                         //   handlePrinting(it)
                        }
                    }
                }

    }}







