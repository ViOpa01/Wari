package com.wizarpos.emvsample.activity

import com.google.gson.annotations.Expose


data class AirtimeSuccessResponse(@Expose val error : Boolean, @Expose val message: String, @Expose val amount: Boolean, @Expose val ref : String, @Expose val date : String)

data class AirtimeFailedResponse(@Expose val error : Boolean, @Expose val message: String, @Expose val ref : String, @Expose val date : String)

data class AirtimeRequestDetails(@Expose val terminal_id : String, @Expose val user_id: String, @Expose val amount: String, @Expose val phone : String, @Expose val service : String, @Expose val pin : String, @Expose val password : String)