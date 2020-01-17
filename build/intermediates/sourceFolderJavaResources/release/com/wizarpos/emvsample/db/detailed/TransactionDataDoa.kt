package com.wizarpos.emvsample.db.detailed

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import com.iisysgroup.poslib.host.entities.TransactionResult

/**
 * Created by Olije Favour on 11/5/2019.
 *Copyright (c) 2019    All rights reserved.
 */

// Copyright (c) 2019  Itex Integrated Services  All rights reserved.

@Dao
interface  TransactionDataDoa {


    @Insert
    fun saveTransData(cardTransactionResult: CardTransactionResult):Long


//    @Update(onConflict = OnConflictStrategy.REPLACE)
//    fun updateTransactionResult (id:Int):  LiveData<Int>
    @Query("Select * From CardTransactionResult ORDER BY LongDateTime DESC")
    fun getAllTransactions( ):List <CardTransactionResult>


    @Query("SELECT * FROM CardTransactionResult WHERE id LIKE :id LIMIT 1")
    fun getTransactionByID(id: Int): LiveData<CardTransactionResult>

    @Query("SELECT * FROM CardTransactionResult WHERE RRN LIKE :rrn LIMIT 1")
    fun getTransactionByRRN(rrn: String): LiveData<CardTransactionResult>

    @Query("SELECT * FROM CardTransactionResult WHERE id LIKE :id LIMIT 1")
     fun getImmediately(id: Int): CardTransactionResult

    @Query("SELECT * FROM CardTransactionResult ORDER BY LongDateTime DESC ")
     fun findAll(): LiveData<List<CardTransactionResult>>

    @Query("SELECT * FROM CardTransactionResult WHERE responseCode !=:responsecode AND LongDateTime >= :minDate AND LongDateTime <= :maxDate ORDER BY LongDateTime DESC ")
     fun findDeclinedTransactionsInDateRange(minDate: Long, maxDate: Long,responsecode :String): LiveData<List<CardTransactionResult>>

    @Query("SELECT * FROM CardTransactionResult WHERE responseCode =:responsecode AND LongDateTime >= :minDate AND LongDateTime <= :maxDate ORDER BY LongDateTime DESC ")
    fun findApprovedTransactionsInDateRange(minDate: Long, maxDate: Long, responsecode :String): LiveData<List<CardTransactionResult>>



//    @Query("Select * From CardTransactionResult where paymentMethod= :paymentMethod And transactionType= :transactionType")
//    fun getTransactionsByTypeandMethod(paymentMethod:String ,transactionType:String ):List <CardTransactionResult>

//    @Query("UPDATE CardTransactionResult SET responseCode= :responseCode, beneficiaryAcc = :beneficiaryAcc ,beneficiaryName= :beneficiaryName , bankCode= :bankCode, beneficiaryBank= :beneficiaryBank, benefeciaryEmail= :benefeciaryEmail, beneficiaryPhone= :beneficiaryPhone, requestId= :requestId, remarks= :remarks, type= :type, vasResponseCode= :vasResponseCode , vasTransactionStatus= :vasTransactionStatus , vasResponseDescription= :vasResponseDescription , date= :date, vasTransactionType= :vasTransactionType, paymentMethod=:paymentMethod, transactionType=:transactionType WHERE id LIKE :id ")
//    fun updateItem(        id: Int,
//                           responseCode:String,
//                           beneficiaryAcc: String,
//                           beneficiaryName:String,
//                           bankCode:String,
//                           beneficiaryBank:String,
//                           benefeciaryEmail:String,
//                           beneficiaryPhone:String,
//                           requestId:String,
//                           remarks:String,
//                           type:String,
//                           vasResponseCode:String ,
//                           vasTransactionStatus :String,
//                           vasResponseDescription:String ,
//                           date:String,
//                           vasTransactionType:String,
//                           paymentMethod:String,
//                           transactionType:String): Int

}