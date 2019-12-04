//package com.wizarpos.emvsample.db.detailed;
//
//import android.arch.persistence.room.PrimaryKey;
//import android.support.annotation.NonNull;
//
///**
// * Created by Olije Favour on 11/22/2019.
// * Copyright (c) 2019    All rights reserved.
// */
//
//// *Copyright (c) 2019  Itex Integrated Services  All rights reserved.
//
//
//public class EodData(
//        @PrimaryKey(autoGenerate = true)
//        @NonNull
//    int id,
//     String transactionRef,
//     String transactionType, //card card-vas cash ;
//    var type:String="",//Vas or card
//    var specificTransaction:String ="",//purchase,transfer
//    var dateTime:Long=0,
//    var responseCode:String="",
//    var amount:String="" ) extends  Serializable {
//
//        fun isApproved(): Boolean {
//            return this.responseCode.trim { it <= ' ' } == "00"
//        }
//
//
//
//}
//
