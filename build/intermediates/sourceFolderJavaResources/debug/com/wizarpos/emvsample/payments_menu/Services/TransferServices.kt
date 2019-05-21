package com.wizarpos.emvsample.payments_menu.Services

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

public class TransferServices {
    val BASE_URL = "http://basehuge.itexapp.com:8090"
    //val BASE_URL = "http://vas.itexapp.com/"
    var retrofit: Retrofit? = null
    fun TransferService(): TransferService {
        val clientBuilder = OkHttpClient.Builder()


        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        clientBuilder.connectTimeout(20, TimeUnit.SECONDS)
        clientBuilder.readTimeout(30, TimeUnit.SECONDS)
        clientBuilder.writeTimeout(30, TimeUnit.SECONDS)

        //   clientBuilder.addInterceptor(logging)

        clientBuilder.addInterceptor(logging).build()


        val service = Retrofit.Builder()
                .baseUrl(TransferService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(clientBuilder.build())
                .build().create(TransferService::class.java)

        // val service = retrofit.create(TransferService::class.java)
//            val gson = GsonBuilder().setLenient().create()
//            val retrofit = Retrofit.Builder().client(clientBuilder.build()).addConverterFactory(GsonConverterFactory.create(gson)).addCallAdapterFactory(CoroutineCallAdapterFactory()).baseUrl(BASE_URL).build()
//            val service = retrofit.create(TransferService::class.java)

        return service
    }
}
