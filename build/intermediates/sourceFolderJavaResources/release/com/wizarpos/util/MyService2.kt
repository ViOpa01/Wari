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

class MyService2 : Service() {
    private val TAG = MyService2::class.java!!.getSimpleName()
    lateinit var request : Request

    private var isRunning: Boolean = false
    private var backgroundThread: Thread? = null


    @Nullable
    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    override fun onCreate() {
       val client = OkHttpClient()
        stopService(Intent(this, MyService::class.java))
        Log.i("okh", "Retrying Notification")
       GlobalScope.launch(Dispatchers.Default) {
            client.newCall(request).enqueue(object : Callback{
                override fun onFailure(call: Call?, e: IOException?) {
                    var intent = Intent(this@MyService2, MyService::class.java)
                    var reqString = Gson().toJson(request, Request::class.java)
                    intent.putExtra("request", reqString)
                    startService(intent)
                }

                override fun onResponse(call: Call?, response: Response?) {
                    if(response!!.isSuccessful){
                        Log.i("okh", "Background call completed with res code of " + response.code() + " and body of " + response.body()!!.string());
                        stopSelf()
                    }
                    else{
                        var intent = Intent(this@MyService2, MyService::class.java)
                        var reqString = Gson().toJson(request, Request::class.java)
                        intent.putExtra("request", reqString)
                        startService(intent)
                    }
                }
            })
        }

    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        request = Gson().fromJson(intent.extras.getString("request"), Request::class.java)

        return Service.START_NOT_STICKY
    }

    override fun onDestroy() {
        Log.i("okh","Killing Service")
    }

}
