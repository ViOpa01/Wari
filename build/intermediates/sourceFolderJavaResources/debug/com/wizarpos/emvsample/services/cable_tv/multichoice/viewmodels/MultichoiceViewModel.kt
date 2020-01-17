package com.iisysgroup.payvice.baseimpl.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.iisysgroup.payvice.base.interactor.MultichoiceInteractor
import com.iisysgroup.payvice.base.presenter.MultichoicePresenter
import com.iisysgroup.payvice.baseimpl.interactor.MultichoiceInteractorImpl
import com.itex.richard.payviceconnect.model.DstvModel
import com.itex.richard.payviceconnect.model.StartimesModel
import com.wizarpos.emvsample.R
import com.wizarpos.emvsample.activity.FuncActivity
import com.wizarpos.emvsample.activity.FuncActivity.appState
import com.wizarpos.util.Service
import com.wizarpos.util.VasServices

class MultichoiceViewModel(appContext: Application) : AndroidViewModel(appContext), MultichoicePresenter {

    private var tempList: List<DstvModel.Data>? = null

    private val interactor by lazy {
        MultichoiceInteractorImpl(appContext)
    }

    private val product = MutableLiveData<MultichoiceInteractor.MultichoiceProduct>()
    val productLiveData: LiveData<MultichoiceInteractor.MultichoiceProduct>
        get() = product


    private val error = MutableLiveData<Throwable>()
    val errorWatcher: LiveData<Throwable>
        get() = error

    private val paymentResponse = MutableLiveData<DstvModel.PayResponse>()
    val paymentResponseLiveData: LiveData<DstvModel.PayResponse>
        get() = paymentResponse


    private val plans = MutableLiveData<List<DstvModel.Data>>()


    private val selectedPlan = MutableLiveData<DstvModel.Data>()
    val selectPlanLiveData: LiveData<DstvModel.Data>
        get() = selectedPlan

    private val smartCardName = MutableLiveData<String>()
    val iucNameLiveData: LiveData<String>
        get () = smartCardName


    private val mValidationResponse = MutableLiveData<DstvModel.DstvResponse>()
    val lValidationResponse: LiveData<DstvModel.DstvResponse>
        get () = mValidationResponse


    private val progressDialog = MutableLiveData<Pair<Boolean, String>>()
    val progressDialogLiveData: LiveData<Pair<Boolean, String>>
        get () = progressDialog

    private val validated = MutableLiveData<Boolean>()
    val isIucValidated: LiveData<Boolean>
        get() = validated


    override fun setService(service: Service) {
        when (service.name) {

            VasServices.DSTV -> {
               appState.dstv =true
                appState.logo= service.icon
                setProduct(MultichoiceInteractor.MultichoiceProduct.DSTV)
            }
            VasServices.GOTV -> {

                appState.gotv=true
                appState.logo= service.icon
                setProduct(MultichoiceInteractor.MultichoiceProduct.GOTV)
            }
        }

        validated.value = false
    }

    override fun setProduct(product: MultichoiceInteractor.MultichoiceProduct) {
        this.product.value = product
    }

    override fun setSelectedPlan(position: Int) {
        selectedPlan.value = plans.value?.get(position)
    }

    override fun validateIuc(iuc: String) {
        showProgressDialog(true, "Validating card")
        interactor.validateIucAndGetPlans(iuc, product.value!!).subscribe { result, error ->

            showProgressDialog(false)

            error?.let(this::setError)

            result?.let {
                if (it.error) {
                    setError(RuntimeException("Invalid device number"))
                } else {
                    mValidationResponse.value= it
                    this.tempList = it.data
                    smartCardName.value = "${it.fullname}\nType: ${it.unit ?: ""}"
                }
            }
        }
    }

    override fun subscribe(iuc: String, authPin: String) {
        showProgressDialog(true, "Completing payment... Please wait")
        interactor.subscribe(iuc, selectedPlan.value!!, product.value!!, authPin).subscribe { result, error ->
            showProgressDialog(false)
            error?.let(this::setError)

            result?.let {
//                val beneficiary = Beneficiary(iuc, smartCardName.value ?: "", product.value?.name
//                        ?: "")
                val amount = selectedPlan.value?.amount?.toDouble()?.toInt() ?: 0
                val plan = selectedPlan.value?.name ?: ""
//                val data = ServiceResult.Data(beneficiary, amount)
                paymentResponse.value = it!!
            }
        }
    }

//    override fun subscribeWithCard(iucNumber: String, card: Card?, processor: DebitCardProcessor) {
//        val amount = selectedPlan.value?.amount?.toInt() ?: 0
//        processor.processTransaction(amount, card).subscribe { response ->
//            response?.let {
//                if (it.result != TamsResponse.Status.APPROVED) {
//                    setError(RuntimeException(it.message))
//                } else {
//                    subscribe(iucNumber, processor.userPin)
//                }
//            }
//        }
//    }

    override fun setPlans(plans: List<DstvModel.Data>) {
        this.plans.value = plans
    }


    override fun setSmartCardIsValidated(isValidated: Boolean) {
        validated.value = isValidated

        if (isValidated) {
            tempList?.let(this::setPlans)
        } else {
            tempList = null
        }
    }

    override fun setError(throwable: Throwable) {
        error.value = throwable
    }

    private fun showProgressDialog(show: Boolean, message: String = "") {
        progressDialog.postValue(Pair(show, message))
    }

    override fun getPlans(): LiveData<List<DstvModel.Data>> = plans
}