package com.iisysgroup.payvice.startimes.interactor

import android.content.Context
import com.iisysgroup.poslib.commons.emv.EmvCard
import com.itex.richard.payviceconnect.model.DstvModel
import com.itex.richard.payviceconnect.model.StartimesModel
import com.itex.richard.payviceconnect.wrapper.PayviceServices
import com.wizarpos.emvsample.activity.FuncActivity
import com.wizarpos.emvsample.activity.FuncActivity.appState
import com.wizarpos.emvsample.activity.login.Helper
import com.wizarpos.emvsample.activity.login.securestorage.SecureStorage
import com.wizarpos.emvsample.generators.PfmStateGenerator
import com.wizarpos.emvsample.models.PfmJournalGenerator
import com.wizarpos.util.StringUtil
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.apache.commons.codec.binary.StringUtils

/**
 * Created by Olije Favour on 6/24/2019.
 *Copyright (c) 2019  Itex Integrated Services  All rights reserved.
 */


class StartimesInteractorImpl(val context: Context) : StartimesInteractor {

    var payviceServices = PayviceServices.getInstance(context)
    override fun subscribe(authPin: String, password: String, customerName: String, phone: String, productCode: String, bouquet: String, paymentMethod: String, smartCardCode: String, amount: Int): Single<StartimesModel.payResponse> {
        FuncActivity.appState.startimes=true

        val clientReference = StringUtil.getClientRef(context, "")
        val pinInfo = EmvCard.PinInfo(FuncActivity.appState.trans.pinBlock, null, null)

        val emvCard = EmvCard(FuncActivity.appState.trans.cardHolderName, FuncActivity.appState.trans.track2Data, FuncActivity.appState.trans.iccData, pinInfo)

        val tid = SecureStorage.retrieve(Helper.TERMINAL_ENTERED_BY_USER, "")



        val pfm = com.itex.richard.payviceconnect.model.Pfm(PfmStateGenerator(context,tid).generateState(), PfmJournalGenerator(FuncActivity.appState.trans.transactionResult, FuncActivity.appState.nibssData.configData, false, amount.toString(), emvCard, "Startimes", bouquet, "").generateJournal())


        val subscriptionDetails = StartimesModel.payRequest(amount = amount,wallet = terminalId,username = userId,password =password,type = type,channel =channel,smartCardCode =smartCardCode,customerName = customerName,phone = phone,productCode = productCode,bouquet = bouquet,paymentMethod = paymentMethod,pin = authPin,clientReference = clientReference,pfm = pfm )

        return Single.fromObservable(payviceServices.StartimesPayment(subscriptionDetails))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    override fun validateNumber(smartCardCode: String): Single<StartimesModel.lookupResponse> {
        val lookupDetails = StartimesModel.lookupRequest(channel = channel,wallet = terminalId,username = userId,type = type,smartCardCode = smartCardCode)

        return Single.fromObservable(payviceServices.StartimesLookup(lookupDetails))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    private val terminalId by lazy {
        SecureStorage.retrieve(Helper.TERMINAL_ID, "")
    }


    private val type:String ="default"

    private val channel:String = "ANDROIDPOS"

    private val userId by lazy {
        SecureStorage.retrieve(Helper.USER_ID, "")
    }



}