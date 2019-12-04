package com.iisysgroup.payvice.base.presenter

import android.arch.lifecycle.LiveData
import com.iisysgroup.payvice.base.interactor.MultichoiceInteractor
import com.itex.richard.payviceconnect.model.DstvModel
import com.wizarpos.util.Service


interface MultichoicePresenter {
    fun setService(service: Service)
    fun setProduct(product: MultichoiceInteractor.MultichoiceProduct)
    fun setError(throwable: Throwable)
    fun setSelectedPlan(position: Int)
    fun validateIuc(iuc: String)
    fun subscribe(iuc: String, authPin: String)
    fun setPlans(plans: List<DstvModel.Data>)
    fun setSmartCardIsValidated(isValidated: Boolean)
    fun getPlans(): LiveData<List<DstvModel.Data>>

//    fun subscribeWithCard(iucNumber: String, card: Card?, processor: DebitCardProcessor)
}