package com.wizarpos.emvsample.db.detailed.vas.vas_doa

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.wizarpos.emvsample.db.detailed.VasTransactionResult
import com.wizarpos.emvsample.db.detailed.vas.model.AirtimeCardResult
import com.wizarpos.emvsample.db.detailed.vas.vas_entity.AirtimeEntity

/**
 * Created by Olije Favour on 12/12/2019.
 *Copyright (c) 2019    All rights reserved.
 */

// *Copyright (c) 2019  Itex Integrated Services  All rights reserved.

@Dao
interface AirtimeDoa {


    @Insert
    fun saveAirtimeData(vasTransactionResult: AirtimeEntity):Long

    @Query("Select * From AirtimeEntity ORDER BY card_id DESC")
    fun getAllAirtimeData():List <AirtimeEntity>


    @Query("SELECT * FROM AirtimeEntity WHERE transactionRef = :id LIMIT 1")
    fun getAirtimePurchased(id: Int): LiveData<AirtimeEntity>


//    @Query("Select CardTransactionResult.*, AirtimeEntity.* from  AirtimeEntity LEFT JOIN CardTransactionResult on   AirtimeEntity.card_id = CardTransactionResult.id ")
//    fun getDataResult( ): LiveData<List<AirtimeCardResult>>


    @Query("SELECT * FROM  AirtimeEntity  WHERE  AirtimeEntity.transactionRef LIKE :transactionRef ")
    fun getAirtimewithrequestId(transactionRef:String ): LiveData<AirtimeEntity>

//    @Query("SELECT * FROM  AirtimeEntity  INNER JOIN CardTransactionResult ON CardTransactionResult.id = AirtimeEntity.card_id Where CardTransactionResult.RRN = :RRN ")
//    fun getAirtimeResultwithRRN(RRN:String): LiveData<AirtimeCardResult>

}