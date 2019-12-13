package com.wizarpos.emvsample.db.detailed

import android.arch.persistence.room.Ignore
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by Olije Favour on 11/10/2019.
 *Copyright (c) 2019    All rights reserved.
 */

// *Copyright (c) 2019  Itex Integrated Services  All rights reserved.

@Parcelize
data class VasCardResult(var RRN:String?,
                         var authID:String?,
                         var STAN:String? ,
                         var PAN:String? ,
                         var transactionStatus:String? ,
                         var responseCode:String? ,
                         var transactionStatusReason :String?,
                         var accountType:String? ,
                         var batchNumber:String? ,
                         var merchantID :String?,
//                         var isoTransmissionDateTime:String? ,
                         var terminalID:String? ,
//                         var originalForwardingInstitutionCode:String? ,
                         var cardHolderName:String? ,
                         var cardExpiry :String?,
                         var TSI :String?,
                         var TVR:String? ,
                         var AID :String?,
                         var amount: Long? ,
                         var additionalAmount: Long? ,
                         var longDateTime: Long? ,
                         var isRolledBack:Boolean?= false,
//                         @Ignore
//                         var issuerAuthData91: String?,
//                         @Ignore
//                         var issuerScript71: String?,
//                         @Ignore
//                         var issuerScript72: String?,
//                         var transactionType:String?,
                         var  id:Int,
                         var  beneficiaryName:String?,
                         var  beneficiaryAcc:String?,
                         var  bankCode:String?,
                         var  beneficiaryBank:String?,
                         var  BenefeciaryEmail:String?,
                         var  beneficiaryPhone:String?,
//                         var  amount:Long=0L,
                         var  requestId:String?,
                         var  remarks:String?,
                         var  type:String?,
                         var  vasResponseCode:String? , //Take his down
                         var  vasTransactionStatus :String?,//Take his down
                         var  vasResponseDescription:String? ,
                         var  dateTime:Long=0L,
                         var  vasTransactionType:String?,
                         var  paymentMethod:String?,
                         var  transactionType:String?) : Parcelable



//@Parcelize
//class VasCardResult(var RRN:String?,
//                    var authID:String?,
//                    var STAN:String? ,
//                    var PAN:String? ,
//                    var transactionStatus:String? ,
//                    var responseCode:String? ,
//                    var transactionStatusReason :String?,
//                    var accountType:String? ,
//                    var batchNumber:String? ,
//                    var merchantID :String?,
//                    var terminalID:String? ,
//                    var cardHolderName:String? ,
//                    var cardExpiry :String?,
//                    var TSI :String?,
//                    var TVR:String? ,
//                    var AID :String?,
//                    var amount: Long? ,
//                    var additionalAmount: Long? ,
//                    var longDateTime: Long? ,
//                    var isRolledBack:Boolean?= false,
//                    val stan:String? ,
//                    val walletId: String,
//                    val marchantAddress: String,
//                    val marchantTid: String,
//                    val marchantName:String,
//                    val merchantId:String,
//                    val product: String,
//                    var transactionStatusMessage: String,
//                    val vasTid: String,
//                    val transactionRef: String,
//                    val paymentmethod: String,
//                    val logo:Int,
//                    val dateTime:String,
//                    val error:Boolean,
//                    val vasType:String) : Parcelable {
//    constructor():this(RRN,
//            authID,
//            STAN)
//                 PAN ,
//                 transactionStatus ,
//                 responseCode ,
//                 transactionStatusReason ,
//                 accountType ,
//                 batchNumber ,
//                 merchantID ,
//                 terminalID ,
//                 cardHolderName ,
//                 cardExpiry ,
//                 TSI ,
//                 TVR ,
//                 AID ,
//                 amount ,
//                 additionalAmount ,
//                 longDateTime ,
//                 isRolledBack,
//                 stan ,
//                 walletId,
//                 marchantAddress,
//                 marchantTid,
//                 marchantName,
//                 merchantId,
//                 product,
//                 transactionStatusMessage,
//                 vasTid,
//                 transactionRef,
//                 paymentmethod,
//                 logo,
//                 dateTime,
//                 error,
//                 vasType):

//}