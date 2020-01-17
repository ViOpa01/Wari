package com.wizarpos.emvsample.db.detailed.vas.vas_entity

import android.arch.persistence.room.*
import android.os.Parcelable
import android.support.annotation.NonNull
import com.wizarpos.emvsample.db.detailed.CardTransactionResult
import kotlinx.android.parcel.Parcelize

/**
 * Created by Olije Favour on 12/12/2019.
 *Copyright (c) 2019    All rights reserved.
 */

// *Copyright (c) 2019  Itex Integrated Services  All rights reserved.

   @Parcelize
   @Entity(indices =arrayOf(Index("card_id")),foreignKeys=[ForeignKey(entity = CardTransactionResult::class,parentColumns= arrayOf("id"),childColumns = arrayOf("card_id"))])
   data class   DiscoEntity(  @PrimaryKey(autoGenerate = true)
                                 @NonNull
                                 var id:Int?=null,
                              @ColumnInfo(name="card_id")
                              var  cardrowId:Integer?,
                             val stan:String? ="",
                             val amount: String="0.00",
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
                             val tarrif:String="") : Parcelable

