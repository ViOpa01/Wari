package com.wizarpos.emvsample.generators

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.os.Build
import com.iisysgroup.poslib.host.entities.TransactionResult
import java.util.*
import android.os.BatteryManager
import android.content.Context.BATTERY_SERVICE
import android.content.Intent
import android.net.NetworkInfo
import android.net.ConnectivityManager
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.google.gson.Gson
import com.wizarpos.emvsample.activity.login.securestorage.SecureStorage
import com.wizarpos.util.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.*
import org.jetbrains.anko.doAsync
import java.io.IOException


class PfmNotification : AppCompatActivity(){

    lateinit var transaction : TransactionResult

    companion object {
        @JvmStatic var pfmsent: Boolean = false
    }

    fun sendNotification(transactionResult: TransactionResult, context: Context){
        val bm = context.getSystemService(BATTERY_SERVICE) as BatteryManager
        var batLevel = 0
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            batLevel =  bm.getIntProperty(BatteryManager.BATTERY_PROPERTY_CAPACITY)
        } else {
            batLevel = 0
        }
        var cstate = "";
        transaction = transactionResult

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if(bm.isCharging){
                cstate = "isCharging"
            }else{
                cstate = "NotCharging"
            }
        } else {

                cstate = "NotCharging"
        }
        var pfmstate = PfmState(Build.SERIAL, Date().toGMTString(), batLevel, cstate, "nil", transactionResult.terminalID, getNetworkType(context),
                "cid:,lac:,mcc:,mnc:,ss", "nil", "ME30S", "Newland MPOS - " + Build.MANUFACTURER, "true", context.getPackageManager()
                .getPackageInfo(context.getPackageName(), 0).versionName.toString(), Date(transactionResult.longDateTime).toGMTString(), "OK", "nil");

        var pfmJournal = PfmJournal(transactionResult.STAN, transactionResult.PAN, transactionResult.cardHolderName, transactionResult.cardExpiry, transactionResult.RRN, transactionResult.authID, transactionResult.amount.toInt(), Date(transactionResult.longDateTime).toGMTString(),
                "nil", "nil", transactionResult.responseCode, "true", transactionResult.transactionStatusReason, "nil", transactionResult.STAN, transactionResult.RRN, transactionResult.authID, transactionResult.merchantID, "nil", "nil", "nil",
                "Card", "nil", "nil");

        val notificationModel = NotificationModel(pfmstate,pfmJournal)

       GlobalScope.launch(Dispatchers.Default) {
            val client = OkHttpClient()
            Log.i("okh", "Background Pfm call started")

            val mediaType = MediaType.parse("application/json")
//            Log.i("okh", notificationModel.build(SharedPreferenceUtils.getNotificationUser(context)))
//            val body = RequestBody.create(mediaType, notificationModel.build(SharedPreferenceUtils.getNotificationUser(context)))
//            val request = Request.Builder()
//                    .url("https://pfm.payvice.com/api/tms/iisys/auth2")
//                    .post(body)
//                    .addHeader("content-type", "application/json")
//                    .addHeader("cache-control", "no-cache")
//                    .addHeader("authorization", "IISYS 74f230cc6cc96f7672aeb1f1745ccaec56de6e61f1d2ef2122441040ec58d044")
//                    .addHeader("iisysgroup", "21155ded2430abf93108bef7a62cf2cca1bcf3c3ea8a75e6527a53409be495d0")
//                    .build()

//            val response = client.newCall(request).enqueue(object : Callback{
//                override fun onFailure(call: Call?, e: IOException?) {
//                    var intent = Intent(context, MyService::class.java)
//                    intent.putExtra("request",notificationModel.build(SharedPreferenceUtils.getNotificationUser(context)))
//                    context.startService(intent)
//                }
//
//                override fun onResponse(call: Call?, response: Response) {
//                            if (response.code()==200){
//                                SecureStorage.store(transactionResult.RRN, true);
//                                //pfmsent = true
//                                transactionResult.transactionStatus = transactionResult.transactionStatus + "\nPFM sent"
//                                Log.d("okh", "pfm true")
//
//                            }
//                    Log.i("okh", "Background call completed with res code of " + response.code() + " and body of " + response.body()!!.string())
//                }
//            })

        }
    }

    fun generatePFM(transactionResult: TransactionResult, context: Context) : PFMDATA{

        val batLevel = 0
        var cstate = ""
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val bm =  context.getSystemService(BATTERY_SERVICE) as BatteryManager
            bm.getIntProperty(BatteryManager.BATTERY_PROPERTY_CAPACITY)
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if(bm.isCharging){
                    cstate = "isCharging"
                }else{
                    cstate = "NotCharging"
                }
            } else {

                cstate = "NotCharging"
            }
        }




        var pfmstate = PfmState(Build.SERIAL, Date().toGMTString(),batLevel,cstate,"nil",transactionResult.terminalID,getNetworkType(context) + "",
                "cid:,lac:,mcc:,mnc:,ss","nil","ME30S","Newland MPOS - " + Build.MANUFACTURER,"true",context.getPackageManager()
                .getPackageInfo(context.getPackageName(), 0).versionName.toString(),Date(transactionResult.longDateTime).toGMTString(),"OK","nil");
         var pfmJournal : PfmJournal
        if(transactionResult != null){
             pfmJournal = PfmJournal(transactionResult.STAN,transactionResult.PAN,transactionResult.cardHolderName,transactionResult.cardExpiry,transactionResult.RRN,transactionResult.authID,transactionResult.amount.toInt(),Date(transactionResult.longDateTime).toGMTString(),
                    "nil","nil",transactionResult.responseCode,"true",transactionResult.transactionStatusReason,"nil",transactionResult.STAN, transactionResult.RRN,transactionResult.authID,transactionResult.merchantID,"nil","nil","nil",
                    "Card","nil","nil");
        }else{
             pfmJournal = PfmJournal("nil","nil",transactionResult.cardHolderName,transactionResult.cardExpiry,transactionResult.RRN,transactionResult.authID,transactionResult.amount.toInt(),Date(transactionResult.longDateTime).toGMTString(),
                    "nil","nil",transactionResult.responseCode,"true",transactionResult.transactionStatusReason,"nil",transactionResult.STAN, transactionResult.RRN,transactionResult.authID,transactionResult.merchantID,"nil","nil","nil",
                    "Card","nil","nil");
        }


     //   return PFMDATA(pfmstate,pfmJournal,"String","itex")
        return PFMDATA(pfmstate,pfmJournal)

    }

    fun sendNotification(transactionResult: TransactionResult, context: Context, vasPorduct: String,vasCategory : String){
        val bm = context.getSystemService(BATTERY_SERVICE) as BatteryManager
        val batLevel = 0
         if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
             val batLevel =  bm.getIntProperty(BatteryManager.BATTERY_PROPERTY_CAPACITY)
        } else {
             val batLevel = 0
        }
        var cstate = "";


        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if(bm.isCharging){
                cstate = "isCharging"
            }else{
                cstate = "NotCharging"
            }
        } else {

            cstate = "NotCharging"
        }
        var pfmstate = PfmState(Build.SERIAL, Date().toGMTString(),batLevel,cstate,"nil",transactionResult.terminalID,getNetworkType(context),
                "cid:,lac:,mcc:,mnc:,ss","nil","ME30S","Newland MPOS - " + Build.MANUFACTURER,"true",context.getPackageManager()
                .getPackageInfo(context.getPackageName(), 0).versionName.toString(),Date(transactionResult.longDateTime).toGMTString(),"OK","nil");

        var pfmJournal = PfmJournal(transactionResult.STAN, transactionResult.PAN, transactionResult.cardHolderName, transactionResult.cardExpiry, transactionResult.RRN, transactionResult.authID, transactionResult.amount.toInt(), Date(transactionResult.longDateTime).toGMTString(),
                "", "", transactionResult.responseCode, "true", transactionResult.transactionStatusReason, "nil", transactionResult.STAN, transactionResult.RRN, transactionResult.authID, transactionResult.merchantID, vasCategory, vasPorduct, "nil",
                "Card", "nil", "nil");

        val notificationModel = NotificationModel(pfmstate,pfmJournal)

//       GlobalScope.launch(Dispatchers.Default) {
//            val client = OkHttpClient()
//            Log.i("okh", "Background Pfm call started")
//
//            val mediaType = MediaType.parse("application/json")
//            Log.i("okh", notificationModel.build(SharedPreferenceUtils.getNotificationUser(context)))
//            val body = RequestBody.create(mediaType, notificationModel.build(SharedPreferenceUtils.getNotificationUser(context)))
//            val request = Request.Builder()
//                    .url("https://pfm.payvice.com/api/tms/iisys/auth2")
//                    .post(body)
//                    .addHeader("content-type", "application/json")
//                    .addHeader("cache-control", "no-cache")
//                    .addHeader("authorization", "IISYS 74f230cc6cc96f7672aeb1f1745ccaec56de6e61f1d2ef2122441040ec58d044")
//                    .addHeader("iisysgroup", "21155ded2430abf93108bef7a62cf2cca1bcf3c3ea8a75e6527a53409be495d0")
//                    .build()
//
//            val response = client.newCall(request).enqueue(object : Callback{
//                override fun onFailure(call: Call?, e: IOException?) {
//                    var intent = Intent(context, MyService::class.java)
//                    intent.putExtra("request",notificationModel.build(SharedPreferenceUtils.getNotificationUser(context)))
//                    context.startService(intent)
//                }
//
//                override fun onResponse(call: Call?, response: Response) {
//                    Log.i("okh", "Background call completed with res code of " + response.code() + " and body of " + response.body()!!.string());
//                }
//
//            })
//
//        }
    }

    fun sendNotification(vasPorduct: String,vasCategory : String, amount : Int , context: Context, rescode : String, mid : String){
        val bm = context.getSystemService(BATTERY_SERVICE) as BatteryManager
        val batLevel = 0
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            bm.getIntProperty(BatteryManager.BATTERY_PROPERTY_CAPACITY)
        }
        var cstate = "";


        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if(bm.isCharging){
                cstate = "isCharging"
            }else{
                cstate = "NotCharging"
            }
        } else {

            cstate = "NotCharging"
        }
        var pfmstate = PfmState(Build.SERIAL, Date().toGMTString(),batLevel,cstate,"nil",SharedPreferenceUtils.getTerminalId(context),getNetworkType(context),
                "cid:,lac:,mcc:,mnc:,ss","nil","ME30S","Newland MPOS - " + Build.MANUFACTURER,"true",context.getPackageManager()
                .getPackageInfo(context.getPackageName(), 0).versionName.toString(),Date().toGMTString(),"OK","nil");

        var pfmJournal = PfmJournal("nil","","","","nil","nil",amount,Date().toGMTString(),
                "","",rescode,"true","","","nil", "nil","nil",mid,vasCategory,vasPorduct,"",
                "Cash","nil","");

        val notificationModel = NotificationModel(pfmstate,pfmJournal)

//       GlobalScope.launch(Dispatchers.Default) {
//            val client = OkHttpClient()
//            Log.i("okh", "Background Pfm call started")
//
//            val mediaType = MediaType.parse("application/json")
//            Log.i("okh", notificationModel.build(SharedPreferenceUtils.getNotificationUser(context)))
//            val body = RequestBody.create(mediaType, notificationModel.build(SharedPreferenceUtils.getNotificationUser(context)))
//            val request = Request.Builder()
//                    .url("https://pfm.payvice.com/api/tms/iisys/auth2")
//                    .post(body)
//                    .addHeader("content-type", "application/json")
//                    .addHeader("cache-control", "no-cache")
//                    .addHeader("authorization", "IISYS 74f230cc6cc96f7672aeb1f1745ccaec56de6e61f1d2ef2122441040ec58d044")
//                    .addHeader("iisysgroup", "21155ded2430abf93108bef7a62cf2cca1bcf3c3ea8a75e6527a53409be495d0")
//                    .build()
//
//            val response = client.newCall(request).enqueue(object : Callback{
//                override fun onFailure(call: Call?, e: IOException?) {
//                    var intent = Intent(context, MyService::class.java)
//                    intent.putExtra("request",notificationModel.build(SharedPreferenceUtils.getNotificationUser(context)))
//                    context.startService(intent)
//                }
//
//                override fun onResponse(call: Call?, response: Response) {
//                    Log.i("okh", "Background call completed with res code of " + response.code() + " and body of " + response.body()!!.string());
//                }
//
//            })
//
//        }
    }

    fun getNetworkType(context: Context): String? {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val info = cm.activeNetworkInfo
        return if(info == null){"nil"}else{ info.typeName }
    }

   // data class PFMDATA(val state : PfmState, val journal : PfmJournal, val getRRN : String, val requestType : String)
    data class PFMDATA(val state : PfmState, val journal : PfmJournal)
}