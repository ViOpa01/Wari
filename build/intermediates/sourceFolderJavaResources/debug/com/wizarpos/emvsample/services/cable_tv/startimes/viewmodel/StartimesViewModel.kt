package com.iisysgroup.payvice.startimes.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.util.Log
import com.cloudpos.card.Card
import com.google.gson.Gson

import com.iisysgroup.payvice.startimes.interactor.StartimesInteractor
import com.iisysgroup.payvice.startimes.interactor.StartimesInteractorImpl
import com.iisysgroup.payvice.startimes.presenter.StartimesPresenter

import com.itex.richard.payviceconnect.model.DstvModel
import com.itex.richard.payviceconnect.model.StartimesModel
import com.wizarpos.util.Service
import com.wizarpos.util.VasServices

/**
 * Created by Olije Favour on 6/24/2019.
 *Copyright (c) 2019  Itex Integrated Services  All rights reserved.
 */


class StartimesViewModel(appContext: Application):AndroidViewModel(appContext),StartimesPresenter{



    private val interactor by lazy {
        StartimesInteractorImpl(appContext)
    }

    private var lookUpResponse= MutableLiveData<StartimesModel.lookupResponse>()
    val lLookUpResponse: LiveData<StartimesModel.lookupResponse?>
        get () = lookUpResponse

    private val smartCardName = MutableLiveData<String>()
    val iucNameLiveData: LiveData<String>
        get () = smartCardName


    private val progressDialog = MutableLiveData<Pair<Boolean, String>>()
    val progressDialogLiveData: LiveData<Pair<Boolean, String>>
        get () = progressDialog

    private val paymentResponse = MutableLiveData<StartimesModel.payResponse>()
    val paymentResponseLiveData: LiveData<StartimesModel.payResponse>
        get() = paymentResponse

    private val error = MutableLiveData<Throwable>()
    val errorWatcher: LiveData<Throwable>
        get() = error


    private val product = MutableLiveData<StartimesInteractor.StartimesProduct>()
    val productLiveData: LiveData<StartimesInteractor.StartimesProduct>
        get() = product


    private val validated = MutableLiveData<Boolean>()
    val isSmartcardValidated: LiveData<Boolean>
        get() = validated






    override fun setSmartCardIsValidated(isValidated: Boolean) {
        validated.value = isValidated
    }

    override fun setProduct(product: StartimesInteractor.StartimesProduct) {
        this.product.value = product
    }


    override fun setError(throwable: Throwable) {
        error.value = throwable
    }

//    override fun setSelectedPlan(position: Int) {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//    }

    override fun validateSmartCardCode(smartCardCode: String) {

        showProgressDialog(true, "Validating card")
        interactor.validateNumber(smartCardCode).subscribe { result, error ->

            showProgressDialog(false)

            error?.let(this::setError)

            result?.let {
                if (it.error) {
                    setError(RuntimeException("Invalid smart card  number"))
                } else {
                    this.lookUpResponse.value= it
                    smartCardName.value = "${it.name}\n \n Type: ${it.bouquet ?: ""} \n"
                }
            }
        }
    }

    private val selectedPlan = MutableLiveData<StartimesModel.payResponse>()
    val selectPlanLiveData: LiveData<StartimesModel.payResponse>
        get() = selectedPlan



    override fun subscribe(authPin: String, password: String, customerName: String, phone: String, productCode: String, bouquet: String, paymentMethod: String, smartCardCode: String, amount: Int) {
        showProgressDialog(true, "Completing payment... Please wait")
        Log.i("Startimes result >>>>>", "SUBSCRIBED")

        interactor.subscribe(amount = amount,password = password,authPin = authPin,customerName = customerName,phone = phone,productCode = productCode,bouquet = bouquet,paymentMethod = paymentMethod,smartCardCode = smartCardCode).subscribe { result, error ->
            showProgressDialog(false)
            error?.let(this::setError)

            result?.let {

                Log.i("Startimes result >>>>>", Gson().toJson(it))

//                val beneficiary = Beneficiary(smartCardCode, smartCardName.value ?: "", product.value?.name
//                        ?: "")
                val amount = selectedPlan.value?.smartCardCode?.toDouble()?.toInt() ?: 0
                val plan = selectedPlan.value?.smartCardCode ?: ""
                paymentResponse.value=it
                Log.i("Startimes result >>>>>", Gson().toJson(it))
//                val data = ServiceResult.Data(beneficiary, amount)
//                paymentResponse.value = StartimesResult(data, plan, it)
            }
        }
    }


//    override fun getPlans(): LiveData<List<DstvModel.Data>> {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//    }

//    override fun subscribeWithCard(card: Card?, processor: DebitCardProcessor, authPin: String, password: String, customerName: String, phone: String, productCode: String, bouquet: String, paymentMethod: String, smartCardCode: String, amount: Int) {
////        val amount = selectedPlan.value?.amount?.toInt() ?: 0
//        processor.processTransaction(amount, card).subscribe { response ->
//            response?.let {
//                if (it.result != TamsResponse.Status.APPROVED) {
//                    setError(RuntimeException(it.message))
//                } else {
//                    subscribe(amount = amount,authPin = authPin,password = password,customerName = customerName,phone = phone,productCode = productCode,bouquet = bouquet,paymentMethod =paymentMethod ,smartCardCode = smartCardCode)
//                }
//            }
//        }
//
//    }

    override fun setService(service: Service) {

    }
    private fun showProgressDialog(show: Boolean, message: String = "") {
        progressDialog.postValue(Pair(show, message))
    }
}