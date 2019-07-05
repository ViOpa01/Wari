package com.wizarpos.emvsample.services.discos.viewmodels

import android.app.Activity
import android.app.Application
import android.app.ProgressDialog
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.util.Log
import com.google.gson.Gson
import com.itex.richard.payviceconnect.model.*
import com.itex.richard.payviceconnect.wrapper.PayviceServices
import com.wizarpos.util.Service
import com.wizarpos.util.VasServices.*
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import org.jetbrains.anko.indeterminateProgressDialog

/**
 * Created by Olije Favour on 6/26/2019.
 *Copyright (c) 2019  Itex Integrated Services  All rights reserved.
 */


class MeterValidationViewModel(application: Application): AndroidViewModel(application),Observer<Any>{

    fun  loginProgressDialog(message:String, showDialogue: Boolean, activity: Activity): ProgressDialog {
    return activity.indeterminateProgressDialog(message, "Wari - Pos")
  }


     var isValidation: Boolean=true
    var payviceServices = PayviceServices.getInstance(application.baseContext)

  var mProduct : Service.Product?=null



//  private  val mAbujaLookupResp= MutableLiveData<AbujaModel.LookUpResponse>()
//  val lListOfBankResponse : LiveData<AbujaModel.LookUpResponse>
//    get() = mAbujaLookupResp

    lateinit  var dialog : ProgressDialog
    private  val mError = MutableLiveData<String>()
  val lError :LiveData<String>
    get() = mError


    private  val mLookupRes= MutableLiveData<Any>()
    val lLookRes : LiveData<Any>
        get() = mLookupRes

    private  val mPaymentRes= MutableLiveData<Any>()
    val lPaymentRes : LiveData<Any>
        get() = mPaymentRes


//    private  val mAbujapaymentRes= MutableLiveData<AbujaModel.PurchaseResponse>()
//    val lpaymentRes : LiveData<AbujaModel.PurchaseResponse>
//        get() = mAbujapaymentRes
//  lateinit var mAbujaLookupRes:LiveData<AbujaModel.LookUpResponse>

  fun validateMeterNumber(activity: Activity, electricMeterType: String, channel: String, wallet: String, username: String, requestType: String, meterNo: String, meterType: String, password: String, terminalId: String){

     var details:Any?=null

     dialog =  loginProgressDialog("Validating Meter Number ....",true,activity)
    dialog.setCanceledOnTouchOutside(false)
    dialog.show()


      when (electricMeterType){


          ABUJA_ELECTRICITY_POSTPAID,ABUJA_ELECTRICITY_PREPAID ->{

              details= AbujaModel.LookupDetails(channel = channel,wallet = wallet,username = username,requestType = requestType,meterNo = meterNo,meterType = meterType)
              payviceServices.abujaLookup(details)
                      .subscribeOn(Schedulers.io())
                      .observeOn(AndroidSchedulers.mainThread() )
                      .subscribe(this)
          }

          ENUGU_ELECTRICITY_POSTPAID ,ENUGU_ELECTRICITY_PREPAID ->{
              details= EnuguModel.LookupRequest(channel = channel,wallet = wallet,username = username,type = meterType,account = meterNo)
              payviceServices.EnuguElectricValidaton(details)
                      .subscribeOn(Schedulers.io())
                      .observeOn(AndroidSchedulers.mainThread() )
                      .subscribe(this)

          }

          EKO_ELECTRICITY_POSTPAID,EKO_ELECTRICITY_PREPAID ->{
              details=EkoModel.EkoLookupDetails(meter = meterNo)
              payviceServices.EkoLookup(details)
                      .subscribeOn(Schedulers.io())
                      .observeOn(AndroidSchedulers.mainThread() )
                      .subscribe(this)

          }

          IBADAN_ELECTRICITY_POSTPAID ,IBADAN_ELECTRICITY_PREPAID ->{
              details= IbadanModel.IbLookupRequest(channel = channel,wallet = wallet,username = username,type = meterType,account = meterNo)

              payviceServices.IbadanLookUp(details)
                      .subscribeOn(Schedulers.io())
                      .observeOn(AndroidSchedulers.mainThread() )
                      .subscribe(this)
          }

          IKEJA_ELECTRICITY_POSTPAID ->{
              details= IkejaModel.IkejaLookupDetailsPostPaid(account = meterNo,channel =channel ,terminal_id = wallet,terminal =terminalId ,user_id =username ,password =password)
              payviceServices.IkejaLookupPostpaid(details)
                      .subscribeOn(Schedulers.io())
                      .observeOn(AndroidSchedulers.mainThread() )
                      .subscribe(this)
          }

          IKEJA_ELECTRICITY_PREPAID ->{
              details= IkejaModel.IkejaLookupDetailsPrepaid(meter = meterNo,channel =channel ,terminal_id = wallet,terminal =wallet ,user_id =username ,password =password)
              payviceServices.IkejaLookupPrepaid(details)
                      .subscribeOn(Schedulers.io())
                      .observeOn(AndroidSchedulers.mainThread() )
                      .subscribe(this)

          }

          PORTHARCOURT_ELECTRICITY_POSTPAID,PORTHARCOURT_ELECTRICITY_PREPAID ->{
              details= PortharcourtModel.LookupDetails(channel = channel,wallet = wallet,username = username,terminalId = wallet,type = meterType,account = meterNo)
             payviceServices.portharcourtLookup(details)

                     .subscribeOn(Schedulers.io())
                     .observeOn(AndroidSchedulers.mainThread() )
                     .subscribe(this)


          }

          else ->return
      }



  }


    override fun onComplete() {
    }

    override fun onSubscribe(d: Disposable) {
    }

    override fun onNext(t: Any) {
        dialog.dismiss()
        Log.d("result", Gson().toJson(t))

        Log.d("isValidation >>", isValidation.toString())

        if(isValidation){
            Log.d("mLookupRes >>", "here")

            mLookupRes.value =t
        }else{
            Log.d("mPaymentRes >>", "here")
            mPaymentRes.value=t
        }
    }

    override fun onError(e: Throwable) {
        dialog.dismiss()


        Log.d("Error >>>",e.message.toString())
        mError.value = e.message.toString()

        Log.d("e.message", Gson().toJson(e))

    }



//    fun payElectricBill(activity: Activity, amount: String, wallet: String, userName: String, requestType: String, meterType: String, meterNumber: String, channel: String, phone: String, productCode: String, pin: String, paymentMetod: String, electricMeterType: String, password: String, customerName: String, clientReference: String, terminalId: String) {
//        isValidation= false
//
//        dialog =  loginProgressDialog("Making Payments ....",true,activity)
//         dialog.setCanceledOnTouchOutside(false)
//         dialog.show()
//
//
//
//        var details:Any?=null
//
//        isValidation=false
//
//        val type ="getcus"
//        val service_type = "vend"
//
//
//        when (electricMeterType){
//
//
//            ABUJA_ELECTRICITY_POSTPAID,ABUJA_ELECTRICITY_PREPAID ->{
//
//                details= AbujaModel.PurchaseDetails(amount = amount,wallet = wallet,username = userName,requestType = requestType,meterType = meterType,meterNo = meterNumber,channel = channel,phone = phone,productCode = productCode,pin = pin,paymentMethod = paymentMetod)
//                payviceServices.abujaPayBill(details)
//                        .subscribeOn(Schedulers.io())
//                        .observeOn(AndroidSchedulers.mainThread() )
//                        .subscribe(this)
//            }
//
//            ENUGU_ELECTRICITY_POSTPAID ,ENUGU_ELECTRICITY_PREPAID ->{
//                details= EnuguModel.PayRequest(amount = amount,wallet = wallet,username = userName,channel = channel,phone = phone,productCode = productCode,pin = pin,paymentMethod = paymentMetod,password =password ,type = meterType,account =meterNumber ,customerName =customerName,clientReference = clientReference)
//
//                payviceServices.EnuguElectricPayment(details)
//                        .subscribeOn(Schedulers.io())
//                        .observeOn(AndroidSchedulers.mainThread() )
//                        .subscribe(this)
//
//            }
//
//            EKO_ELECTRICITY_POSTPAID,EKO_ELECTRICITY_PREPAID ->{
//                details=EkoModel.EkoPayDetails(amount = (amount.toInt() * 100).toString(),channel = channel,phone = phone,pin = pin,password =password ,type = meterType,clientReference = clientReference,meter = meterNumber,accountType = meterType,terminal_id = wallet,user_id = userName)
//
//                payviceServices.EkoPayBill(details)
//                        .subscribeOn(Schedulers.io())
//                        .observeOn(AndroidSchedulers.mainThread() )
//                        .subscribe(this)
//
//            }
//
//            IBADAN_ELECTRICITY_POSTPAID ,IBADAN_ELECTRICITY_PREPAID ->{
//                details= IbadanModel.IbPayRequest(amount = amount,wallet = wallet,username = userName,channel = channel,phone = phone,productCode = productCode,pin = pin,paymentMethod = paymentMetod,password =password ,type = meterType,account =meterNumber ,customerName =customerName,clientReference = clientReference)
//
//
//                payviceServices.ibadanPayBill(details)
//                        .subscribeOn(Schedulers.io())
//                        .observeOn(AndroidSchedulers.mainThread() )
//                        .subscribe(this)
//            }
//
//            IKEJA_ELECTRICITY_POSTPAID ->{
//                details= IkejaModel.IkejaPayDetailsPostPaid(amount = (amount.toInt() * 100).toString(),terminal_id = wallet,terminal = wallet,channel = channel,phone = phone,pin = pin,password =password ,type =type ,clientReference = clientReference,service_type =service_type ,service =meterType,user_id = userName,account = meterNumber)
//                payviceServices.IkejaPayBillPostPaid(details)
//                        .subscribeOn(Schedulers.io())
//                        .observeOn(AndroidSchedulers.mainThread() )
//                        .subscribe(this)
//            }
//
//            IKEJA_ELECTRICITY_PREPAID ->{
//                details= IkejaModel.IkejaPayDetailsPrepaid(amount = (amount.toInt() * 100).toString(),terminal_id = wallet,terminal = terminalId,channel = channel,phone = phone,pin = pin,password =password ,type =type ,clientReference = clientReference,service_type =service_type ,service =meterType,user_id = userName,meter = meterNumber)
//                payviceServices.IkejaPayBillPrepaid(details)
//                        .subscribeOn(Schedulers.io())
//                        .observeOn(AndroidSchedulers.mainThread() )
//                        .subscribe(this)
//
//            }
//
//            PORTHARCOURT_ELECTRICITY_POSTPAID,PORTHARCOURT_ELECTRICITY_PREPAID ->{
//                details= PortharcourtModel.purchaseDetails(amount = amount,wallet = wallet,username = userName,channel = channel,phone = phone,productCode = productCode,pin = pin,paymentMethod = paymentMetod,password =password ,type = meterType,account =meterNumber ,customerName =customerName,clientReference = clientReference,terminalId = terminalId)
//                payviceServices.portharcourtPayBill(details)
//
//                        .subscribeOn(Schedulers.io())
//                        .observeOn(AndroidSchedulers.mainThread() )
//                        .subscribe(this)
//
//
//            }
//
//            else ->return
//        }
//
//
//
//
//
//
//
//
//
//
//
//
//
//
////         val abujaDetails =AbujaModel.PurchaseDetails(amount = amount,wallet = wallet,username = userName,requestType = requestType,meterType = meterType,meterNo = meterNumber,channel = channel,phone = phone,productCode = productCode,pin = pin,paymentMethod = paymentMetod)
////
////         payviceServices.abujaPayBill(abujaDetails)
////                 .subscribeOn(Schedulers.io())
////                 .observeOn(AndroidSchedulers.mainThread() )
////                 .subscribe(object : Observer<AbujaModel.PurchaseResponse> {
////                     override fun onComplete() {
////                     }
////
////                     override fun onSubscribe(d: Disposable) {
////                     }
////
////                     override fun onNext(t: AbujaModel.PurchaseResponse) {
////                         dialog.dismiss()
////                         Log.d("result", Gson().toJson(t))
////                         mAbujapaymentRes.value =t
////                     }
////
////                     override fun onError(e: Throwable) {
////                         dialog.dismiss()
////
////
////                         Log.d("Error >>>",e.message.toString())
////                         mError.value = e.message.toString()
////
////                         Log.d("e.message", Gson().toJson(e))
////
////                     }
////
////                 })
//     }



}