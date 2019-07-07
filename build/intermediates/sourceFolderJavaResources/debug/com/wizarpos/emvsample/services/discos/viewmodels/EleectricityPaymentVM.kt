package com.wizarpos.emvsample.services.discos.viewmodels

import android.app.Activity
import android.app.Application
import android.app.ProgressDialog
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import android.widget.Toast
import com.google.gson.Gson
import com.itex.richard.payviceconnect.model.*
import com.itex.richard.payviceconnect.wrapper.PayviceServices
import com.wizarpos.util.Service
import com.wizarpos.util.VasServices
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import org.jetbrains.anko.indeterminateProgressDialog

/**
 * Created by Olije Favour on 7/1/2019.
 *Copyright (c) 2019  Itex Integrated Services  All rights reserved.
 */


class EleectricityPaymentVM(application: Application): AndroidViewModel(application),Observer<Any> {



        var isValidation: Boolean=true
        var payviceServices = PayviceServices.getInstance(application.baseContext)

        var mProduct : Service.Product?=null

    fun  loginProgressDialog(message:String, showDialogue: Boolean, activity: Activity): ProgressDialog {
        return activity.indeterminateProgressDialog(message, "Wari - Pos")
    }




//  private  val mAbujaLookupResp= MutableLiveData<AbujaModel.LookUpResponse>()
//  val lListOfBankResponse : LiveData<AbujaModel.LookUpResponse>
//    get() = mAbujaLookupResp

        lateinit  var dialog : ProgressDialog
        private  val mError = MutableLiveData<String>()
        val lError : LiveData<String>
            get() = mError



        private  val mPaymentRes= MutableLiveData<Any>()
        val lPaymentRes : LiveData<Any>
            get() = mPaymentRes



        override fun onComplete() {
        }

        override fun onSubscribe(d: Disposable) {
        }

        override fun onNext(t: Any) {
            dialog.dismiss()
            Log.d("result", Gson().toJson(t))

            Log.d("isValidation >>", isValidation.toString())

//            if(isValidation){
//                Log.d("mLookupRes >>", "here")
//
//
//            }else{
                Log.d("mPaymentRes >>", "here")
                mPaymentRes.value=t
//            }
        }

        override fun onError(e: Throwable) {
            dialog.dismiss()


            Log.d("Error >>>",e.message.toString())
            mError.value = e.message.toString()

            Log.d("e.message", Gson().toJson(e))

        }



        fun payElectricBill(activity: Activity, amount: String, wallet: String, userName: String, requestType: String, meterType: String, meterNumber: String, channel: String, phone: String, productCode: String, pin: String, paymentMetod: String, electricMeterType: String, password: String, customerName: String, clientReference: String, terminalId: String,pfm:Pfm) {
            isValidation= false

            Log.i("electricMeterType  >>>",electricMeterType)

            dialog =  loginProgressDialog("Making Payments ....",true,activity)
            dialog.setCanceledOnTouchOutside(false)
            dialog.show()



            var details:Any?=null

            isValidation=false

            val type ="getcus"
            val service_type = "vend"

            Log.i("electricMeterType  >>>",electricMeterType)
            when (electricMeterType){


                VasServices.ABUJA_ELECTRICITY_POSTPAID, VasServices.ABUJA_ELECTRICITY_PREPAID ->{
                    Log.i("electricMeterType >>>","electricMeterType >>>>  ${ electricMeterType} Abuja ")
                    details= AbujaModel.PurchaseDetails(amount = (amount.toInt() * 100).toString(),wallet = wallet,username = userName,requestType = requestType,meterType = meterType,meterNo = meterNumber,channel = channel,phone = phone,productCode = productCode,pin = pin,paymentMethod = paymentMetod,pfm = pfm)
                    payviceServices.abujaPayBill(details)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread() )
                            .subscribe(this)
                }

                VasServices.ENUGU_ELECTRICITY_POSTPAID, VasServices.ENUGU_ELECTRICITY_PREPAID ->{
                    details= EnuguModel.PayRequest(amount = (amount.toInt() * 100).toString(),wallet = wallet,username = userName,channel = channel,phone = phone,productCode = productCode,pin = pin,paymentMethod = paymentMetod,password =password ,type = meterType,account =meterNumber ,customerName =customerName,clientReference = clientReference,pfm = pfm)

                    payviceServices.EnuguElectricPayment(details)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread() )
                            .subscribe(this)

                }

                VasServices.EKO_ELECTRICITY_POSTPAID, VasServices.EKO_ELECTRICITY_PREPAID ->{
                    details= EkoModel.EkoPayDetails(amount = (amount.toInt()).toString(),channel = channel,phone = phone,pin = pin,password =password ,type = meterType,clientReference = clientReference,meter = meterNumber,accountType = meterType,terminal_id = wallet,user_id = userName,pfm = pfm)

                    payviceServices.EkoPayBill(details)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread() )
                            .subscribe(this)

                }

                VasServices.IBADAN_ELECTRICITY_POSTPAID, VasServices.IBADAN_ELECTRICITY_PREPAID ->{
                    details= IbadanModel.IbPayRequest(amount = (amount.toInt() * 100).toString(),wallet = wallet,username = userName,channel = channel,phone = phone,productCode = productCode,pin = pin,paymentMethod = paymentMetod,password =password ,type = meterType,account =meterNumber ,customerName =customerName,clientReference = clientReference,pfm = pfm)


                    payviceServices.ibadanPayBill(details)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread() )
                            .subscribe(this)
                }

                VasServices.IKEJA_ELECTRICITY_POSTPAID ->{
                    details= IkejaModel.IkejaPayDetailsPostPaid(amount = (amount.toInt()).toString(),terminal_id = wallet,terminal = wallet,channel = channel,phone = phone,pin = pin,password =password ,type =type ,clientReference = clientReference,service_type =service_type ,service =meterType,user_id = userName,account = meterNumber,pfm = pfm)
                    payviceServices.IkejaPayBillPostPaid(details)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread() )
                            .subscribe(this)
                }

                VasServices.IKEJA_ELECTRICITY_PREPAID ->{
                    details= IkejaModel.IkejaPayDetailsPrepaid(amount = (amount.toInt()).toString(),terminal_id = wallet,terminal = terminalId,channel = channel,phone = phone,pin = pin,password =password ,type =type ,clientReference = clientReference,service_type =service_type ,service =meterType,user_id = userName,meter = meterNumber,pfm = pfm)
                    payviceServices.IkejaPayBillPrepaid(details)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread() )
                            .subscribe(this)

                }

                VasServices.PORTHARCOURT_ELECTRICITY_POSTPAID, VasServices.PORTHARCOURT_ELECTRICITY_PREPAID ->{
                    details= PortharcourtModel.purchaseDetails(amount = (amount.toInt() * 100).toString(),wallet = wallet,username = userName,channel = channel,phone = phone,productCode = productCode,pin = pin,paymentMethod = paymentMetod,password =password ,type = meterType,account =meterNumber ,customerName =customerName,clientReference = clientReference,terminalId = terminalId,pfm = pfm)
                    payviceServices.portharcourtPayBill(details)

                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread() )
                            .subscribe(this)


                }

                else ->return
            }














//         val abujaDetails =AbujaModel.PurchaseDetails(amount = amount,wallet = wallet,username = userName,requestType = requestType,meterType = meterType,meterNo = meterNumber,channel = channel,phone = phone,productCode = productCode,pin = pin,paymentMethod = paymentMetod)
//
//         payviceServices.abujaPayBill(abujaDetails)
//                 .subscribeOn(Schedulers.io())
//                 .observeOn(AndroidSchedulers.mainThread() )
//                 .subscribe(object : Observer<AbujaModel.PurchaseResponse> {
//                     override fun onComplete() {
//                     }
//
//                     override fun onSubscribe(d: Disposable) {
//                     }
//
//                     override fun onNext(t: AbujaModel.PurchaseResponse) {
//                         dialog.dismiss()
//                         Log.d("result", Gson().toJson(t))
//                         mAbujapaymentRes.value =t
//                     }
//
//                     override fun onError(e: Throwable) {
//                         dialog.dismiss()
//
//
//                         Log.d("Error >>>",e.message.toString())
//                         mError.value = e.message.toString()
//
//                         Log.d("e.message", Gson().toJson(e))
//
//                     }
//
//                 })
        }




}