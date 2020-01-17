package com.wizarpos.emvsample.payments_menu.handlers

import android.arch.lifecycle.LifecycleOwner
import android.content.Context
import android.util.Log
import com.iisysgroup.poslib.commons.TripleDES
import com.iisysgroup.poslib.commons.dukpt.StringUtil
import com.iisysgroup.poslib.commons.emv.EmvTransactionType
import com.iisysgroup.poslib.deviceinterface.interactors.EmvInteractor
import com.iisysgroup.poslib.host.Host
import com.iisysgroup.poslib.host.HostInteractor
import com.iisysgroup.poslib.host.dao.PosLibDatabase
import com.iisysgroup.poslib.host.entities.ConfigData
import com.iisysgroup.poslib.host.entities.ConnectionData
import com.iisysgroup.poslib.host.entities.KeyHolder
import com.iisysgroup.poslib.host.entities.TransactionResult
import com.iisysgroup.poslib.utils.InputData
import com.iisysgroup.poslib.utils.TransactionData
import com.wizarpos.emvsample.VasCommunicator
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.runBlocking
import org.jetbrains.anko.doAsync
import kotlin.experimental.xor

class VasPurchase(owner: LifecycleOwner, val db: PosLibDatabase, val inputData: InputData,
                  val hostInteractor: HostInteractor, var connData: ConnectionData,
                  val emvInteractor: EmvInteractor, var configData: ConfigData, var keyHolder: KeyHolder, var context : Context)  {



    init {
        emvInteractor.observeStatus().subscribe {
            Log.d("VAS_PURCHASE", it.state.toString())
        }
    }


    fun getTransactionResult(): Single<TransactionResult> {

        val cardData = emvInteractor.startEmvTransaction(inputData.amount,
                inputData.additionalAmount, EmvTransactionType.EMV_PURCHASE).subscribeOn(Schedulers.io())


        return cardData.flatMap {
            val transactionData = TransactionData(inputData, it, configData, keyHolder)
            val  varsr = VasCommunicator(context,transactionData,connData, keyHolder)
            //hostInteractor.getTransactionResult(Host.TransactionType.PURCHASE, connData, transactionData).subscribeOn(Schedulers.io())
            Single.fromCallable{
                runBlocking {
                    varsr.processOnlineTransaction()
                }
            }
        }
    }


}


