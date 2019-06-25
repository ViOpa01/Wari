package com.wizarpos.emvsample.activity

import android.content.Context
import android.preference.PreferenceManager
import android.util.Log
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.wizarpos.emvsample.R
import com.wizarpos.emvsample.activity.login.Helper
import com.wizarpos.emvsample.activity.login.securestorage.SecureStorage
import com.wizarpos.util.SharedPreferenceUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.ConnectException
import java.net.SocketTimeoutException

class AirtimeProcessor(val context : Context, listener : onAirtimeTransactionResultListener, airtimeProvider : String, phoneNumber : String, airtimeAmount : String, isCard : Boolean = false) :  Callback<Any> {

    private val mPayvicePin by lazy {
        PreferenceManager.getDefaultSharedPreferences(context).getString(context.getString(R.string.key_payvice_wallet_pin),"")
    }

    private var isCard : Boolean = false

   private val listener = listener
   private val airtimeProvider = airtimeProvider
   private val phoneNumber = phoneNumber
   private val airtimeAmount = airtimeAmount

   private val terminalID =  PreferenceManager.getDefaultSharedPreferences(context).getString(context.getString(R.string.key_terminal_id), null)

    private val wallet_username by lazy {
        SharedPreferenceUtils.getPayviceUsername(context)
    }

    private val wallet_id by lazy {
        SharedPreferenceUtils.getPayviceWalletId(context)
    }

    private val wallet_password by lazy {
        SharedPreferenceUtils.getPayvicePassword(context)
    }

    private val wallet_clear_password by lazy {
        SecureStorage.retrieve(Helper.PLAIN_PASSWORD, "")
    }

    fun performTransaction(isCard: Boolean = false, pin : String){
        this.isCard = isCard

        if (isCard){
            val details = AirtimeRequestDetails(amount = airtimeAmount, phone = phoneNumber, service = airtimeProvider, terminal_id = wallet_id, user_id = wallet_username, password = wallet_clear_password, pin = pin)
            AirtimeService.create().airtimeCardPurchase(details).enqueue(this)
            return
        }

        val details = AirtimeRequestDetails(amount = airtimeAmount, phone = phoneNumber, service = airtimeProvider, terminal_id = wallet_id, user_id = wallet_username, password = wallet_clear_password, pin = pin)
        AirtimeService.create().airtimePurchase(details).enqueue(this)

    }

    interface onAirtimeTransactionResultListener {
        fun onResponse(model : AirtimeSuccessResponse)
        fun onError(errorMessage : String, isCard : Boolean)
    }

    override fun onFailure(call: Call<Any>?, t: Throwable?) {
        t?.let {
            Log.d("Special error", "Some error2")
            Log.d("Special error", it.toString())
            if (it is SocketTimeoutException){
                listener.onError("Connection is taking too long. Please try again later", isCard)
                return
            }

            if (it is ConnectException){
                listener.onError("Please check your internet connection.", isCard)
                return
            }

            if (it is retrofit2.HttpException){
                listener.onError("Server error", isCard)
                return
            }
            it.message?.let {
                listener.onError(it, isCard)
            }
        }

    }

    override fun onResponse(call: Call<Any>?, response: Response<Any>?) {
        response?.body()?.let {
            val jsonResponse = Gson().toJsonTree(it)
            val gson = GsonBuilder().excludeFieldsWithoutExposeAnnotation().create()

            if (jsonResponse.toString().contains("amount")){
                val parsedResponse = gson.fromJson(jsonResponse.toString(),  AirtimeSuccessResponse::class.java)
                listener.onResponse(parsedResponse)
            } else {
                val parsedResponse = gson.fromJson(jsonResponse.toString(), AirtimeFailedResponse::class.java)
                listener.onError(parsedResponse.message, isCard)
            }
        }
    }
}