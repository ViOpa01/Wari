package com.wizarpos.emvsample.db.detailed.vas.vas_doa

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.wizarpos.emvsample.db.detailed.vas.model.TransferCardResult
import com.wizarpos.emvsample.db.detailed.vas.vas_entity.TransferEntity

/**
 * Created by Olije Favour on 12/12/2019.
 *Copyright (c) 2019    All rights reserved.
 */

// *Copyright (c) 2019  Itex Integrated Services  All rights reserved.

@Dao
interface TransferDoa {

    @Insert
    fun saveTransferData(vasTransactionResult: TransferEntity):Long

    @Query("Select * From TransferEntity ORDER BY dateTime DESC")
    fun getTransferData( ):List <TransferEntity>


    @Query("SELECT * FROM TransferEntity WHERE card_id LIKE :id LIMIT 1")
    fun getTransferPurchased(id: Int): LiveData<TransferEntity>


    @Query("Select CardTransactionResult.*, TransferEntity.* from  TransferEntity LEFT JOIN CardTransactionResult on   TransferEntity.card_id LIKE CardTransactionResult.id ")
    fun getTransferDoaResult( ): LiveData<List<TransferCardResult>>


    @Query("SELECT * FROM  TransferEntity  WHERE  TransferEntity.transactionRef LIKE :transactionRef ")
    fun getTransferwithrequestId(transactionRef:String ): LiveData<TransferEntity>

    @Query("SELECT * FROM  TransferEntity  INNER JOIN CardTransactionResult ON CardTransactionResult.id = TransferEntity.card_id Where CardTransactionResult.RRN = :RRN ")
    fun getTransferResultwithRRN(RRN:String ): LiveData<TransferCardResult>

}