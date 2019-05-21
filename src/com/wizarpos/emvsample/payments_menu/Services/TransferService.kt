package com.wizarpos.emvsample.payments_menu.Services

import android.util.Log
import com.google.gson.GsonBuilder
import com.wizarpos.emvsample.models.LookupSuccessModel
import com.wizarpos.emvsample.models.WithdrawalLookupSuccessModel
import com.iisysgroup.androidlite.models.WithdrawalWalletResponse.WithdrawalWalletCreditModel
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.wizarpos.emvsample.models.transfer.TransferSuccessModel
import com.wizarpos.emvsample.payments_menu.models.*
import kotlinx.coroutines.Deferred
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.Header
import retrofit2.http.POST
import java.util.concurrent.TimeUnit



interface TransferService {

    //API endpoint for account number lookup
//    @POST("/vas/vice-banking/transfer/lookup")
//    fun lookUpAccountNumberTransfer(@Body lookUpRequestDetails : AccountLookUpDetailTransfer) : Deferred<LookupSuccessModel>

    @POST("/vas/vice-banking/transfer/lookup")
    fun lookUpAccountNumberTransfer(@Body lookUpRequestDetails : AccountLookUpDetailTransfer) : Call<com.wizarpos.emvsample.payments_menu.models.LookupSuccessModel>

    @POST("/vas/vice-banking/withdrawal/lookup")
    fun lookUpAccountNumberWithdrawal(@Body lookUpRequestDetails : AccountLookUpDetailWithdrawal) : Call<WithdrawalLookupSuccessModel>

    //API endpoint for transfer
//    @POST("/vas/vice-banking/transfer/payment")
//    fun transfer(@Body transferModel : TransactionDetails, @Header("Content-Type") contentType : String = "application/json", @Header("ITEX-Signature") signature : String, @Header("ITEX-Nonce") nonce : String) : Deferred<TransactionResponse>

    @POST("/vas/vice-banking/transfer/payment")
    fun transfer(@Body transferModel: TransferDetails, @Header("Content-Type") contentType : String = "application/json", @Header("ITEX-Signature") signature : String, @Header("ITEX-Nonce") nonce : String) : Call<TransferSuccessModel>

    //API endpoint for withdraw
    @POST("/vas/vice-banking/withdrawal/payment")
    fun withdraw(@Body withdrawalModel : WithdrawalDetails, @Header("Content-Type") contentType : String = "application/json", @Header("ITEX-Signature") signature : String, @Header("ITEX-Nonce") nonce : String) : Call<WithdrawalWalletCreditModel>

    //API endpoint for deposit - cash
    @POST("/tams/tams/transfer-engine.php")
    fun deposit(@Body transferModel : TransactionDetails, @Header("Content-Type") contentType : String = "application/json", @Header("ITEX-Signature") signature : String, @Header("ITEX-Nonce") nonce : String) : Deferred<TransactionResponse>

    fun withdraws(@Body withdrawalModel : WithdrawalDetails, @Header("Content-Type") contentType : String = "application/json", @Header("ITEX-Signature") signature : String, @Header("ITEX-Nonce") nonce : String) : Deferred<WithdrawalWalletCreditModel>

    companion object Factory {
        //private val BASE_URL = "http://197.253.19.75"
        val BASE_URL = "http://basehuge.itexapp.com:8090"
        //  val BASE_URL = "http://vas.itexapp.com/"
        private var retrofit: Retrofit? = null
        public fun create(): TransferService {
            val clientBuilder = OkHttpClient.Builder()

            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY

            clientBuilder.connectTimeout(20, TimeUnit.SECONDS)
            clientBuilder.readTimeout(30, TimeUnit.SECONDS)
            clientBuilder.writeTimeout(30, TimeUnit.SECONDS)

            //   clientBuilder.addInterceptor(logging)

            clientBuilder.addInterceptor(logging).build()

            val service = Retrofit.Builder()
                    .baseUrl(BASE_URL)
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
}