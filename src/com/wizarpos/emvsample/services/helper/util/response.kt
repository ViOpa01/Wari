//package com.wizarpos.emvsample.services.helper.activity.util
//
//import android.app.Dialog
//import android.app.ProgressDialog
//import android.util.Log
//import com.google.gson.Gson
//import io.reactivex.Observable
//import io.reactivex.Observer
//import io.reactivex.disposables.Disposable
//
///**
// * Created by Olije Favour on 7/1/2019.
// *Copyright (c) 2019  Itex Integrated Services  All rights reserved.
// */
//
//
//open class response<T>(val service:Observable<T>,val dialog: ProgressDialog,val returnResponse:ReturnResponse<T>) {
//
//   fun kilo() {
////       service.subscribe(object : Observer<T> {
////        override fun onComplete() {
////        }
////
////        override fun onSubscribe(d: Disposable) {
////        }
////
////        override fun onNext(t:T) {
////            dialog.dismiss()
////            Log.d("result", Gson().toJson(t))
////            returnResponse.error(t,)
////            mAbujaLookupRes.value =t
////        }
////
////        override fun onError(e: Throwable) {
////            dialog.dismiss()
////            returnResponse.error()
////
////            Log.d("Error >>>",e.message.toString())
////            mError.value = e.message.toString()
////
////            Log.d("e.message", Gson().toJson(e))
////
////        }
////
////    })
////   }
//
////    .subscribeOn(Schedulers.io())
////    .observeOn(AndroidSchedulers.mainThread() )
////
//
//   }
//}