package com.wizarpos.emvsample.activity

import android.content.Context
import android.preference.PreferenceManager
import android.util.Log
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.iisysgroup.poslib.commons.emv.EmvCard
import com.itex.richard.payviceconnect.model.AirtimeModel
import com.wizarpos.emvsample.R
import com.wizarpos.emvsample.activity.login.Helper
import com.wizarpos.emvsample.activity.login.securestorage.SecureStorage
import com.wizarpos.emvsample.generators.PfmStateGenerator
import com.wizarpos.emvsample.models.PfmJournalGenerator
import com.wizarpos.util.MemoryUtil
import com.wizarpos.util.SharedPreferenceUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.util.*

class AirtimeProcessor(val context : Context, listener : onAirtimeTransactionResultListener, airtimeProvider : String, phoneNumber : String, airtimeAmount : String, isCard : Boolean = false) :  Callback<AirtimeModel.AirtimePinResponse> {

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
        Log.d("airtimeType performTransaction >>>>>", "wallet")

        this.isCard = isCard



        MemoryUtil.setValue(context, MemoryUtil.LastTransactionTimeKey, Date());




        val tid = SecureStorage.retrieve(Helper.TERMINAL_ENTERED_BY_USER, "")


        var pinInfo:EmvCard.PinInfo? = null
//        Log.d("pinInfo  >>>>>", Gson().toJson(pinInfo))

        var emvCard :EmvCard?= null
//        Log.d("emvCard  >>>>>", Gson().toJson(emvCard))


//        Log.d("generate State   >>>>>", Gson().toJson(PfmStateGenerator(context,tid ).generateState()))
//
//        Log.d("Journal Generator  >>>>>", Gson().toJson(PfmJournalGenerator(FuncActivity.appState.trans.getTransactionResult(), FuncActivity.appState.nibssData.getConfigData(), false, airtimeAmount, emvCard, "Airtime", airtimeProvider, "").generateJournal()))

//        val tid = SecureStorage.retrieve(Helper.TERMINAL_ENTERED_BY_USER, "")

//
        var pfm:com.itex.richard.payviceconnect.model.Pfm? = null
//        Log.d("pfm  >>>>>", Gson().toJson(pfm))



        Log.d("airtimeType About to purchase  >>>>>", "Here")

        if (isCard){
            Log.d("airtimeType About to purchase  >>>>>", "card")


            pinInfo = EmvCard.PinInfo(FuncActivity.appState.trans.getPinBlock(), null, null)
            Log.d("pinInfo  >>>>>", Gson().toJson(pinInfo))

            emvCard = EmvCard(FuncActivity.appState.trans.getCardHolderName(), FuncActivity.appState.trans.getTrack2Data(), FuncActivity.appState.trans.getICCData(), pinInfo)
            Log.d("emvCard  >>>>>", Gson().toJson(emvCard))


            Log.d("generate State   >>>>>", Gson().toJson(PfmStateGenerator(context,tid ).generateState()))

            Log.d("Journal Generator  >>>>>", Gson().toJson(PfmJournalGenerator(FuncActivity.appState.trans.getTransactionResult(), FuncActivity.appState.nibssData.getConfigData(), false, airtimeAmount, emvCard, "Airtime", airtimeProvider, "").generateJournal()))

//        val tid = SecureStorage.retrieve(Helper.TERMINAL_ENTERED_BY_USER, "")

//
            pfm = com.itex.richard.payviceconnect.model.Pfm(PfmStateGenerator(context,tid ).generateState(), PfmJournalGenerator(FuncActivity.appState.trans.getTransactionResult(), FuncActivity.appState.nibssData.getConfigData(), false, airtimeAmount, emvCard, "Airtime", airtimeProvider, "").generateJournal())
            Log.d("pfm  >>>>>", Gson().toJson(pfm))

            val details = AirtimeRequestDetails(amount = airtimeAmount, phone = phoneNumber, service = airtimeProvider, terminal_id = wallet_id, user_id = wallet_username, password = wallet_clear_password, pin = pin, pfm = pfm,paymentMethod = "card")
            AirtimeService.create().airtimeCardPurchase(details).enqueue(this)
            return
        }else {
            Log.d("airtimeType About to purchase alrigh >>>>>", "wallet")

            pinInfo = null
            Log.d("pinInfo  >>>>>", Gson().toJson(pinInfo))

            emvCard = null
            Log.d("emvCard  >>>>>", Gson().toJson(emvCard))


            Log.d("generate State   >>>>>", Gson().toJson(PfmStateGenerator(context,tid ).generateState()))

            Log.d("Journal Generator  >>>>>", Gson().toJson(PfmJournalGenerator(null, FuncActivity.appState.nibssData.getConfigData(), false, airtimeAmount, emvCard, "Airtime", airtimeProvider, "").generateJournal()))

//        val tid = SecureStorage.retrieve(Helper.TERMINAL_ENTERED_BY_USER, "")

//
            pfm = com.itex.richard.payviceconnect.model.Pfm(PfmStateGenerator(context,tid ).generateState(), PfmJournalGenerator(FuncActivity.appState.trans.getTransactionResult(), FuncActivity.appState.nibssData.getConfigData(), false, airtimeAmount, emvCard, "Airtime", airtimeProvider, "").generateJournal())
            Log.d("pfm  >>>>>", Gson().toJson(pfm))

            val details = AirtimeRequestDetails(amount = airtimeAmount, phone = phoneNumber, service = airtimeProvider, terminal_id = wallet_id, user_id = wallet_username, password = wallet_clear_password, pin = pin, pfm = pfm,paymentMethod = "cash")

            Log.d("AirtimeRequestDetails wallet  >>>>>", Gson().toJson(details))


            AirtimeService.create().airtimePurchase(details).enqueue(this)

        }

    }

    interface onAirtimeTransactionResultListener {
        fun onResponse(model : AirtimeSuccessResponse)
        fun onError(errorMessage : String, isCard : Boolean)
    }

    override fun onFailure(call: Call<AirtimeModel.AirtimePinResponse>?, t: Throwable?) {
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

    override fun onResponse(call: Call<AirtimeModel.AirtimePinResponse>?, response: Response<AirtimeModel.AirtimePinResponse>?) {
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