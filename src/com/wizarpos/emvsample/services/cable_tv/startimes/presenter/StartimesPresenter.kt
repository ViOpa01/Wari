package com.iisysgroup.payvice.startimes.presenter

import android.arch.lifecycle.LiveData
import com.cloudpos.card.Card
import com.iisysgroup.payvice.startimes.interactor.StartimesInteractor
import com.itex.richard.payviceconnect.model.DstvModel
import com.wizarpos.util.Service

/**
 * Created by Olije Favour on 6/24/2019.
 *Copyright (c) 2019  Itex Integrated Services  All rights reserved.
 */


interface StartimesPresenter {

        fun setService(service: Service)
        fun setProduct(product: StartimesInteractor.StartimesProduct)
        fun setError(throwable: Throwable)
//        fun setSelectedPlan(position: Int)
        fun validateSmartCardCode(smartCardCode: String)
        fun subscribe(authPin: String, password: String, customerName: String, phone: String, productCode: String, bouquet: String, paymentMethod: String, smartCardCode: String, amount: Int)
//        fun setPlans(plans: List<DstvModel.Data>)
        fun setSmartCardIsValidated(isValidated: Boolean)
//        fun getPlans(): LiveData<List<DstvModel.Data>>

//        fun subscribeWithCard(card: Card?, processor: DebitCardProcessor, authPin: String, password: String, customerName: String, phone: String, productCode: String, bouquet: String, paymentMethod: String, smartCardCode: String, amount: Int)

}