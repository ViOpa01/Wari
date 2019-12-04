package com.wizarpos.emvsample.db.detailed;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

/**
 * Created by Olije Favour on 11/22/2019.
 * Copyright (c) 2019    All rights reserved.
 */

// *Copyright (c) 2019  Itex Integrated Services  All rights reserved.

@Dao
public  interface EodDoa {

    @Insert
    public Long saveEodData(EodData eodData);


    //    @Update(onConflict = OnConflictStrategy.REPLACE)
//    fun updateTransactionResult (id:Int):  LiveData<Int>
    @Query("Select * From EodData ORDER BY dateTime DESC")
    LiveData<List<EodData>> getAllEodTransactions( );


    @Query("SELECT * FROM EodData WHERE id LIKE :id LIMIT 1")
    LiveData<EodData> getEod(int id);

//    @Query("SELECT * FROM EodData WHERE id LIKE :id LIMIT 1")
//    fun getImmediately(id: Int): EodData

    @Query("SELECT * FROM EodData ORDER BY dateTime DESC ")
    LiveData<List<EodData>> findAllEod();

    @Query("SELECT * FROM EodData WHERE responseCode !=:responsecode AND dateTime >= :minDate AND dateTime <= :maxDate ORDER BY dateTime DESC ")
    LiveData<List<EodData>> findDeclinedEodInDateRange(Long minDate, Long maxDate,String responsecode);

    @Query("SELECT * FROM EodData WHERE responseCode =:responsecode AND dateTime >= :minDate AND dateTime <= :maxDate ORDER BY dateTime DESC ")
    LiveData<List<EodData>> findApprovedEodInDateRange(Long minDate, Long maxDate, String responsecode );


}
