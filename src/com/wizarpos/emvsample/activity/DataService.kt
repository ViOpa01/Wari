package com.wizarpos.emvsample.activity

import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST
import java.util.concurrent.TimeUnit

interface DataService {

    //API endpoint for AirtimeService
    @Headers("Authorization:IISYSGROUP c1e750cf89b05b0fc56eecf6fc25cce85e2bb8e0c46d7bfed463f6c6c89d4b8e","sysid:ee2dadd1e684032929a2cea40d1b9a2453435da4f588c1ee88b1e76abb566c31", "Content-Type:application/json")
    @POST("data/lookup")
    fun dataLookup(@Body service : DataModel.DataLookUpDetails) : Deferred<Any>

    @Headers("Authorization:IISYSGROUP c1e750cf89b05b0fc56eecf6fc25cce85e2bb8e0c46d7bfed463f6c6c89d4b8e","sysid:ee2dadd1e684032929a2cea40d1b9a2453435da4f588c1ee88b1e76abb566c31", "Content-Type:application/json")
    @POST("data/subscribe")
    fun dataSubscribe(@Body subscriptionDetails : DataModel.DataSubscriptionDetails) : Deferred<Any>

    @Headers("Authorization:IISYSGROUP c1e750cf89b05b0fc56eecf6fc25cce85e2bb8e0c46d7bfed463f6c6c89d4b8e","sysid:ee2dadd1e684032929a2cea40d1b9a2453435da4f588c1ee88b1e76abb566c31", "Content-Type:application/json")
//    @POST("card/data/subscribe")
    @POST("card/data/subscribe")
    fun dataSubscribeWithCard(@Body subsriptionDetails : DataModel.DataSubscriptionDetails) : Call<DataModel.DataSubscriptionSuccessResponse>

    companion object Factory {
        val BASE_URL = "http://197.253.19.75:8090/vas/"
        fun create(): DataService {
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY

            val clientBuilder = OkHttpClient.Builder()
            clientBuilder.addInterceptor(logging)

            clientBuilder.connectTimeout(30, TimeUnit.SECONDS)
            clientBuilder.readTimeout(60, TimeUnit.SECONDS)
            clientBuilder.writeTimeout(60, TimeUnit.SECONDS)

            val client = clientBuilder.build()

            val gson = GsonBuilder().setLenient().create()
            val retrofit = Retrofit.Builder().addConverterFactory(GsonConverterFactory.create(gson)).addCallAdapterFactory(CoroutineCallAdapterFactory()).client(client).baseUrl(BASE_URL).build()
            val service = retrofit.create(DataService::class.java)

            return service
        }
    }

}