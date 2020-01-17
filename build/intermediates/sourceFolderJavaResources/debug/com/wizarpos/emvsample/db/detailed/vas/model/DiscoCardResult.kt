package com.wizarpos.emvsample.db.detailed.vas.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.io.Serializable

/**
 * Created by Olije Favour on 12/12/2019.
 *Copyright (c) 2019    All rights reserved.
 */

// *Copyright (c) 2019  Itex Integrated Services  All rights reserved.

@Parcelize
data class DiscoCardResult(var RRN:String?,
                           var authID:String?,
                           var STAN:String?,
                           var PAN:String?,
                           var transactionStatus:String?,
                           var responseCode:String?,
                           var transactionStatusReason :String?,
                           var accountType:String?,
                           var batchNumber:String?,
                           var merchantID :String?,
//                         var isoTransmissionDateTime:String? ,
                           var terminalID:String?,
//                         var originalForwardingInstitutionCode:String? ,
                           var cardHolderName:String?,
                           var cardExpiry :String?,
                           var TSI :String?,
                           var TVR:String?,
                           var AID :String?,
                           var amount: Long?,
                           var additionalAmount: Long?,
                           var longDateTime: Long?,
                           var isRolledBack:Boolean?= false,
                           val stan:String? ="",
                           val walletId: String="",
                           val marchantAddress: String="",
                           val marchantTid: String="",
                           val marchantName:String="",
                           val merchantId:String="",
                           val product: String="",
                           var transactionStatusMessage: String="",
                           val vasTid: String="",
                           val transactionRef: String="",
                           val paymentmethod: String="cash",
                           val logo:Int=0,
                           val dateTime:String="",
                           val error:Boolean=true,
                           val vasType:String="",
                           val recepiant_name: String,
                           val meterType:String,
                           val transactionId:String,
                           val unit:String ="",
                           val unit_value:String="",
                           val vat:String="",
                           val meterNumber:String="",
                           val token:String="",
                           val address:String="",
                           val arras:String="",
                           val tarrif:String=""):Parcelable {
}