package com.wizarpos.emvsample

import android.os.Bundle
import android.util.Log
import com.iisysgroup.poslib.deviceinterface.interactors.EmvInteractor
import com.iisysgroup.poslib.host.Host
import com.iisysgroup.poslib.host.entities.ConfigData
import com.iisysgroup.poslib.host.entities.ConnectionData
import com.iisysgroup.poslib.host.entities.KeyHolder
import com.iisysgroup.poslib.utils.AccountType
import com.iisysgroup.poslib.utils.InputData
import com.wizarpos.emvsample.activity.BaseCardPaymentProcessor
import com.wizarpos.emvsample.payments_menu.BasePaymentActivity
import com.wizarpos.emvsample.payments_menu.handlers.VasPurchase
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.jetbrains.anko.Android
import org.jetbrains.anko.alert
import org.jetbrains.anko.okButton
import org.jetbrains.anko.toast

class VasPurchaseProcessor: BaseCardPaymentProcessor(){

    //Receives amount, additional amount, account type,
    //Returns value to calling library or class


    private val mAmount = 5L

    private val mAdditionalAmount = 0L

    private val mAccountType= AccountType.DEFAULT_UNSPECIFIED


    private val mDb by lazy {
        (application as MainApp).poslibdb
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Single.fromCallable {
            mDb.vasTerminalDataDao.get()
        }.subscribeOn(Schedulers.io())
                .flatMap { vasTerminalDetails ->
            val connectionData = ConnectionData(vasTerminalDetails.tid, "196.6.103.73", 5043, true)
//                    val connectionData = ConnectionData(vasTerminalDetails.tid, "197.253.19.78", 5001, true)

                    val keyHolder = KeyHolder(vasTerminalDetails.masterKey, vasTerminalDetails.sessionKey, vasTerminalDetails.pinKey)

            val configData = ConfigData()


            //Time out
            configData.storeConfigData("04002", "90")

            //Country Code
            configData.storeConfigData("06003", vasTerminalDetails.countryCode)

            //MCC
            configData.storeConfigData("08004", vasTerminalDetails.mcc)


            //Merchant's name - 40 length
            configData.storeConfigData("52040", vasTerminalDetails.merchantName)

            //Merchant Id - 15 length
            configData.storeConfigData("03015", vasTerminalDetails.mid)

            //Currency Code
            configData.storeConfigData("05003", vasTerminalDetails.currencyCode)

            val inputData = InputData(mAmount, mAdditionalAmount, mAccountType)
            val vasPurchase = VasPurchase(this, mDb, inputData, mHostInteractor, connectionData, mEmvInteractor, configData, keyHolder, this@VasPurchaseProcessor)


            vasPurchase.getTransactionResult()
        }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { result, error ->

                        result?.let {
                            Log.d("OkHTransactionResult",  it.toString())
//                            EmvInteractor.getInstance(wariDevice).processOnlineResponse(it.responseCode, it.issuerAuthData91, it.issuerScript71, it.issuerScript72)
                            setTransactionRrn(it.RRN)

                        }

                    error?.let {
                        toast(it.message!!)
                    }
                    }


    }

//    override fun finish() {
//        VerifoneDevice.removeService(this)
//        super.finish()
//    }
}
