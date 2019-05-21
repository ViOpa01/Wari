package com.wizarpos.emvsample.activity

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST
import java.util.concurrent.TimeUnit

class AirtimeServices {

        val BASE_URL = "http://vas.itexapp.com/"
        fun create(): AirtimeService {
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY

            val clientBuilder = OkHttpClient.Builder()
            clientBuilder.connectTimeout(20, TimeUnit.SECONDS)
            clientBuilder.readTimeout(30, TimeUnit.SECONDS)
            clientBuilder.writeTimeout(30, TimeUnit.SECONDS)
            clientBuilder.addInterceptor(logging)

            val client = clientBuilder.build()

            val gson = GsonBuilder().setLenient().create()
            val retrofit = Retrofit.Builder().client(client).addConverterFactory(GsonConverterFactory.create(gson)).baseUrl(BASE_URL).build()
            val service = retrofit.create(AirtimeService::class.java)

            return service
        }

}