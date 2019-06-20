package com.wizarpos.emvsample

import com.iisysgroup.poslib.host.entities.VasTerminalData
import io.reactivex.Single
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface VasTerminalService {

    @GET("xmerchant.php")
    fun getVasTerminalDetails() : Single<VasTerminalData>


    object Factory {
        private val baseUrl = "http://197.253.19.75/tams/eftpos/op/"
        fun getService() : VasTerminalService {

            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

            val retrofitBuilder = Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .baseUrl(baseUrl)
                    .client(client)
                    .build()

            return retrofitBuilder.create(VasTerminalService::class.java)
        }
    }
}