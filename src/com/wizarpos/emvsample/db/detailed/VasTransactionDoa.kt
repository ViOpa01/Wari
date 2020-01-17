package com.wizarpos.emvsample.db.detailed

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import org.jetbrains.annotations.Nullable

/**
 * Created by Olije Favour on 11/8/2019.
 *Copyright (c) 2019    All rights reserved.
 */

// *Copyright (c) 2019  Itex Integrated Services  All rights reserved.

@Dao
interface VasTransactionDoa {

    @Insert
    fun saveVasTransData(vasTransactionResult: VasTransactionResult):Long

    @Query("Select * From VasTransactionResult ORDER BY dateTime DESC")
    fun getAllVasTransactions():List <VasTransactionResult>


    @Query("SELECT * FROM VasTransactionResult WHERE card_id LIKE :id LIMIT 1")
    fun getVas(id: Int): LiveData<VasTransactionResult>


    @Query("Select CardTransactionResult.*, VasTransactionResult.* from  VasTransactionResult LEFT JOIN CardTransactionResult on   VasTransactionResult.card_id LIKE CardTransactionResult.id ")
    fun getVasResult( ):LiveData<List<VasCardResult>>


//    @Query("SELECT * FROM  VasTransactionResult  WHERE  VasTransactionResult.requestId LIKE :requestId ")
//    fun getVasResultwithrequestId(requestId:String ):LiveData<VasCardResult>


    @Query("SELECT * FROM  VasTransactionResult  INNER JOIN AirtimeEntity on VasTransactionResult.transactionRef = AirtimeEntity.transactionRef where VasTransactionResult.vasType = :transactionRef   ")
    fun getAirtimeResultwithrequestId(transactionRef:String ):LiveData<VasCardResult>


    @Query("SELECT * FROM  VasTransactionResult  INNER JOIN CardTransactionResult ON CardTransactionResult.id = VasTransactionResult.card_id  INNER JOIN AirtimeEntity on VasTransactionResult.transactionRef = AirtimeEntity.transactionRef Where CardTransactionResult.RRN = :RRN ")
    fun getAirtimeResultwithRRN(RRN:String ):LiveData<VasCardResult>

}