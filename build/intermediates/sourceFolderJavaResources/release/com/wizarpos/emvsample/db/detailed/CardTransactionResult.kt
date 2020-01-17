package com.wizarpos.emvsample.db.detailed

import android.arch.persistence.room.Entity
import android.arch.persistence.room.Ignore
import android.arch.persistence.room.PrimaryKey
import android.support.annotation.NonNull
import com.iisysgroup.poslib.host.Host
import java.io.Serializable
import java.util.*

@Entity
data class CardTransactionResult( @PrimaryKey(autoGenerate = true)
                             @NonNull
                            var id:Int?=null,
                            var RRN:String ="",
                            var authID:String ="",
                            var STAN:String ="",
                            var PAN:String ="",
                            var transactionStatus:String ="",
                            var responseCode:String ="",
                            var transactionStatusReason :String="",
                            var accountType:String = "",
                            var batchNumber:String = "",
                            var merchantID :String= "",
                            var isoTransmissionDateTime:String ="",
                            var terminalID:String = "",
                            var originalForwardingInstitutionCode:String = "",
                            var cardHolderName:String = "",
                            var cardExpiry :String= "",
                            var TSI :String= "",
                            var TVR:String = "",
                            var AID :String= "",
                            var amount: Long = 0,
                            var additionalAmount: Long = 0,
                            var longDateTime: Long = 0,
////                            var isRolledBack:Boolean = false,
//                            @Ignore
//                            var issuerAuthData91: String?="",
//                            @Ignore
//                            var issuerScript71: String?="",
//                            @Ignore
//                            var issuerScript72: String?="",
                             var transactionType:String=""):Serializable
//                             ,
//                             var  beneficiaryName:String ="",
//                             var  beneficiaryAcc:String="",
//                             var  bankCode:String="",
//                             var  beneficiaryBank:String="",
//                             var  BenefeciaryEmail:String="",
//                             var  beneficiaryPhone:String="",
//                             var  requestId:String="",
//                             var  remarks:String="",
//                             var  type:String="",
//                             var vasResponseCode:String="" , //Take his down
//                             var vasTransactionStatus :String="",//Take his down
//                             var vasResponseDescription:String="" ,
//                             var date:String="",
//                             var vasTransactionType:String="",
//                             var paymentMethod:String="",
//                             var transactionType:String=""
{
    @Ignore
    constructor(rrn: String, authID: String, stan: String, pan: String, transactionStatus: String, responseCode: String, transactionStatusReason: String, accountType: String, batchNumber: String, merchantID: String, isoTransmissionDateTime: String, terminalID: String, originalForwardingInstitutionCode: String, cardHolderName: String, cardExpiry: String, tsi: String, tvr: String, aid: String, amount: Long, additionalAmount: Long, longDateTime: Long,transactionType:Host.TransactionType) : this() {

    }
//    constructor() : this(null, "", "", "", "", "", 0.0, 0)

    fun isApproved(): Boolean {
        return this.responseCode.trim { it <= ' ' } == "00"
    }

//    fun isRolledBack(): Boolean {
//        return this.isRolledBack
//    }
//
//    fun setRolledBack(rolledBack: Boolean) {
//        this.isRolledBack = rolledBack
//    }
}

