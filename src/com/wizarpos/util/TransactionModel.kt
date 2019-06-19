package com.wizarpos.util

import com.iisysgroup.poslib.host.Host
import java.io.Serializable

/**
 * Created by Agbede on 2/28/2018.
 */
data class TransactionModel(val terminalID : String, val rrn : String, val cardholderName : String, val pan : String, val amount : String, val additionalAmount : String, val transactionType : String, val responseCode : String, val transactionStatus : String, val transactionStatusReason : String, val merchantId : String, val merchantName : String,  val ticket : String, val UNRP : String, val AC : String, val TVR : String, val AID : String, val TSI : String, val ISODateTime : String, val cardType : String, val AIP : String, val bankLogoName : String, val phoneNumber : String) : Serializable