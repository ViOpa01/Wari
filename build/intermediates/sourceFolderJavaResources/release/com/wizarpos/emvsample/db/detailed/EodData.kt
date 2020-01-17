package com.wizarpos.emvsample.db.detailed

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.support.annotation.NonNull
import java.io.Serializable

/**
 * Created by Olije Favour on 11/22/2019.
 *Copyright (c) 2019    All rights reserved.
 */

// *Copyright (c) 2019  Itex Integrated Services  All rights reserved.

@Entity
data class EodData(@PrimaryKey(autoGenerate = true)
                   @NonNull
                   var id:Int=0,
                   var transactionRef:String="",
                   var transactionType:String="", //card card-vas cash ;
                   var type:String="",//Vas or card
                   var specificTransaction:String ="",//purchase,transfer
                   var dateTime:Long=0,
                   var responseCode:String="",
                   var amount:String="" ): Serializable {
//    constructor(rrn: String, paymentMethodCard: String, timeInMills: Long, responseCode: String, valueOf: String) : this() {

//    }


    fun isApproved(): Boolean {
        return this.responseCode.trim { it <= ' ' } == "00"
    }

}