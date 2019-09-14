package com.iisysgroup.payvice.baseimpl.interactor

import android.content.Context
import com.iisysgroup.payvice.base.interactor.MultichoiceInteractor
import com.iisysgroup.poslib.commons.emv.EmvCard
import com.itex.richard.payviceconnect.model.DstvModel
import com.itex.richard.payviceconnect.wrapper.PayviceServices
import com.wizarpos.emvsample.activity.FuncActivity
import com.wizarpos.emvsample.activity.login.Helper
import com.wizarpos.emvsample.activity.login.securestorage.SecureStorage
import com.wizarpos.emvsample.generators.PfmStateGenerator
import com.wizarpos.emvsample.models.PfmJournalGenerator
import com.wizarpos.util.StringUtil
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class MultichoiceInteractorImpl(val context: Context) : MultichoiceInteractor {

    private val terminalId by lazy {
        SecureStorage.retrieve(Helper.TERMINAL_ID, "")
    }

    private val userId by lazy {
        SecureStorage.retrieve(Helper.USER_ID, "")
    }
    var payviceServices = PayviceServices.getInstance(context)

    val clientReference = StringUtil.getClientRef(context, "")

    override fun validateIucAndGetPlans(iuc: String, product: MultichoiceInteractor.MultichoiceProduct): Single<DstvModel.DstvResponse> {
        val lookupDetails = DstvModel.DstvLookup(product.name, iuc)

        return Single.fromObservable(payviceServices.DstvLookup(lookupDetails))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    override fun subscribe(iuc: String, plan: DstvModel.Data,
                           product: MultichoiceInteractor.MultichoiceProduct, authPin: String): Single<DstvModel.PayResponse> {
        val pinInfo = EmvCard.PinInfo(FuncActivity.appState.trans.pinBlock, null, null)

        val emvCard = EmvCard(FuncActivity.appState.trans.cardHolderName, FuncActivity.appState.trans.track2Data, FuncActivity.appState.trans.iccData, pinInfo)

        val tid = SecureStorage.retrieve(Helper.TERMINAL_ENTERED_BY_USER, "")


        val pfm = com.itex.richard.payviceconnect.model.Pfm(PfmStateGenerator(context,tid).generateState(), PfmJournalGenerator(FuncActivity.appState.trans.transactionResult, FuncActivity.appState.nibssData.configData, false,plan.amount, emvCard, product.name, plan.name, "").generateJournal())


        val subscriptionDetails = DstvModel.PayDetails(iuc, plan.product_code,
                userId, terminalId, null,  authPin, product.toString(), clientReference,pfm)

        return Single.fromObservable(payviceServices.DstvSubscribe(subscriptionDetails))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }
}