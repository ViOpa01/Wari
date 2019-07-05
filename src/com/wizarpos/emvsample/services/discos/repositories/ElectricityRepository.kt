package com.wizarpos.emvsample.services.discos.repositories

import android.app.Activity
import android.app.ProgressDialog
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.itex.richard.payviceconnect.model.AbujaModel
import com.itex.richard.payviceconnect.wrapper.PayviceServices
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import org.jetbrains.anko.indeterminateProgressDialog

/**
 * Created by Olije Favour on 6/26/2019.
 *Copyright (c) 2019  Itex Integrated Services  All rights reserved.
 */


class ElectricityRepository(val payviceServices:PayviceServices) {

//    var dialog : ProgressDialog =  loginProgressDialog("Validating Meter Number....",true,activity)
//
//    dialog.setCanceledOnTouchOutside(false)
//    dialog.show()

    fun getAbujaValidationResult(channel: String, wallet: String, username: String, requestType: String, meterNo: String, meterType: String): MutableLiveData<AbujaModel.LookUpResponse> {
        val abujaDetails =AbujaModel.LookupDetails(channel = channel,wallet = wallet,username = username,requestType = requestType,meterNo = meterNo,meterType = meterType)
        val lAbujaLookupRes = MutableLiveData<AbujaModel.LookUpResponse>()
        payviceServices.abujaLookup(abujaDetails)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread() )
                .subscribe(object :Observer<AbujaModel.LookUpResponse>{
                    override fun onComplete() {
                    }

                    override fun onSubscribe(d: Disposable) {
                    }

                    override fun onNext(t: AbujaModel.LookUpResponse) {
                        Log.d("result", Gson().toJson(t))
                        lAbujaLookupRes.value =t
                    }

                    override fun onError(e: Throwable) {
                        Log.d("e.message", Gson().toJson(e))

                    }

                })

        return lAbujaLookupRes
    }

}