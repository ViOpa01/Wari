package com.wizarpos.emvsample.db.detailed.vas.vas_doa

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.wizarpos.emvsample.db.detailed.vas.model.DiscoCardResult
import com.wizarpos.emvsample.db.detailed.vas.vas_entity.DiscoEntity
import com.wizarpos.emvsample.services.discos.activities.DiscosActivity

/**
 * Created by Olije Favour on 12/12/2019.
 *Copyright (c) 2019    All rights reserved.
 */

// *Copyright (c) 2019  Itex Integrated Services  All rights reserved.

@Dao
interface  DiscoDoa {

    @Insert
    fun savDiscoData(vasTransactionResult: DiscoEntity):Long

    @Query("Select * From DiscoEntity ORDER BY dateTime DESC")
    fun getDiscoData( ):List <DiscoEntity>


    @Query("SELECT * FROM DiscoEntity WHERE card_id LIKE :id LIMIT 1")
    fun getAirtimePurchased(id: Int): LiveData<DiscoEntity>


    @Query("Select CardTransactionResult.*, DiscoEntity.* from  DiscoEntity LEFT JOIN CardTransactionResult on   DiscoEntity.card_id LIKE CardTransactionResult.id ")
    fun getDiscoResult( ): LiveData<List<DiscoEntity>>


    @Query("SELECT * FROM  DiscoEntity  WHERE  DiscoEntity.transactionId LIKE :transactionId ")
    fun getDiscowithrequestId(transactionId:String ): LiveData<DiscoEntity>

    @Query("SELECT * FROM  DiscoEntity  INNER JOIN CardTransactionResult ON DiscoEntity.id = DiscoEntity.card_id Where CardTransactionResult.RRN = :RRN ")
    fun getDiscoResultwithRRN(RRN:String ): LiveData<DiscoCardResult>

}