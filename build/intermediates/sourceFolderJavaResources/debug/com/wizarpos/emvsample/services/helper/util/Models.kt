package com.wizarpos.emvsample.services.helper.activity.util

import java.io.Serializable


/**
 * Created by Olije Favour on 7/2/2019.
 *Copyright (c) 2019  Itex Integrated Services  All rights reserved.
 */

  class Models {
    data class GeneralElectricityDetails(val amount: String, val wallet: String, val userName: String, val requestType: String, val meterType: String, val meterNumber: String, val channel: String, val phone_number: String, val productCode: String, val paymentMetod: String, val clientReference: String, val terminalId: String, val electricMeterType: String, val password: String, val meterName: String,val address:String):Serializable

    data class DiscosModel(val error:Boolean,val recepiant_name: String,val meterType:String,val transactionId:String,val unit:String ="",val unit_value:String="",val vat:String="",val meterNumber:String="",val token:String="",val address:String="",val arras:String="",val tarrif:String=""):Serializable

//    vasType ie transfer cable transfer disco
//    Any: VasTypeModel
    //Transaction Message   val amount: String, val walletId: String, val marchantAddress: String, val marchantTid: String, val product: String, val transactionStatus: String, val vasTid: String, val transactionRef: String, val paymentmethod: String,val logo:Int,val dateTime:String

    data class AirtimeModel(val error:Boolean,val recepiant_phone: String):Serializable

    data class  TransferModel(val error:Boolean,val recepiant: String,val accountName:String,val recivingBank:String):Serializable

    data class CableTvModel(val error:Boolean,val iuc: String):Serializable

    data class CardDetails(val terminalID : String="", val rrn : String="", val cardholderName : String="", val pan : String="", val amount : String="", val additionalAmount : String="", val transactionType : String="", val responseCode : String="", val transactionStatus : String="", val transactionStatusReason : String="", val merchantId : String="", val merchantName : String="",  val ticket : String="", val UNRP : String="", val AC : String="", val TVR : String="", val AID : String="", val TSI : String="", val ISODateTime : String="", val cardType : String="", val Expiry : String="", val bankLogoName : String="") : Serializable

    data class VasDetails( val stan:String ="",val amount: String="0.00", val walletId: String="", val marchantAddress: String="", val marchantTid: String="", val marchantName:String="",  val merchantId:String="",val product: String="", val transactionStatusMessage: String="", val vasTid: String="", val transactionRef: String="", val paymentmethod: String="cash",val logo:Int=0,val dateTime:String="",val error:Boolean=true,val vasType:String="",val  VasTypeModel:Any=AirtimeModel(false,"")):Serializable


    companion object{
        const val DISCO :String ="disco"
        const val AIRTIME :String ="airtime"
        const val TRANSFER :String ="transfer"
        const val CABLE_TV :String ="cable_tv"
        const val PURCHASE :String ="purchase"
        const val DATA :String ="data"
    }
}

