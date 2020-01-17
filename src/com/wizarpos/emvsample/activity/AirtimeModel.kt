package com.wizarpos.emvsample.activity

import com.google.gson.annotations.Expose
import com.itex.richard.payviceconnect.model.Pfm



data class AirtimeSuccessResponse(@Expose val error : Boolean, @Expose val message: String, @Expose val amount: Boolean, @Expose val ref : String, @Expose val date : String,@Expose val transactionID:String)

data class AirtimeFailedResponse(@Expose val error : Boolean, @Expose val message: String, @Expose val ref : String, @Expose val date : String)

data class AirtimeRequestDetails(@Expose val clientReference:String,@Expose val terminal_id : String,  @Expose val user_id: String, @Expose val amount: String, @Expose val phone : String, @Expose val service : String, @Expose val pin : String, @Expose val password : String, @Expose val pfm : Pfm, @Expose val paymentMethod:String?)