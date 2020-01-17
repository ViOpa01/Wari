package com.wizarpos.emvsample.db.detailed.vas.vas_doa

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.wizarpos.emvsample.db.detailed.vas.model.CableTvCardResult
import com.wizarpos.emvsample.db.detailed.vas.vas_entity.CableTvEntity

/**
 * Created by Olije Favour on 12/12/2019.
 *Copyright (c) 2019    All rights reserved.
 */

// *Copyright (c) 2019  Itex Integrated Services  All rights reserved.

@Dao
interface CableTvDoa {

    @Insert
    fun saveCableTvData(vasTransactionResult: CableTvEntity):Long

    @Query("Select * From CableTvEntity ORDER BY dateTime DESC")
    fun getAllCableTvData( ):List <CableTvEntity>


    @Query("SELECT * FROM CableTvEntity WHERE transactionRef LIKE :id LIMIT 1")
    fun getCableTvPurchased(id: Int): LiveData<CableTvEntity>


    @Query("Select CardTransactionResult.*, CableTvEntity.* from  CableTvEntity LEFT JOIN CardTransactionResult on   CableTvEntity.card_id LIKE CardTransactionResult.id ")
    fun getCableTvResult( ): LiveData<List<CableTvCardResult>>


//    @Query("SELECT * FROM  VasTransactionResult  WHERE  VasTransactionResult.transactionRef LIKE :requestId ")
//    fun getCableTvrequestId(requestId:String ): LiveData<CableTvEntity>

    @Query("SELECT * FROM  CableTvEntity  INNER JOIN CardTransactionResult ON CardTransactionResult.id = CableTvEntity.card_id Where CardTransactionResult.RRN = :RRN ")
    fun getCableTvResultwithRRN(RRN:String ): LiveData<CableTvCardResult>

}