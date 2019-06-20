package com.wizarpos.util

import android.app.Service
import android.content.ComponentName
import android.content.Intent
import android.os.Bundle
import android.os.IBinder
import android.support.annotation.Nullable
import android.util.Log
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.*
import java.io.IOException
import java.util.*

class MyService : Service() {
    private val TAG = MyService::class.java!!.getSimpleName()
    lateinit var request : Request

    private var isRunning: Boolean = false
    private var backgroundThread: Thread? = null


    @Nullable
    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    override fun onStart(intent: Intent, startId: Int) {
        super.onStart(intent, startId)

    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        val body = RequestBody.create(MediaType.parse("application/json"),intent.extras.getString("request"))
        request = Request.Builder()
                .url("https://pfm.payvice.com/api/tms/iisys/auth2")
                .post(body)
                .addHeader("content-type", "application/json")
                .addHeader("cache-control", "no-cache")
                .addHeader("authorization", "IISYS 74f230cc6cc96f7672aeb1f1745ccaec56de6e61f1d2ef2122441040ec58d044")
                .addHeader("iisysgroup", "21155ded2430abf93108bef7a62cf2cca1bcf3c3ea8a75e6527a53409be495d0")
                .build()
        Log.i("okh request string",intent.extras.getString("request"))
        Log.i("okh request body", request.body().toString())
        val client = OkHttpClient()
        stopService(Intent(this, MyService2::class.java))
        Log.i("okh", "Retrying Notification")
        GlobalScope.launch(Dispatchers.Default) {
            try{
                client.newCall(request).enqueue(object : Callback{
                    override fun onFailure(call: Call?, e: IOException?) {
                         retry(request)
                    }

                    override fun onResponse(call: Call?, response: Response?) {
                        if(response!!.isSuccessful){
                            Log.i("okh", "Background call completed with res code of " + response.code() + " and body of " + response.body()!!.string());

                            this@MyService.stopSelf()
                        }
                        else{
                            retry(request)
                        }
                    }

                })
            }catch (e : Exception){
                e.printStackTrace()
                retry(request)
            }
        }

        return Service.START_NOT_STICKY
    }

    override fun onDestroy() {
        Log.i("okh","Killing Service")
    }

    fun retry(request: Request){
        Log.i("okh", "Retrying Notification again")
        val task =
        Timer().scheduleAtFixedRate(CallTask(request,this),0,300000)
    }
     class  CallTask( val request : Request, val service : Service) : TimerTask() {
         override fun run() {
             val client = OkHttpClient()
             try{
                GlobalScope.launch(Dispatchers.Default) {
                     client.newCall(request).enqueue(object : Callback{
                         override fun onFailure(call: Call?, e: IOException?) {
                             retry()
                         }

                         override fun onResponse(call: Call?, response: Response?) {
                             if(response!!.isSuccessful){
                                 Log.i("okh", "Background call completed with res code of " + response.code() + " and body of " + response.body()!!.string());
                                 service.stopSelf()
                                 cancel()

                             }
                             else{
                                 retry()
                             }
                         }

                     })
                 }
             }catch (e : Exception){
                 e.printStackTrace()
                 retry()
             }
         }

         fun retry(){
             Log.i("okh", "Retrying Notification again")
             val client = OkHttpClient()
             try{
                GlobalScope.launch(Dispatchers.Default) {
                     client.newCall(request).enqueue(object : Callback{
                         override fun onFailure(call: Call?, e: IOException?) {
                             Thread.sleep(300000)
                             retry()
                         }

                         override fun onResponse(call: Call?, response: Response?) {
                             if(response!!.isSuccessful){
                                 Log.i("okh", "Background call completed with res code of " + response.code() + " and body of " + response.body()!!.string());
                                service.stopSelf()
                             }
                             else{
                                 Thread.sleep(300000)
                                 retry()
                             }
                         }

                     })
                 }
             }catch (e : Exception){
                 e.printStackTrace()
             }
         }

     }


}
