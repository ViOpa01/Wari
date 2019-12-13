package com.wizarpos.emvsample.db.detailed

import android.arch.persistence.room.*
import android.support.annotation.NonNull
import com.wizarpos.emvsample.services.helper.activity.util.Models

/**
 * Created by Olije Favour on 11/4/2019.
 *Copyright (c) 2019    All rights reserved.
 */

// *Copyright (c) 2019  Itex Integrated Services  All rights reserved.

@Entity(indices =arrayOf(Index("card_id")),foreignKeys=[ForeignKey(entity = CardTransactionResult::class,parentColumns= arrayOf("id"),childColumns = arrayOf("card_id"))])
//data class VasTransactionResult (
//        @PrimaryKey(autoGenerate = true)
//        @NonNull
//        var  id:Int=0,
//        @ColumnInfo(name="card_id")
//        var  cardrowId:Integer?,
//        var  beneficiaryName:String ="",
//        var  beneficiaryAcc:String="",
//        var  bankCode:String="",
//        var  beneficiaryBank:String="",
//        var  BenefeciaryEmail:String="",
//        var  beneficiaryPhone:String="",
//        var  amount:Long=0L,
//        var  requestId:String="",
//        var  remarks:String="",
//        var  type:String="",
//        var  vasResponseCode:String="" , //Take his down
//        var  vasTransactionStatus :String="",//Take his down
//        var  vasResponseDescription:String="" ,
//        var  dateTime:Long=0L,
//        var  vasTransactionType:String="",
//        var  paymentMethod:String="",
//        var  transactionType:String="",
//        var  token:String,
//        var  smartCardNumber:String )


data class VasTransactionResult (
        @PrimaryKey(autoGenerate = true)
        @NonNull
        var  id:Int=0,
        @ColumnInfo(name="card_id")
         var stan:String? ="",
        var amount: String="0.00",
        var walletId: String="",
        var marchantAddress: String="",
        var marchantTid: String="",
        var marchantName:String="",
        var merchantId:String="",
        var product: String="",
        var transactionStatusMessage: String="",
        var vasTid: String="",
        var transactionRef: String="",
        var paymentmethod: String="cash",
        var logo:Int=0,
        var dateTime:String="",
        var error:Boolean=true,
        var vasType:String=""){

}