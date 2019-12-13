package com.wizarpos.emvsample.history_summary.service

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.wizarpos.emvsample.history_summary.model.HistoryModel
import kotlinx.coroutines.Deferred
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import java.util.concurrent.TimeUnit

interface HistoryService {


    @GET("vas/history/{walletId}")
    fun getWalletHistory(@Path("walletId") walletId : String) : Deferred<HistoryModel>

    companion object {
        private val base_url = "https://197.253.19.77/api/"
        fun getInstance() : HistoryService {
            val clientBuilder = OkHttpClient.Builder()

            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY

            clientBuilder.connectTimeout(20, TimeUnit.SECONDS)
            clientBuilder.readTimeout(30, TimeUnit.SECONDS)
            clientBuilder.writeTimeout(30, TimeUnit.SECONDS)

            clientBuilder.addInterceptor(logging)


            val retrofit = Retrofit.Builder()
                    .baseUrl(base_url)
                    .addConverterFactory( GsonConverterFactory.create())
                    .addCallAdapterFactory(CoroutineCallAdapterFactory())
                    .client(clientBuilder.build())
                    .build()

            return retrofit.create(HistoryService::class.java)
        }
    }
}